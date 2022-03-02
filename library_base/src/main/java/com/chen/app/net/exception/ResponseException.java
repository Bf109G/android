package com.chen.app.net.exception;

import java.io.IOException;

import androidx.annotation.Nullable;

/**
 * Author by chennan
 * Date on 2022/2/19
 * Description
 */
public class ResponseException extends IOException {

    private int code;

    private String message;

    public ResponseException(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Nullable
    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
