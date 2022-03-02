package com.chen.app.net.observer;

import com.chen.app.net.base.BaseObserver;
import com.chen.app.net.exception.ResponseException;
import com.chen.app.utils.KLog;
import com.chen.app.utils.UtilsBridge;
import com.chen.app.utils.gson.GsonUtil;

import androidx.annotation.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Author by chennan
 * Date on 2022/2/19
 * Description
 */
public abstract class DefaultObserver<T> extends BaseObserver<T> {
    @Override
    public void onSubscribe(@NonNull Disposable d) {
        super.onSubscribe(d);
        onSubscribes(d);
    }

    @Override
    public void onComplete() {
        super.onComplete();
    }

    @Override
    public void onNext(@NonNull T t) {
        super.onNext(t);
        KLog.i(GsonUtil.bean2String(t));
        onComplete(t);
    }

    @Override
    public void onError(@NonNull Throwable throwable) {
        KLog.e("进入--DefaultObserver--onError:" + throwable);
        super.onError(throwable);

        if (throwable instanceof ResponseException) {
            KLog.e(((ResponseException) throwable).getCode() + throwable.getMessage());
            onError(((ResponseException) throwable));
            UtilsBridge.toastShowShort("code=" + ((ResponseException) throwable).getCode() + "message=" + throwable.getMessage());
        }
    }

    protected abstract void onComplete(@NonNull T t);

    protected abstract void onError(@NonNull ResponseException e);

    protected abstract void onSubscribes(@NonNull Disposable d);
}
