package com.chen.app.net.interceptor;

//import com.chen.app.utils.MMKVUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Author by chennan
 * Date on 2021/11/3
 * Description
 */
public class TokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        Response response = chain.proceed(builder.build());
        ResponseBody responseBody = response.body();
        MediaType mediaType = responseBody.contentType();
        String content = responseBody.string();

//        if(!"".equals(MMKVUtil.getStringValue("refreshToken"))){
//
//        }

        return response.newBuilder()
                .body(ResponseBody.create(mediaType, content))
                .build();
    }
}
