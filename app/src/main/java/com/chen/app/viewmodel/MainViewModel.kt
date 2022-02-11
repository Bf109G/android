package com.chen.app.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.chen.app.base.BaseViewModel
import com.chen.app.bus.event.SingleLiveEvent

/**
 * Author by chennan
 * Date on 2021/11/5
 * Description
 */
class MainViewModel(application: Application): BaseViewModel(application) {

    var requestCameraPermissions = SingleLiveEvent<Boolean>()

//    var textName = SingleLiveEvent<String>()

    var textName = MutableLiveData<String>("123")

    fun getText(): MutableLiveData<String>{
        return textName
    }

    fun setText(){
        textName.value = "chennan"
    }

}