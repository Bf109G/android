package com.chen.app.net

import com.chen.app.model.bean.MenuInfo
import com.chen.app.net.base.BaseResponse
import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Author by chennan
 * Date on 2022/2/19
 * Description
 */
interface ApiService {
//    @POST("homePage/v1/listBottomMenu")
//    fun getBottomMenu(): Observable<BaseResponse<List<MenuInfo>>>

    @POST("homePage/v1/listBottomMenu")
    fun getBottomMenu(): Observable<List<MenuInfo>>

    @POST("login/v1/loginByPassword")
    fun signInByPwd(@Body jsonObject: JsonObject): Observable<Any>
}