package com.chen.app.net.utils;

import com.chen.app.net.exception.ExceptionHandler;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Author by chennan
 * Date on 2022/2/19
 * Description
 */
public class RxUtils {
    public static <T> ObservableTransformer<T, T> mainTransformer() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> ObservableTransformer<T, T> ioTransformer() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    public static <T> ObservableTransformer<T, T> exceptionTransformer() {
        return upstream -> upstream.onErrorResumeNext(new HttpResponseFunction());
    }

    private static class HttpResponseFunction<T> implements Function<Throwable, Observable<T>> {

        @Override
        public Observable<T> apply(@NonNull Throwable throwable) {
            return Observable.error(ExceptionHandler.handleException(throwable));
        }
    }
} 
