package com.chen.app.utils;

import android.annotation.SuppressLint;
import android.app.Application;

import java.lang.reflect.InvocationTargetException;

/**
 * Author by chennan
 * Date on 2022/2/25
 * Description
 */
public final class AppUtils {
//    @SuppressLint("StaticFieldLeak")
    private static Application application;


    public static Application getApp(){


        if(application == null){
            throw new NullPointerException("reflect failed");
        }
        return null;
    }

    public static void init(Application app){
        if(application == null){
            application = app;
        }
    }


    Application getApplicationByReflect(){
        try {
            Class<?> activityThread = Class.forName("android.app.ActivityThread");
            Object thread = activityThread.getMethod("currentActivityThread").invoke(null);
            Object app = activityThread.getMethod("getApplication").invoke(thread);
            if(app == null){
                throw  new NullPointerException("u should init first");
            }
            return (Application) app;
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        throw  new NullPointerException("u should init first");
    }
} 
