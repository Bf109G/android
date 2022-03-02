package com.chen.app.net.gsonConverter;

import com.chen.app.net.base.BaseResponse;
import com.chen.app.net.exception.ResponseException;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import androidx.annotation.NonNull;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Author by chennan
 * Date on 2022/3/2
 * Description
 */
final public class BaseGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    BaseGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(@NonNull ResponseBody value) throws IOException {
        String jsonStr = value.string();
        try {
            BaseResponse baseResponse = gson.fromJson(jsonStr, BaseResponse.class);
            if(!baseResponse.isOk()){
                throw new ResponseException(baseResponse.getCode(), baseResponse.getMessage());
            }
            return (T) baseResponse.getData();
        }  finally {
            value.close();
        }
    }
} 
