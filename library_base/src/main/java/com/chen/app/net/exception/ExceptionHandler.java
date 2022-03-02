package com.chen.app.net.exception;

import android.net.ParseException;

import com.chen.app.utils.KLog;
import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;

/**
 * Author by chennan
 * Date on 2022/2/19
 * Description
 */
public class ExceptionHandler {
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int SERVICE_UNAVAILABLE = 503;

    /**
     * 约定异常
     */
    static class ERROR {
        /**
         * 未知错误
         */
        static final int UNKNOWN = 1000;
        /**
         * 解析错误
         */
        static final int PARSE_ERROR = 1001;
        /**
         * 网络错误
         */
        static final int INTERNET_ERROR = 1002;
        /**
         * 协议出错
         */
        static final int HTTP_ERROR = 1003;

        /**
         * 证书出错
         */
        static final int SSL_ERROR = 1005;

        /**
         * 连接超时
         */
        static final int TIMEOUT_ERROR = 1006;

        /**
         * API错误
         */
        static final int API_ERROR = 1007;
    }

    public static ResponseException handleException(Throwable e) {
        ResponseException ex;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new ResponseException(ERROR.HTTP_ERROR, e.getMessage());
            switch (httpException.code()) {
                case UNAUTHORIZED:
                    ex.setMessage("操作未授权");
                    break;
                case FORBIDDEN:
                    ex.setMessage("请求被拒绝");
                    break;
                case NOT_FOUND:
                    ex.setMessage("资源不存在");
                    break;
                case REQUEST_TIMEOUT:
                    ex.setMessage("服务器执行超时");
                    break;
                case INTERNAL_SERVER_ERROR:
                    ex.setMessage("服务器内部错误");
                    break;
                case SERVICE_UNAVAILABLE:
                    ex.setMessage("服务器不可用");
                    break;
                default:
                    ex.setMessage("网络错误");
                    break;
            }
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException
                || e instanceof MalformedJsonException) {
            ex = new ResponseException(ERROR.PARSE_ERROR, "解析错误");
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new ResponseException(ERROR.INTERNET_ERROR, "连接失败");
            return ex;
        } else if (e instanceof javax.net.ssl.SSLException) {
            ex = new ResponseException(ERROR.SSL_ERROR, "证书验证失败");
            return ex;
        } else if (e instanceof ConnectTimeoutException) {
            ex = new ResponseException(ERROR.TIMEOUT_ERROR, "连接超时");
            return ex;
        } else if (e instanceof SocketTimeoutException) {
            ex = new ResponseException(ERROR.TIMEOUT_ERROR, "连接超时");
            return ex;
        } else if (e instanceof UnknownHostException) {
            ex = new ResponseException(ERROR.TIMEOUT_ERROR, "主机地址未知");
            return ex;
        } else if(e instanceof  SecurityException){
            ex = new ResponseException(ERROR.INTERNET_ERROR,  e.getMessage());
            return ex;
        } else if(e instanceof ResponseException){
            ex = new ResponseException(((ResponseException) e).getCode(),  e.getMessage());
            return ex;
        } else {
            ex = new ResponseException(ERROR.UNKNOWN, "未知错误");
            return ex;
        }
    }
} 
