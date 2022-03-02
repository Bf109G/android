package com.chen.app.viewmodel

import android.app.Application
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.launcher.ARouter
import com.chen.app.R
import com.chen.app.base.BaseViewModel
import com.chen.app.bus.event.SingleLiveEvent
import com.chen.app.model.bean.MenuInfo
import com.chen.app.model.bean.UserInfo
import com.chen.app.net.RetrofitRequest
import com.chen.app.net.exception.ResponseException
import com.chen.app.net.observer.DefaultObserver
import com.chen.app.net.utils.RxSchedulers
import com.chen.app.router.RouterActivityPath
import com.chen.app.router.callback.CustomNavigationCallBack
import com.google.gson.JsonObject
import io.reactivex.disposables.Disposable

/**
 * Author by chennan
 * Date on 2022/2/14
 * Description
 */
class HomeViewModel(application: Application) : BaseViewModel(application) {

    var btnText = SingleLiveEvent<String>()

    fun onClick(v: View) {
//        KLog.i("HomeViewModel", btnText.value)
//        KLog.i("HomeViewModel", v.id)
        when (v.id) {
            R.id.button1 -> {
                btnText.value = "jump"
//                ARouter.getInstance().build(RouterActivityPath.DETAIL)
//                    .withBoolean("flag", true)
//                    .withInt("total", 1)
//                    .withString("name", "detail")
//                    .withObject("userInfo", UserInfo("chennan", 18))
//                    .navigation(getApplication(), CustomNavigationCallBack())
                this.signIn()
            }
            R.id.btnOOM -> {
                ARouter.getInstance().build(RouterActivityPath.OOM)
                    .navigation(getApplication(), CustomNavigationCallBack())
            }
        }
    }

    var stateData: MutableLiveData<Any> = MutableLiveData()

    fun doPost() {
        val observable = RetrofitRequest.instance.getBottomMenu()
        observable
            .compose(RxSchedulers.mainSchedulerErrorNext())
            .subscribe(object : DefaultObserver<List<MenuInfo>>() {
                override fun onComplete(t: List<MenuInfo>) {
                }

                override fun onError(e: ResponseException) {
                }

                override fun onSubscribes(d: Disposable) {
                    addDisposable(d)
                }

            })
    }

    private fun signIn() {
        val jsonObject = JsonObject()
        jsonObject.addProperty("mobile", "17621148720")
        jsonObject.addProperty("password", "123456")

        val observable = RetrofitRequest.instance.signInByPwd(jsonObject)
        observable.compose(RxSchedulers.mainSchedulerErrorNext())
            .subscribe(object : DefaultObserver<Any>() {
                override fun onComplete(t: Any) {
                }

                override fun onError(e: ResponseException) {
                }

                override fun onSubscribes(d: Disposable) {
                    addDisposable(d)
                }

            })
    }
}