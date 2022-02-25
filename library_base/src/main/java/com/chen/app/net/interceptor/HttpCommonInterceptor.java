package com.chen.app.net.interceptor;

import android.os.Build;
import android.text.TextUtils;

import com.chen.app.base.BuildConfig;
import com.chen.app.utils.KLog;
//import com.chen.app.utils.KLog;
//import com.chen.app.utils.MMKVUtil;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import androidx.annotation.NonNull;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Author by chennan
 * Date on 2021/11/3
 * Description
 */
public class HttpCommonInterceptor implements Interceptor {

    private static final String TAG = HttpCommonInterceptor.class.getSimpleName();

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        request = addHeader(request);
        Response response = chain.proceed(request);
//        addCookie()
        if(BuildConfig.DEBUG){
            ResponseBody responseBody = response.body();
            KLog.INSTANCE.e(TAG, "网络请求--+" + response.request().url());
            if(responseBody != null){
                BufferedSource source = responseBody.source();
                Buffer buffer = source.getBuffer();
                MediaType mediaType = responseBody.contentType();
                Charset charset = mediaType != null? mediaType.charset(StandardCharsets.UTF_8) :
                        StandardCharsets.UTF_8;
                Charset newCharSet = Util.bomAwareCharset(source, charset);
                String str = buffer.clone().readString(newCharSet);
                KLog.INSTANCE.json(TAG, str);
            }
        }
        return response;
    }

    private Request addHeader(Request request){
        Request.Builder builder = request.newBuilder();
        builder.addHeader("sysVersion", Build.VERSION.SDK_INT + "-android" + Build.VERSION.RELEASE);
        builder.addHeader("channelId", "7");
        builder.addHeader("platform", "android");

//        String accessToken = MMKVUtil.getStringValue("accessToken");
//        if(!TextUtils.isEmpty(accessToken)){
//            builder.addHeader("accessToken", accessToken);
//        }
//        KLog.INSTANCE.w(TAG, "accessToken=" + accessToken);
        return builder.build();
    }
}
