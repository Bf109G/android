package com.chen.app.net.observer;

import android.graphics.Color;
import android.view.Gravity;
import android.widget.Toast;

import com.chen.app.base.R;
import com.chen.app.net.base.BaseObserver;
import com.chen.app.net.exception.ResponseException;
import com.chen.app.utils.AppUtils;
import com.chen.app.utils.ColorUtils;
import com.chen.app.utils.KLog;
import com.chen.app.utils.ToastUtils;
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
        KLog.INSTANCE.i("DefaultObserver", GsonUtil.bean2String(t));
        onComplete(t);
    }

    @Override
    public void onError(@NonNull Throwable throwable) {
        KLog.INSTANCE.e("进入--DefaultObserver--onError", throwable);
        super.onError(throwable);

        if(throwable instanceof ResponseException){
            KLog.INSTANCE.e("toastError", throwable.getMessage());
            KLog.INSTANCE.e("toastError", ((ResponseException) throwable).getCode());
            onError(((ResponseException) throwable));

            ToastUtils.showShort("code=" +((ResponseException) throwable).getCode() + "message=" + throwable.getMessage());
//            ToastUtils.make().setTextColor(Color.GREEN).setDurationIsLong(true).show("");
//            ToastUtils.make().setTextColor(Color.WHITE).setBgColor(ColorUtils.getColor(R.color.color_353535)).show("");
//            ToastUtils.make().setGravity(Gravity.CENTER,0,0).show("");
//            ToastUtils.make().setMode(ToastUtils.MODE.DARK).show("");
        }


//        try {
//            KLog.INSTANCE.e("toastError", throwable.getMessage());
//            if (throwable instanceof HttpException) {   //  处理服务器返回的非成功异常
//                KLog.INSTANCE.e("HttpException", ((HttpException) throwable).code());
//                ResponseBody responseBody = ((HttpException) throwable).response().errorBody();
//                if (responseBody != null) {
//                    Type type = new TypeToken<BaseResponse<Object>>() {
//                    }.getType();
//
//                    BaseResponse baseResponse = new Gson().fromJson(responseBody.string(), type);
////                    onNext(baseResponse);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

//    private void onException(){
//        KLog.INSTANCE.i("toastError", );
//    }

    protected abstract void onComplete(@NonNull T t);

     protected abstract void onError(@NonNull ResponseException e);

     protected abstract void onSubscribes(@NonNull Disposable d);
}
