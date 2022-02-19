package com.chen.app.net.base;

import com.chen.app.net.utils.RetrofitUtil;

import retrofit2.Retrofit;

/**
 * Author by chennan
 * Date on 2021/11/3
 * Description
 */
public class BaseRequest {
    protected Retrofit retrofit;

    public BaseRequest(){
        this.retrofit = RetrofitUtil.getInstance().getRetrofit();
    }
}
