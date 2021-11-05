package com.chen.app.net.token;

import android.annotation.SuppressLint;
import android.os.ConditionVariable;
import android.text.TextUtils;

import com.chen.app.net.base.BaseResponse;
import com.chen.app.net.utils.MetaDataUtil;
import com.chen.app.utils.KLog;
import com.chen.app.utils.MMKVUtil;
import com.chen.app.utils.gson.GsonUtil;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author by chennan
 * Date on 2021/11/3
 * Description
 */
public class TokenUtil {
    public static boolean isTokenExpired(String response) {
        try {
            Type type = new TypeToken<BaseResponse<Object>>() {
            }.getType();
            BaseResponse<Object> baseResponse = GsonUtil.getGson().fromJson(response, type);
            if (baseResponse != null) {
                int remainTime = MMKVUtil.getIntValue("expirationTime") - baseResponse.getCurrentTime();//过期时间现在单位是s
//                KLog.e("token:remainTime----"+remainTime);
                if (remainTime < 0) {
                    return false;
                }
//                if (baseResponse.getCode() == 2007 || remainTime < 60 ) {
                if (remainTime < 60 * 60 * 47) {//提前2天的时候自动刷新
                    return true;
                }
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @SuppressLint("CheckResult")
    public static String getNewToken(){
        final ConditionVariable variable = new ConditionVariable();
        if(TextUtils.isEmpty(MMKVUtil.getStringValue("refreshToken"))){
            return "";
        }

        final String[] newToken = new String[1];
        Observable.create((ObservableOnSubscribe) emitter -> {
            try {
                KLog.INSTANCE.e("刷新token前  refreshToken：" + MMKVUtil.getStringValue("refreshToken"));
                Request request = new Request.Builder()
                        .url(MetaDataUtil.getBaseUrl() + "/chinese/student/refreshToken?refreshToken=" + MMKVUtil.getStringValue("refreshToken"))
                        .build();

                Response response = new OkHttpClient().newCall(request).execute();
                String result = response.body().string();
                KLog.INSTANCE.e("网络返回 刷新token请求结果：" + result);

                Type type = new TypeToken<BaseResponse<TokenBean>>() {
                }.getType();

                BaseResponse<TokenBean> tokenBeanBaseResponse = GsonUtil.getGson().fromJson(result, type);
                KLog.INSTANCE.e("TokenBean----------" + tokenBeanBaseResponse.getData());
                if (tokenBeanBaseResponse != null && tokenBeanBaseResponse.getCode() == 0) {
                    newToken[0] = tokenBeanBaseResponse.getData().getAccessToken();
                    MMKVUtil.putStringValue("accessToken", tokenBeanBaseResponse.getData().getAccessToken());
                    MMKVUtil.putStringValue("refreshToken", tokenBeanBaseResponse.getData().getRefreshToken());
                    MMKVUtil.putIntValue("expirationTime", tokenBeanBaseResponse.getData().getExpirationTime());
                } else {
                    newToken[0] = MMKVUtil.getStringValue("accessToken");
                }
            }catch (IOException e){
                e.printStackTrace();
                emitter.onNext("");
            }
            emitter.onNext("");
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(o -> variable.open());
        variable.block();
        return  newToken[0];
    }
} 
