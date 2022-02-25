package com.chen.app.net.utils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author by chennan
 * Date on 2021/11/3
 * Description
 */
public class RetrofitUtil<T> {

    private Retrofit retrofit;

    private RetrofitUtil(){
        OkHttpClient okHttpClient = OkHttpUtil.getInstance().okHttpClient();
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(MetaDataUtil.getBaseUrl() + "renter/")
                .client(okHttpClient)
                .build();
    }

    public Retrofit getRetrofit(){
        return retrofit;
    }

    public static RetrofitUtil getInstance(){
        return Holder.instance;
    }

    private static  class Holder{
        private static final RetrofitUtil instance = new RetrofitUtil();
    }
}
