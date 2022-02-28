package com.chen.app.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;

import java.lang.reflect.Field;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Author by chennan
 * Date on 2022/2/28
 * Description
 */
public final class KeyboardUtils {

    private KeyboardUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void showSoftInput() {
        InputMethodManager imm =
                (InputMethodManager) AppUtils.getApp().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public static void showSoftInput(@Nullable Activity activity) {
        if (activity == null) {
            return;
        }
        if (!isSoftInputVisible(activity)) {
            toggleSoftInput();
        }
    }

    public static void showSoftInput(@NonNull final View view) {
        showSoftInput(view, 0);
    }

    public static void showSoftInput(@NonNull final View view, final int flags) {
        InputMethodManager imm =
                (InputMethodManager) AppUtils.getApp().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        imm.showSoftInput(view, flags, new ResultReceiver(new Handler()) {
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                if (resultCode == InputMethodManager.RESULT_UNCHANGED_HIDDEN
                        || resultCode == InputMethodManager.RESULT_HIDDEN) {
                    toggleSoftInput();
                }
            }
        });
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public static void hideSoftInput(@Nullable final Activity activity) {
        if (activity == null) {
            return;
        }
        hideSoftInput(activity.getWindow());
    }

    public static void hideSoftInput(@Nullable final Window window) {
        if (window == null) {
            return;
        }
        View view = window.getCurrentFocus();
        if (view == null) {
            View decorView = window.getDecorView();
            View focusView = decorView.findViewWithTag("keyboardTagView");
            if (focusView == null) {
                view = new EditText(window.getContext());
                view.setTag("keyboardTagView");
                ((ViewGroup) decorView).addView(view, 0, 0);
            } else {
                view = focusView;
            }
            view.requestFocus();
        }
        hideSoftInput(view);
    }

    public static void hideSoftInput(@NonNull final View view) {
        InputMethodManager imm =
                (InputMethodManager) AppUtils.getApp().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void toggleSoftInput() {
        InputMethodManager imm =
                (InputMethodManager) AppUtils.getApp().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }
        imm.toggleSoftInput(0, 0);
    }

    public static boolean isSoftInputVisible(@NonNull final Activity activity) {
        return getDecorViewInvisibleHeight(activity.getWindow()) > 0;
    }

    private static int sDecorViewDelta = 0;

    private static int getDecorViewInvisibleHeight(@NonNull final Window window) {
        final View decorView = window.getDecorView();
        final Rect outRect = new Rect();
        decorView.getWindowVisibleDisplayFrame(outRect);
        Log.d("KeyboardUtils",
                "getDecorViewInvisibleHeight: " + (decorView.getBottom() - outRect.bottom));
        int delta = Math.abs(decorView.getBottom() - outRect.bottom);
        if (delta <= UtilsBridge.getNavBarHeight() + UtilsBridge.getStatusBarHeight()) {
            sDecorViewDelta = delta;
            return 0;
        }
        return delta - sDecorViewDelta;
    }

    public static void fixSoftInputLeaks(@NonNull final Activity activity) {
        fixSoftInputLeaks(activity.getWindow());
    }

    public static void fixSoftInputLeaks(@NonNull final Window window) {
        InputMethodManager imm =
                (InputMethodManager) AppUtils.getApp().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }
        String[] leakViews =
                new String[] { "mLastSrvView", "mCurRootView", "mServedView", "mNextServedView" };
        for (String leakView : leakViews) {
            try {
                Field leakViewField = InputMethodManager.class.getDeclaredField(leakView);
                if (!leakViewField.isAccessible()) {
                    leakViewField.setAccessible(true);
                }
                Object obj = leakViewField.get(imm);
                if (!(obj instanceof View)) {
                    continue;
                }
                View view = (View) obj;
                if (view.getRootView() == window.getDecorView().getRootView()) {
                    leakViewField.set(imm, null);
                }
            } catch (Throwable ignore) {/**/}
        }
    }
} 
