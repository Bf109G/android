package com.chen.app.net.exception;

import androidx.annotation.Nullable;

/**
 * Author by chennan
 * Date on 2022/2/19
 * Description
 */
public class ResponseException extends  Exception{

    public int code;

    public String message;

    ResponseException(Throwable throwable, int code){
        super(throwable);
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
