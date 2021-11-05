package com.chen.app.net.cookie;

import java.net.CookieStore;
import java.util.List;

import androidx.annotation.NonNull;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Author by chennan
 * Date on 2021/11/3
 * Description
 */
public class CookieJarImpl implements CookieJar {

    private ICookieStore cookieStore;

    public CookieJarImpl(ICookieStore cookieStore) {
        if (cookieStore == null) throw new IllegalArgumentException("cookieStore can not be null.");
        this.cookieStore = cookieStore;
    }


    @Override
    public void saveFromResponse(@NonNull HttpUrl httpUrl, @NonNull List<Cookie> cookies) {
        cookieStore.add(httpUrl, cookies);
    }

    @NonNull
    @Override
    public List<Cookie> loadForRequest(@NonNull HttpUrl httpUrl) {
        return cookieStore.get(httpUrl);
    }

    public ICookieStore getCookieStore(){
        return cookieStore;
    }
}
