package com.chen.app.utils;

import android.content.res.Resources;

/**
 * Author by chennan
 * Date on 2022/2/28
 * Description
 */
public final class AndroidBarUtils {

    public static int getStatusBarHeight() {
        Resources resources = AppUtils.getApp().getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }

    public static int getNavBarHeight() {
        Resources res = AppUtils.getApp().getResources();
        int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId != 0) {
            return res.getDimensionPixelSize(resourceId);
        } else {
            return 0;
        }
    }
} 
