package com.chen.app.utils;

import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;

/**
 * Author by chennan
 * Date on 2022/3/1
 * Description
 */
public final class ColorUtils {

    private ColorUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static int getColor(@ColorRes int id) {
        return ContextCompat.getColor(AppUtils.getApp(), id);
    }
} 
