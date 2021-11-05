package com.chen.app.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

/**
 * Author by chennan
 * Date on 2021/11/1
 * Description
 */
open class BaseViewModel(application: Application) : AndroidViewModel(application), IBaseViewModel,
    IAction, Consumer<Disposable> {

    private var mCompositeDisposable: CompositeDisposable? = null

    private fun addDisposable(disposable: Disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }

        mCompositeDisposable?.add(disposable)
    }

    override fun onAny() {}

    override fun onCreate() {}

    override fun onDestroy() {}

    override fun onStart() {}

    override fun onStop() {}

    override fun onResume() {}

    override fun onPause() {}

    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable?.clear()
    }

    @Throws(Exception::class)
    override fun accept(disposable: Disposable?) {
        disposable?.let {
            addDisposable(it)
        }
    }

    override fun alert(msg: String) {}

    override fun showToast(text: String) {}

    override fun showLoading(resId: Int) {}

    override fun hideLoading() {}

    override fun finish() {}
}