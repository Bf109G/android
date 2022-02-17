package com.chen.app.viewmodel

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.launcher.ARouter
import com.chen.app.R
import com.chen.app.base.BaseViewModel
import com.chen.app.bus.event.SingleLiveEvent
import com.chen.app.model.bean.UserInfo
import com.chen.app.router.RouterActivityPath
import com.chen.app.router.callback.CustomNavigationCallBack

/**
 * Author by chennan
 * Date on 2022/2/14
 * Description
 */
class HomeViewModel(application: Application): BaseViewModel(application) {

    var btnText = SingleLiveEvent<String>()

//    var btnText = MutableLiveData<String>()

    fun onClick(v: View){
//        KLog.i("HomeViewModel", btnText.value)
//        KLog.i("HomeViewModel", v.id)
        when(v.id){
            R.id.button1 -> {
                btnText.value = "jump"
                ARouter.getInstance().build(RouterActivityPath.DETAIL)
                    .withBoolean("flag", true)
                    .withInt("total", 1)
                    .withString("name", "detail")
                    .withObject("userInfo", UserInfo("chennan",18))
                    .navigation(getApplication(), CustomNavigationCallBack())
            }
        }
    }
}