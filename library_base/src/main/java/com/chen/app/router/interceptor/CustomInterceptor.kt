package com.chen.app.router.interceptor

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor
import com.chen.app.router.RouterActivityPath
import com.chen.app.utils.KLog
import com.chen.app.utils.gson.GsonUtil

/**
 * Author by chennan
 * Date on 2022/2/17
 * Description
 */
@Interceptor(name="LoginInterceptor", priority = 1)
class CustomInterceptor: IInterceptor {

    override fun init(context: Context?) {
      KLog.d("CustomInterceptor", "CustomInterceptor初始化了")
    }

    override fun process(postcard: Postcard, callback: InterceptorCallback) {
       KLog.d("CustomInterceptor", postcard.path)
        if(postcard.path == RouterActivityPath.DETAIL){
            val token = "123"
            if(token.isEmpty()){
                callback.onInterrupt(RuntimeException("还没有登录，去登录"))
            }else{
                callback.onContinue(postcard)
            }
        }else{
            callback.onContinue(postcard)
        }
    }
}