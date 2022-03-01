package com.chen.app.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.view.WindowManager;

import androidx.annotation.NonNull;

/**
 * Author by chennan
 * Date on 2022/2/28
 * Description
 */
public final class ScreenUtils {

    public static int getAppScreenWidth() {
        WindowManager wm = (WindowManager) AppUtils.getApp().getSystemService(Context.WINDOW_SERVICE);
        if (wm == null) return -1;
        Point point = new Point();
        wm.getDefaultDisplay().getSize(point);
        return point.x;
    }


    public static int getScreenWidth() {
        WindowManager wm = (WindowManager) AppUtils.getApp().getSystemService(Context.WINDOW_SERVICE);
        if (wm == null) return -1;
        Point point = new Point();
        wm.getDefaultDisplay().getRealSize(point);
        return point.x;
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
//            wm.getDefaultDisplay().getSize(point);
//        }
    }

    public static int getScreenHeight() {
        WindowManager vm = (WindowManager) AppUtils.getApp().getSystemService(Context.WINDOW_SERVICE);
        if (vm == null) return -1;
        Point point = new Point();
        vm.getDefaultDisplay().getRealSize(point);
        return point.y;
    }

    public static float getScreenDensity() {
        return Resources.getSystem().getDisplayMetrics().density;
    }

    public static int getScreenDensityDpi() {
        return Resources.getSystem().getDisplayMetrics().densityDpi;
    }

    public static boolean isFullScreen(@NonNull final Activity activity) {
        int fullScreenFlag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        return (activity.getWindow().getAttributes().flags & fullScreenFlag) == fullScreenFlag;
    }

    public static void setLandscape(@NonNull final Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @SuppressLint("SourceLockedOrientationActivity")
    public static void setPortrait(@NonNull final Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public static boolean isLandscape() {
        return AppUtils.getApp().getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
    }

    public static boolean isPortrait() {
        return AppUtils.getApp().getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_PORTRAIT;
    }

    public static int dp2px(float dpValue) {
        float density = getScreenDensity();
        return (int) (dpValue * density + 0.5f);
    }

    public static int px2dp(float pxValue) {
        float density = getScreenDensity();
        return (int) (pxValue / density + 0.5f);
    }

    public static int sp2px(float spValue) {
        float density = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (spValue * density + 0.5f);
    }

    public static int px2sp(float pxValue) {
        float density = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / density + 0.5f);
    }
}
