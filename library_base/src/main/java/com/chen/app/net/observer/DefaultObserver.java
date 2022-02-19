package com.chen.app.net.observer;

import com.chen.app.net.base.BaseObserver;
import com.chen.app.net.base.BaseResponse;
import com.chen.app.utils.KLog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import androidx.annotation.NonNull;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Author by chennan
 * Date on 2022/2/19
 * Description
 */
public class DefaultObserver<T> extends BaseObserver<T> {
    @Override
    public void onSubscribe(@NonNull Disposable d) {
        super.onSubscribe(d);
    }

    @Override
    public void onComplete() {
        super.onComplete();
    }

    @Override
    public void onNext(@NonNull BaseResponse<T> baseResponse) {
        super.onNext(baseResponse);
        int code = baseResponse.getCode();
        switch (code){
            case 0:
            default:
                break;
        }
    }

    @Override
    public void onError(@NonNull Throwable throwable) {
        KLog.INSTANCE.e("进入--DefaultObserver--onError");
        super.onError(throwable);
        try {
            if (throwable instanceof HttpException) {   //  处理服务器返回的非成功异常
                ResponseBody responseBody = ((HttpException) throwable).response().errorBody();
                if (responseBody != null) {
                    Type type = new TypeToken<BaseResponse<Object>>() {
                    }.getType();

                    BaseResponse baseResponse = new Gson().fromJson(responseBody.string(), type);
                    onNext(baseResponse);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
