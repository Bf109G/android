package com.chen.app.net.cookie;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

/**
 * Author by chennan
 * Date on 2021/11/3
 * Description
 */
public interface ICookieStore {
    void add(HttpUrl httpUrl, List<Cookie> cookieList);

    List<Cookie> get(HttpUrl httpUrl);

    List<Cookie> getCookies();

    boolean remove(HttpUrl httpUrl, Cookie cookie);

    boolean removeAll();
}
