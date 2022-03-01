package com.chen.app.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;


/**
 * Author by chennan
 * Date on 2022/2/25
 * Description
 */
public final class AppUtils {
    @SuppressLint("StaticFieldLeak")
    private static Application mApplication;

    public static void init(Application application) {
        if (application == null) {
            return;
        }

        if (mApplication != null) {
            if (mApplication.equals(application)) return;
            UtilsBridge.unRegister(mApplication);
        }
        mApplication = application;
        UtilsBridge.register(mApplication);
    }

    public static Application getApp() {
        if (mApplication != null) return mApplication;
        init(UtilsBridge.getApplicationByReflect());
        if (mApplication == null) throw new NullPointerException("reflect failed");
        Log.i("AppUtils", " reflect app success");
        return mApplication;
    }

    public static void exitApp() {
        UtilsBridge.finishAllActivities();
        System.exit(0);
    }

    public static class ActivityLifecycleCallbacks {

        public void onActivityCreated(@NonNull Activity activity) {/**/}

        public void onActivityStarted(@NonNull Activity activity) {/**/}

        public void onActivityResumed(@NonNull Activity activity) {/**/}

        public void onActivityPaused(@NonNull Activity activity) {/**/}

        public void onActivityStopped(@NonNull Activity activity) {/**/}

        public void onActivityDestroyed(@NonNull Activity activity) {/**/}

        public void onLifecycleChanged(@NonNull Activity activity, Lifecycle.Event event) {/**/}
    }

    public interface OnAppStatusChangedListener {
        void onForeground(Activity activity);

        void onBackground(Activity activity);
    }
} 
