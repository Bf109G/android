package com.chen.app.net.cookie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

/**
 * Author by chennan
 * Date on 2021/11/3
 * Description
 */
public class MemoryCookieStore implements ICookieStore {

    private final HashMap<String, List<Cookie>> allCookies = new HashMap<>();

    @Override
    public void add(HttpUrl httpUrl, List<Cookie> cookieList) {
        List<Cookie> oldCookies = allCookies.get(httpUrl.host());

        if (oldCookies != null) {
            Iterator<Cookie> itNew = cookieList.iterator();
            Iterator<Cookie> itOld = oldCookies.iterator();
            while (itNew.hasNext()) {
                String va = itNew.next().name();
                while (va != null && itOld.hasNext()) {
                    String v = itOld.next().name();
                    if (v != null && va.equals(v)) {
                        itOld.remove();
                    }
                }
            }
            oldCookies.addAll(cookieList);
        } else {
            allCookies.put(httpUrl.host(), cookieList);
        }
    }

    @Override
    public List<Cookie> get(HttpUrl httpUrl) {
        List<Cookie> newCookies = new ArrayList<>();
        if (allCookies.get(httpUrl.host()) != null) {
            for (Cookie cookie : allCookies.get(httpUrl.host())) {//深拷贝 防止多线程并发时报数组越界和空指针问题
                Cookie parse = Cookie.parse(httpUrl, cookie.toString());
                newCookies.add(parse);
            }
        } else {
            allCookies.put(httpUrl.host(), newCookies);
        }
        return newCookies;
    }

    @Override
    public List<Cookie> getCookies() {
        List<Cookie> cookies = new ArrayList<>();
        Set<String> httpUrls = allCookies.keySet();
        for (String url : httpUrls) {
            cookies.addAll(allCookies.get(url));
        }
        return cookies;
    }

    @Override
    public boolean remove(HttpUrl httpUrl, Cookie cookie) {
        List<Cookie> cookies = allCookies.get(httpUrl.host());
        if (cookie != null) {
            return cookies.remove(cookie);
        }
        return false;
    }

    @Override
    public boolean removeAll() {
        allCookies.clear();
        return true;
    }
}
