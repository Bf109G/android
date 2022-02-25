package com.chen.app.net.utils;

import com.chen.app.net.base.BaseResponse;
import com.chen.app.net.exception.ExceptionHandler;
import com.chen.app.net.exception.ResponseException;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Author by chennan
 * Date on 2022/2/19
 * Description
 */
public class RxSchedulers {
    public static <T> ObservableTransformer<T, T> mainTransformer() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> ObservableTransformer<T, T> ioTransformer() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    public static <T> ObservableTransformer<BaseResponse<T>, T> exceptionTransformer() {
        return upstream -> upstream
                .onErrorResumeNext(new ErrorResumeFunction<>())
                .flatMap(new ResponseFunction<>());
    }

    private static class ErrorResumeFunction<T> implements Function<Throwable, Observable<T>> {

        @Override
        public Observable<T> apply(@NonNull Throwable throwable) throws Exception {
            return Observable.error(ExceptionHandler.handleException(throwable));
        }
    }

    private static class ResponseFunction<T> implements Function<BaseResponse<T>, Observable<T>> {

        @Override
        public Observable<T> apply(@NonNull BaseResponse<T> baseResponse) throws Exception {
            if (!baseResponse.isOk()) {
                return Observable.error(new ResponseException(baseResponse.getCode(), baseResponse.getMessage()));
            }
            return Observable.just(baseResponse.getData());
        }
    }
} 
