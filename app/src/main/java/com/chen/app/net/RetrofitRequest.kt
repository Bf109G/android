package com.chen.app.net

import com.chen.app.model.bean.MenuInfo
import com.chen.app.net.base.BaseRequest
import com.chen.app.net.base.BaseResponse
import io.reactivex.Observable
import android.annotation.SuppressLint


/**
 * Author by chennan
 * Date on 2022/2/19
 * Description
 */
class RetrofitRequest : BaseRequest() {

    companion object {
        val instance: RetrofitRequest by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitRequest()
        }
    }

    private var service: ApiService = retrofit.create(ApiService::class.java)

    fun getBottomMenu(): Observable<BaseResponse<List<MenuInfo>>> {
        return service.getBottomMenu()
    }
}