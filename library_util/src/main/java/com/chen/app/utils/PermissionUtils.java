package com.chen.app.utils;

import android.os.Build;
import android.provider.Settings;

import androidx.annotation.RequiresApi;

/**
 * Author by chennan
 * Date on 2022/2/28
 * Description
 */
public final class PermissionUtils {

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static boolean isGrantedDrawOverlays() {
        return Settings.canDrawOverlays(AppUtils.getApp());
    }
} 
