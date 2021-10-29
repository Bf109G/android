package com.chen.app

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.chen.app.BuildConfig

/**
 * Author by chennan
 * Date on 2021/10/28
 * Description
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initARouter()
    }

    private fun initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }
}