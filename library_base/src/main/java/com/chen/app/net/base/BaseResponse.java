package com.chen.app.net.base;

/**
 * Author by chennan
 * Date on 2021/11/2
 * Description
 */
public class BaseResponse<T> {
    private int code;
    private String message;
    private String detail;
    private T data;
    private int currentTime;

    private boolean isOk;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

    public boolean isOk() {
        return code == 1;
    }
}
