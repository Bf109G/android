package com.chen.app.router.callback

import android.widget.Toast
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.chen.app.utils.KLog
import com.chen.app.utils.ToastUtils
import io.reactivex.Observable

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
//        Toast.makeText(postcard.context, "去登录", Toast.LENGTH_LONG).show()
    }

    override fun onInterrupt(postcard: Postcard) {
        KLog.d("CustomInterceptor", "onInterrupt=${postcard.path}")
        ToastUtils.showLong("去登录")
    }
}