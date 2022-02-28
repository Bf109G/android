package com.chen.app.utils;

import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;

import java.io.File;
import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;


/**
 * Author by chennan
 * Date on 2022/2/28
 * Description
 */
public class UtilsBridge {

    //start
    static void register(Application application) {
        UtilsActivityLifecycleImpl.INSTANCE.register(application);
    }

    static void unRegister(Application application) {
        UtilsActivityLifecycleImpl.INSTANCE.unRegister(application);
    }

    static Application getApplicationByReflect() {
        return UtilsActivityLifecycleImpl.INSTANCE.getApplicationByReflect();
    }

    static void addOnAppStatusChangedListener(final AppUtils.OnAppStatusChangedListener listener) {
        UtilsActivityLifecycleImpl.INSTANCE.addOnAppStatusChangedListener(listener);
    }

    static void removeOnAppStatusChangedListener(final AppUtils.OnAppStatusChangedListener listener) {
        UtilsActivityLifecycleImpl.INSTANCE.removeOnAppStatusChangedListener(listener);
    }

    static void addActivityLifecycleCallbacks(final AppUtils.ActivityLifecycleCallbacks callbacks) {
        UtilsActivityLifecycleImpl.INSTANCE.addActivityLifecycleCallbacks(callbacks);
    }

    static void removeActivityLifecycleCallbacks(final AppUtils.ActivityLifecycleCallbacks callbacks) {
        UtilsActivityLifecycleImpl.INSTANCE.removeActivityLifecycleCallbacks(callbacks);
    }

    static void addActivityLifecycleCallbacks(final Activity activity,
                                              final AppUtils.ActivityLifecycleCallbacks callbacks) {
        UtilsActivityLifecycleImpl.INSTANCE.addActivityLifecycleCallbacks(activity, callbacks);
    }

    static void removeActivityLifecycleCallbacks(final Activity activity) {
        UtilsActivityLifecycleImpl.INSTANCE.removeActivityLifecycleCallbacks(activity);
    }

    static void removeActivityLifecycleCallbacks(final Activity activity,
                                                 final AppUtils.ActivityLifecycleCallbacks callbacks) {
        UtilsActivityLifecycleImpl.INSTANCE.removeActivityLifecycleCallbacks(activity, callbacks);
    }

    static List<Activity> getActivityList() {
        return UtilsActivityLifecycleImpl.INSTANCE.getActivityList();
    }

    static boolean isAppForeground() {
        return UtilsActivityLifecycleImpl.INSTANCE.isAppForeground();
    }
    //end

    // ActivityUtils start
    static boolean isActivityAlive(final Activity activity) {
        return ActivityUtils.isActivityAlive(activity);
    }
    // ActivityUtils end

    // AndroidBarUtils start
    static int getStatusBarHeight() {
        return AndroidBarUtils.getStatusBarHeight();
    }

    static int getNavBarHeight() {
        return AndroidBarUtils.getNavBarHeight();
    }
    // AndroidBarUtil end

    // FileUtils start
    static boolean isFileExists(final File file) {
        return FileUtils.isFileExists(file);
    }

    static File getFileByPath(final String filePath) {
        return FileUtils.getFileByPath(filePath);
    }

    static boolean deleteAllInDir(final File dir) {
        return FileUtils.deleteAllInDir(dir);
    }

    static boolean createOrExistsFile(final File file) {
        return FileUtils.createOrExistsFile(file);
    }

    static boolean createOrExistsDir(final File file) {
        return FileUtils.createOrExistsDir(file);
    }

    static boolean createFileByDeleteOldFile(final File file) {
        return FileUtils.createFileByDeleteOldFile(file);
    }

    static long getFsTotalSize(String path) {
        return FileUtils.getFsTotalSize(path);
    }

    static long getFsAvailableSize(String path) {
        return FileUtils.getFsAvailableSize(path);
    }

    static void notifySystemToScan(File file) {
        FileUtils.notifySystemToScan(file);
    }
    // FileUtils end

    // ImageUtils start
    static byte[] bitmap2Bytes(final Bitmap bitmap) {
        return ImageUtils.bitmap2Bytes(bitmap);
    }

    static byte[] bitmap2Bytes(final Bitmap bitmap, final Bitmap.CompressFormat format, int quality) {
        return ImageUtils.bitmap2Bytes(bitmap, format, quality);
    }

    static Bitmap bytes2Bitmap(final byte[] bytes) {
        return ImageUtils.bytes2Bitmap(bytes);
    }

    static byte[] drawable2Bytes(final Drawable drawable) {
        return ImageUtils.drawable2Bytes(drawable);
    }

    static byte[] drawable2Bytes(final Drawable drawable, final Bitmap.CompressFormat format, int quality) {
        return ImageUtils.drawable2Bytes(drawable, format, quality);
    }

    static Drawable bytes2Drawable(final byte[] bytes) {
        return ImageUtils.bytes2Drawable(bytes);
    }

    static Bitmap view2Bitmap(final View view) {
        return ImageUtils.view2Bitmap(view);
    }

    static Bitmap drawable2Bitmap(final Drawable drawable) {
        return ImageUtils.drawable2Bitmap(drawable);
    }

    static Drawable bitmap2Drawable(final Bitmap bitmap) {
        return ImageUtils.bitmap2Drawable(bitmap);
    }
    // ImageUtils end

    // KeyboardUtils start
    static void fixSoftInputLeaks(final Activity activity) {
        KeyboardUtils.fixSoftInputLeaks(activity);
    }
    // KeyboardUtils end

    // ScreenUtils start
    static int getScreenWidth() {
        return ScreenUtils.getAppScreenWidth();
    }

    static int dp2px(final float dpValue) {
        return ScreenUtils.dp2px(dpValue);
    }

    static int px2dp(final float pxValue) {
        return ScreenUtils.px2dp(pxValue);
    }

    static int sp2px(final float spValue) {
        return ScreenUtils.sp2px(spValue);
    }

    static int px2sp(final float pxValue) {
        return ScreenUtils.px2sp(pxValue);
    }
    // ScreenUtils end

    // StringUtils start
    static boolean isSpace(final String s) {
        return StringUtils.isSpace(s);
    }

    static boolean equals(final CharSequence s1, final CharSequence s2) {
        return StringUtils.equals(s1, s2);
    }

    static String getString(@StringRes int id) {
        return StringUtils.getString(id);
    }

    static String getString(@StringRes int id, Object... formatArgs) {
        return StringUtils.getString(id, formatArgs);
    }

    static String format(@Nullable String str, Object... args) {
        return StringUtils.format(str, args);
    }

    static String bytes2HexString(final byte[] bytes) {
        return StringUtils.bytes2HexString(bytes);
    }
    // StringUtils end

    // ThreadUtils start
    static void runOnUiThread(final Runnable runnable) {
        ThreadUtils.runOnUiThread(runnable);
    }

    static void runOnUiThreadDelayed(Runnable runnable, long delayMillis) {
        ThreadUtils.runOnUiThreadDelayed(runnable, delayMillis);
    }
    // ThreadUtils end

    // UriUtils start
    static Uri file2Uri(final File file) {
        return UriUtils.file2Uri(file);
    }

    static File uri2File(final Uri uri) {
        return UriUtils.uri2File(uri);
    }
    // UriUtils end

    //ViewUtils start
    static View layoutId2View(@LayoutRes int layoutId) {
        return ViewUtils.layoutId2View(layoutId);
    }

    static boolean isLayoutRtl() {
        return ViewUtils.isLayoutRtl();
    }
    //ViewUtils end
} 
