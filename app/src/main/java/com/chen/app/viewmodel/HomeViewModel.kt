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
import com.chen.app.model.bean.MenuInfo
import com.chen.app.model.bean.UserInfo
import com.chen.app.net.RetrofitRequest
import com.chen.app.net.base.BaseResponse
import com.chen.app.net.observer.DefaultObserver
import com.chen.app.net.utils.RetrofitUtil
import com.chen.app.net.utils.RxUtils
import com.chen.app.router.RouterActivityPath
import com.chen.app.router.callback.CustomNavigationCallBack
import com.chen.app.utils.KLog
import io.reactivex.disposables.Disposable

/**
 * Author by chennan
 * Date on 2022/2/14
 * Description
 */
class HomeViewModel(application: Application): BaseViewModel(application) {

    var btnText = SingleLiveEvent<String>()

    fun onClick(v: View){
//        KLog.i("HomeViewModel", btnText.value)
//        KLog.i("HomeViewModel", v.id)
        when(v.id){
            R.id.button1 -> {
                btnText.value = "jump"
//                Toast.makeText(getApplication(), "jump", Toast.LENGTH_LONG).show()
                ARouter.getInstance().build(RouterActivityPath.DETAIL)
                    .withBoolean("flag", true)
                    .withInt("total", 1)
                    .withString("name", "detail")
                    .withObject("userInfo", UserInfo("chennan",18))
                    .navigation(getApplication(), CustomNavigationCallBack())

            }
        }
    }

    fun doPost(){
        val observable =  RetrofitRequest.instance.getBottomMenu()
        observable.compose(RxUtils.mainTransformer())
            .compose(RxUtils.exceptionTransformer())
            .subscribe(object : DefaultObserver<List<MenuInfo>>() {
                override fun onSubscribe(d: Disposable) {
                    super.onSubscribe(d)
                }

                override fun onComplete() {
                    super.onComplete()
                }

                override fun onNext(baseResponse: BaseResponse<List<MenuInfo>>) {
                    super.onNext(baseResponse)
                }

                override fun onError(throwable: Throwable) {
                    super.onError(throwable)
                }
            })
    }
}