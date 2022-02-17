package com.chen.app.router.callback

import android.widget.Toast
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.chen.app.utils.KLog

/**
 * Author by chennan
 * Date on 2022/2/17
 * Description
 */
class CustomNavigationCallBack: NavigationCallback {
    override fun onFound(postcard: Postcard) {
        KLog.d("CustomInterceptor", "onFound=${postcard.path}")
    }

    override fun onLost(postcard: Postcard) {
        KLog.d("CustomInterceptor", "onLost=${postcard.path}")
    }

    override fun onArrival(postcard: Postcard) {
        KLog.d("CustomInterceptor", "onArrival=${postcard.path}")
    }

    override fun onInterrupt(postcard: Postcard) {
        KLog.d("CustomInterceptor", "onInterrupt=${postcard.path}")
        Toast.makeText(postcard.context, "去登录", Toast.LENGTH_LONG).show()
    }
}