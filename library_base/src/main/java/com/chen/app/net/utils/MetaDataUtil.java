package com.chen.app.net.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.chen.app.utils.KLog;

import java.lang.reflect.InvocationTargetException;

/**
 * Author by chennan
 * Date on 2021/11/3
 * Description
 */
public class MetaDataUtil {
    private static final String TAG = MetaDataUtil.class.getSimpleName();

    private static Application mApplication;

    public static Application getApp() {
        if (mApplication == null) {
            mApplication = getApplicationByReflect();
        }
        return mApplication;
    }

    private static Application getApplicationByReflect() {
        try {
            @SuppressLint("PrivateApi") Class<?> activityThread = Class.forName("android.app.ActivityThread");
            Object thread = activityThread.getMethod("currentActivityThread").invoke(null);
            Object app = activityThread.getMethod("getApplication").invoke(thread);
            if (app == null) {
                throw new NullPointerException("u should init first");
            }
            return (Application) app;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException
                | ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("u should init first");
    }

    private static int getEnvironment() {
        try {
            ApplicationInfo appInfo = getApp().getPackageManager().getApplicationInfo(getApp().getPackageName(),
                    PackageManager.GET_META_DATA);
            int environment = appInfo.metaData.getInt("ENVIRONMENT");
            if (environment == 0) {
                KLog.INSTANCE.e(TAG, "environment" + environment + "--dev环境");
            } else if (environment == 1) {
                KLog.INSTANCE.e(TAG,"environment" + environment + "--qa环境");
            } else if (environment == 2) {
                KLog.INSTANCE.e(TAG,"environment" + environment + "--release环境");
            }
            return environment;
        } catch (Exception e) {
            KLog.INSTANCE.e("environment" + 2 + "--release环境e");
            return 2;
        }
    }

    public static String getChannel() {
        try {
            ApplicationInfo appInfo = getApp().getPackageManager().getApplicationInfo(getApp().getPackageName(),
                    PackageManager.GET_META_DATA);
            return appInfo.metaData.getString("CHANNEL_ID");
        } catch (Exception e) {
            return null;
        }
    }

    public static String getBaseUrl() {
        String baseUrl;
        int environment = getEnvironment();
        switch (environment) {
            case 0:
                baseUrl = "http://api.expoon1.com/";
                break;
            case 1:
                baseUrl = "http://api.expoon2.com/";
                break;
            default:
                baseUrl = "http://api.expoon.com/";
                break;
        }
        KLog.INSTANCE.e(TAG,"baseUrl=" + baseUrl);
        return baseUrl;
    }
} 
