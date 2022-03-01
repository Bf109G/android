package com.chen.app.utils;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import java.util.Locale;

import androidx.annotation.LayoutRes;

/**
 * Author by chennan
 * Date on 2022/2/28
 * Description
 */
public final class ViewUtils {
    public static View layoutId2View(@LayoutRes int layoutId) {
        LayoutInflater inflater = (LayoutInflater) AppUtils.getApp().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(layoutId, null);
    }

    public static boolean isLayoutRtl(){
        Locale locale;
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.N){
            locale = AppUtils.getApp().getResources().getConfiguration().getLocales().get(0);
        } else{
            locale = AppUtils.getApp().getResources().getConfiguration().locale;
        }
        return TextUtils.getLayoutDirectionFromLocale(locale) == View.LAYOUT_DIRECTION_RTL;
    }
} 
