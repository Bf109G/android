package com.chen.app.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.chen.app.utils.KLog
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

    protected fun addDisposable(disposable: Disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        mCompositeDisposable?.add(disposable)
        KLog.i("addDisposable", mCompositeDisposable?.size())
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
        mCompositeDisposable?.dispose()
    }

    @Throws(Exception::class)
    override fun accept(disposable: Disposable?) {
        KLog.i("disposable", disposable)
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