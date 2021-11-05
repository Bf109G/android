package com.chen.app.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Author by chennan
 * Date on 2021/11/4
 * Description
 */
public final class AppUtil {

    private static Application mApplication;

//    private final ActivityLifeCycleIM

    private AppUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void init(final Context context) {
        if (context == null) {
            init(getApplicationByReflect());
            return;
        }
        init((Application) context.getApplicationContext());
    }

    public static void init(Application app) {
        if (mApplication == null) {
            if (app == null) {
                mApplication = getApplicationByReflect();
            } else {
                mApplication = app;
            }
//            mApplication.registerActivityLifecycleCallbacks();
        }

    }

    public static Application getApp() {
        if (mApplication != null) {
            return mApplication;
        }
        Application app = getApplicationByReflect();
        init(app);
        return app;
    }

    public static Application getApplicationByReflect() {
        try {
            @SuppressLint("PrivateApi") Class<?> activityThread = Class.forName("android.app.ActivityThread");
            try {
                Object thread = activityThread.getMethod("currentActivityThread").invoke(null);
                Object app = activityThread.getMethod("getApplication").invoke(thread);
                if (app == null) {
                    throw new NullPointerException("u should init first");
                }
                return (Application) app;
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("u should init first");
    }

    private static class ActivityLifecycleImpl implements Application.ActivityLifecycleCallbacks {

        public static final LinkedList<Activity> mActivityList = new LinkedList<>();
        final Map<Object, OnAppStatusChangedListener> mStatusListenerMap = new HashMap<>();
        final Map<Activity, Set<OnActivityDestroyListener>> mDestroyListenerMap = new HashMap<>();

        private int mForegroundCount = 0;
        private int mConfigCount = 0;
        private boolean mIsBackground = false;

        @Override
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
            setTopActivity(activity);
        }

        @Override
        public void onActivityStarted(@NonNull Activity activity) {
            if (mIsBackground) {
                setTopActivity(activity);
            }
            if (mConfigCount < 0) {
                ++mConfigCount;
            } else {
                ++mForegroundCount;
            }
        }

        @Override
        public void onActivityResumed(@NonNull Activity activity) {
            setTopActivity(activity);
            if (mIsBackground) {
                mIsBackground = false;
                postStatus(true);
            }
        }

        @Override
        public void onActivityPaused(@NonNull Activity activity) {

        }

        @Override
        public void onActivityStopped(@NonNull Activity activity) {
            if (activity.isChangingConfigurations()) {
                --mConfigCount;
            } else {
                --mForegroundCount;
                if (mForegroundCount <= 0) {
                    mIsBackground = true;
                    postStatus(false);
                }
            }
        }

        @Override
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {
            mActivityList.remove(activity);
            consumeOnActivityDestroyListener(activity);
            fixSoftInputLeaks(activity);
        }

        private void setTopActivity(final Activity activity) {
//            if (PERMISSION_ACTIVITY_CLASS_NAME.equals(activity.getClass().getName())) return;
            if (mActivityList.contains(activity)) {
                if (!mActivityList.getLast().equals(activity)) {
                    mActivityList.remove(activity);
                    mActivityList.addLast(activity);
                }
            } else {
                mActivityList.addLast(activity);
            }
        }

        private void postStatus(boolean isForeground) {
            if (!mStatusListenerMap.isEmpty()) {
                for (OnAppStatusChangedListener onAppStatusChangedListener : mStatusListenerMap.values()) {
                    if (onAppStatusChangedListener != null) {
                        if (isForeground) {
                            onAppStatusChangedListener.onForeground();
                        } else {
                            onAppStatusChangedListener.onBackground();
                        }
                    }
                }
            }
        }

        private void consumeOnActivityDestroyListener(Activity activity) {
            Iterator<Map.Entry<Activity, Set<OnActivityDestroyListener>>> iterator =
                    mDestroyListenerMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Activity, Set<OnActivityDestroyListener>> entry = iterator.next();
                if (entry.getKey() == activity) {
                    Set<OnActivityDestroyListener> value = entry.getValue();
                    for (OnActivityDestroyListener listener : value) {
                        listener.onActivityDestroyed(activity);
                    }
                    iterator.remove();
                }
            }
        }

        private static void fixSoftInputLeaks(Activity activity) {
            if (activity != null) {
                InputMethodManager imm = (InputMethodManager) AppUtil.getApp().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    String[] leakViews = new String[]{"mCurRootView", "mServedView", "mNextServedView"};
                    for (String leakView : leakViews) {
                        try {
                            Field declaredField = InputMethodManager.class.getDeclaredField(leakView);
                            if (!declaredField.isAccessible()) {
                                declaredField.setAccessible(true);
                            }
                            Object obj = declaredField.get(imm);
                            if (obj instanceof View) {
                                View view = (View) obj;
                                if (view.getRootView() == activity.getWindow().getDecorView().getRootView()) {
                                    declaredField.set(imm, null);
                                }
                            }
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public interface OnAppStatusChangedListener {
        void onForeground();

        void onBackground();
    }

    public interface OnActivityDestroyListener {
        void onActivityDestroyed(Activity activity);
    }

} 
