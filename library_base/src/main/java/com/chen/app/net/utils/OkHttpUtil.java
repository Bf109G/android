package com.chen.app.net.utils;

import com.chen.app.net.cookie.MemoryCookieStore;
import com.chen.app.net.interceptor.HttpCommonInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Author by chennan
 * Date on 2021/11/3
 * Description
 */
public class OkHttpUtil  {
    private OkHttpClient okHttpClient;

    private MemoryCookieStore mStore = new MemoryCookieStore();

    public MemoryCookieStore getMemoryCookieStore() {
        return mStore;
    }

    private static class OkHttpClientHelperHolder {
        private static OkHttpUtil instance = new OkHttpUtil();
    }

    public static OkHttpUtil getInstance() {
        return OkHttpClientHelperHolder.instance;
    }
    protected OkHttpClient okHttpClient(){
        if(okHttpClient == null){
            okHttpClient = okHttpBuilder().build();
        }
        return okHttpClient;
    }

    private OkHttpClient.Builder okHttpBuilder(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(NetConfig.OKHTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(NetConfig.OKHTTP_READ_WRITE_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(NetConfig.OKHTTP_READ_WRITE_TIMEOUT, TimeUnit.SECONDS);
        builder.addInterceptor(new HttpCommonInterceptor());
        return builder;
    }
}
