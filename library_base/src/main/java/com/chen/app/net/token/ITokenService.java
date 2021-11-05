package com.chen.app.net.token;

import com.chen.app.net.base.BaseResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Author by chennan
 * Date on 2021/11/3
 * Description
 */
public interface ITokenService {
    /**
     * 刷新token接口
     * @param refreshToken
     * @return
     */
    @GET("chinese/student/refreshToken")
    Call<BaseResponse<TokenBean>> refreshToken(@Query(value = "refreshToken", encoded = true) String refreshToken);
} 
