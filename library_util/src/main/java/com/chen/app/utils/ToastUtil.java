package com.chen.app.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringDef;

/**
 * Author by chennan
 * Date on 2022/2/25
 * Description
 */
public final class ToastUtil {

    @StringDef({MODE.LIGHT, MODE.DARK})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MODE {
        String LIGHT = "light";
        String DARK = "dark";
    }

    private static final String TAG_TOAST = "TAG_TOAST";
    private static final int COLOR_DEFAULT = 0xFEFFFFFF;
    private static WeakReference<IToast> sWeakToast;

    private String mMode;
    private int mBgResource = -1;
    private int mGravity = -1;
    private int mXOffset = -1;
    private int mYOffset = -1;
    private int mBgColor = COLOR_DEFAULT;
    private int mTextColor = COLOR_DEFAULT;
    private int mTextSize = -1;

    private boolean isLong = false;

    public static ToastUtil make() {
        return new ToastUtil();
    }

    public ToastUtil setMode(String mode) {
        mMode = mode;
        return this;
    }

    public ToastUtil setGravity(int gravity, int xOffset, int yOffset) {
        mGravity = gravity;
        mXOffset = xOffset;
        mYOffset = yOffset;
        return this;
    }

    public ToastUtil setBgColor(int color) {
        mBgColor = color;
        return this;
    }

    public ToastUtil setTextColor(int color) {
        mTextColor = color;
        return this;
    }

    public ToastUtil setTextSize(int size) {
        mTextSize = size;
        return this;
    }

    public ToastUtil setDurationIsLong(boolean isLong) {
        this.isLong = isLong;
        return this;
    }

    private int getDuration() {
        return isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;
    }

    private View tryApplyUtilsToastView(final CharSequence text) {
        if (!MODE.DARK.equals(mMode) && !MODE.LIGHT.equals(mMode)) {
//                && mIcons[0] == null && mIcons[1] == null && mIcons[2] == null && mIcons[3] == null) {
            return null;
        }

        View toastView = ViewUtils.layoutId2View(R.layout.toast_view);
        TextView messageTv = toastView.findViewById(android.R.id.message);
        if (MODE.DARK.equals(mMode)) {
            GradientDrawable bg = (GradientDrawable) toastView.getBackground().mutate();
            bg.setColor(Color.parseColor("#BB000000"));
            messageTv.setTextColor(Color.WHITE);
        }
        messageTv.setText(text);
//        if (mIcons[0] != null) {
//            View leftIconView = toastView.findViewById(R.id.utvLeftIconView);
//            ViewCompat.setBackground(leftIconView, mIcons[0]);
//            leftIconView.setVisibility(View.VISIBLE);
//        }
//        if (mIcons[1] != null) {
//            View topIconView = toastView.findViewById(R.id.utvTopIconView);
//            ViewCompat.setBackground(topIconView, mIcons[1]);
//            topIconView.setVisibility(View.VISIBLE);
//        }
//        if (mIcons[2] != null) {
//            View rightIconView = toastView.findViewById(R.id.utvRightIconView);
//            ViewCompat.setBackground(rightIconView, mIcons[2]);
//            rightIconView.setVisibility(View.VISIBLE);
//        }
//        if (mIcons[3] != null) {
//            View bottomIconView = toastView.findViewById(R.id.utvBottomIconView);
//            ViewCompat.setBackground(bottomIconView, mIcons[3]);
//            bottomIconView.setVisibility(View.VISIBLE);
//        }
        return toastView;
    }

    public void show(CharSequence text) {
        show(text, getDuration(), this);
    }

    public void show(@NonNull View view) {
        show(view, getDuration(), this);
    }

    private static void show(@Nullable CharSequence text, int duration, ToastUtil util) {
        show(null, text, duration, util);
    }

    private static void show(@NonNull View view, int duration, ToastUtil util) {

    }

    private static void show(@Nullable View view, @Nullable CharSequence text, int duration, ToastUtil util) {
        ThreadUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    interface IToast {
        void setToastView(View view);

        void setToastView(CharSequence text);

        void show(int duration);

        void cancel();
    }

    static final class ActivityToast extends AbsToast {
        private static int sShowingIndex = 0;
        private IToast iToast;
        private AppUtils.ActivityLifecycleCallbacks mActivityLifecycleCallbacks;

        ActivityToast(ToastUtil toastUtil) {
            super(toastUtil);
        }

        @Override
        public void show(int duration) {
            if (mToast == null) return;
            if (!UtilsBridge.isAppForeground()) {
                // try to use system toast
//                iToast = showSystemToast(duration);
                return;
            }
            boolean hasAliveActivity = false;
            for (final Activity activity : UtilsBridge.getActivityList()) {
                if (!UtilsBridge.isActivityAlive(activity)) {
                    continue;
                }
                if (!hasAliveActivity) {
                    hasAliveActivity = true;
//                    iToast = showWithActivityWindow(activity, duration);
                } else {
                    showWithActivityView(activity, sShowingIndex, true);
                }
            }

            if (hasAliveActivity) {
                registerLifecycleCallback();
                UtilsBridge.runOnUiThreadDelayed(new Runnable() {
                    @Override
                    public void run() {
                        cancel();
                    }
                }, duration == Toast.LENGTH_SHORT ? 2000 : 3500);

                ++sShowingIndex;
            } else {
//                iToast = showSystemToast(duration);
            }
        }

        @Override
        public void cancel() {
            if (isShowing()) {
                unregisterLifecycleCallback();
                for (Activity activity : UtilsBridge.getActivityList()) {
                    if (!UtilsBridge.isActivityAlive(activity)) {
                        continue;
                    }
                    final Window window = activity.getWindow();
                    if (window != null) {
                        ViewGroup decorView = (ViewGroup) window.getDecorView();
                        View toastView = decorView.findViewWithTag(TAG_TOAST + (sShowingIndex - 1));
                        if (toastView != null) {
                            try {
                                decorView.removeView(toastView);
                            } catch (Exception ignored) {/**/}
                        }
                    }
                }
            }
            if (iToast != null) {
                iToast.cancel();
                iToast = null;
            }
            super.cancel();
        }


        private boolean isShowing() {
            return mActivityLifecycleCallbacks != null;
        }


        private IToast showSystemToast(int duration) {
            SystemToast systemToast = new SystemToast(mToastUtils);
            systemToast.mToast = mToast;
            systemToast.show(duration);
            return systemToast;
        }

        private IToast showWithActivityWindow(Activity activity, int duration) {
            WindowManagerToast wmToast = new WindowManagerToast(mToastUtils, activity.getWindowManager(), WindowManager.LayoutParams.LAST_APPLICATION_WINDOW);
            wmToast.mToastView = getToastViewSnapshot(-1);
            wmToast.mToast = mToast;
            wmToast.show(duration);
            return wmToast;
        }

        private void showWithActivityView(final Activity activity, final int index, boolean useAnim) {
            final Window window = activity.getWindow();
            if (window != null) {
                final ViewGroup decorView = (ViewGroup) window.getDecorView();
                FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
                );
                lp.gravity = mToast.getGravity();
                lp.bottomMargin = mToast.getYOffset() + UtilsBridge.getNavBarHeight();
                lp.topMargin = mToast.getYOffset() + UtilsBridge.getStatusBarHeight();
                lp.leftMargin = mToast.getXOffset();
                View toastViewSnapshot = getToastViewSnapshot(index);
                if (useAnim) {
                    toastViewSnapshot.setAlpha(0);
                    toastViewSnapshot.animate().alpha(1).setDuration(200).start();
                }
                decorView.addView(toastViewSnapshot, lp);
            }
        }

        private void registerLifecycleCallback() {
            final int index = sShowingIndex;
            mActivityLifecycleCallbacks = new AppUtils.ActivityLifecycleCallbacks() {
                @Override
                public void onActivityCreated(@NonNull Activity activity) {
                    if (isShowing()) {
                        showWithActivityView(activity, index, false);
                    }
                }
            };
            UtilsBridge.addActivityLifecycleCallbacks(mActivityLifecycleCallbacks);
        }

        private void unregisterLifecycleCallback() {
            UtilsBridge.removeActivityLifecycleCallbacks(mActivityLifecycleCallbacks);
            mActivityLifecycleCallbacks = null;
        }
    }

    static abstract class AbsToast implements IToast {
        protected Toast mToast;

        protected ToastUtil mToastUtil;

        protected View mToastView;

        AbsToast(ToastUtil toastUtil) {
            mToast = new Toast(AppUtils.getApp());
            mToastUtil = toastUtil;

            if (mToastUtil.mGravity != -1 || mToastUtil.mXOffset != -1 || mToastUtil.mYOffset != -1) {
                mToast.setGravity(mToastUtil.mGravity, mToastUtil.mXOffset, mToastUtil.mYOffset);
            }
        }

        @Override
        public void setToastView(View view) {
            mToastView = view;
            mToast.setView(mToastView);
        }

        @Override
        public void setToastView(CharSequence text) {
            View view = mToastUtil.tryApplyUtilsToastView(text);
            if (view != null) {
                setToastView(view);
                return;
            }

            mToastView = mToast.getView();
            if (mToastView == null || mToastView.findViewById(android.R.id.message) == null) {
                setToastView(UtilsBridge.layoutId2View(R.layout.toast_view));
            }

            TextView msgTv = mToastView.findViewById(android.R.id.message);
            msgTv.setText(text);

            if (mToastUtil.mTextColor != COLOR_DEFAULT) {
                msgTv.setTextColor(mToastUtil.mTextColor);
            }

            if (mToastUtil.mTextSize != -1) {
                msgTv.setTextSize(mToastUtil.mTextSize);
            }
            setBg(msgTv);
        }

        @Override
        public void cancel() {
            if (mToast != null) {
                mToast.cancel();
            }
            mToast = null;
            mToastView = null;
        }

        View getToastViewSnapshot(final int index) {
            Bitmap bitmap = UtilsBridge.view2Bitmap(mToastView);
            ImageView toastIv = new ImageView(AppUtils.getApp());
            toastIv.setTag(TAG_TOAST + index);
            toastIv.setImageBitmap(bitmap);
            return toastIv;
        }

        private void setBg(final TextView msgTv) {
            if (mToastUtil.mBgResource != -1) {
                mToastView.setBackgroundResource(mToastUtil.mBgResource);
                msgTv.setBackgroundColor(Color.TRANSPARENT);
            } else if (mToastUtil.mBgColor != COLOR_DEFAULT) {
                Drawable toastBg = mToastView.getBackground();
                Drawable msgBg = msgTv.getBackground();
                if (toastBg != null && msgBg != null) {
                    toastBg.mutate().setColorFilter(new PorterDuffColorFilter(mToastUtil.mBgColor, PorterDuff.Mode.SRC_IN));
                    msgTv.setBackgroundColor(Color.TRANSPARENT);
                } else if (toastBg != null) {
                    toastBg.mutate().setColorFilter(new PorterDuffColorFilter(mToastUtil.mBgColor, PorterDuff.Mode.SRC_IN));
                } else if (msgBg != null) {
                    msgBg.mutate().setColorFilter(new PorterDuffColorFilter(mToastUtil.mBgColor, PorterDuff.Mode.SRC_IN));
                } else {
                    mToastView.setBackgroundColor(mToastUtil.mBgColor);
                }
            }
        }
    }

    public static final class UtilsMaxWidthRelativeLayout extends RelativeLayout {

        private static final int SPACING = UtilsBridge.dp2px(80);

        public UtilsMaxWidthRelativeLayout(Context context) {
            super(context);
        }

        public UtilsMaxWidthRelativeLayout(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public UtilsMaxWidthRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int widthMaxSpec = MeasureSpec.makeMeasureSpec(ScreenUtils.getAppScreenWidth() - SPACING, MeasureSpec.AT_MOST);
            super.onMeasure(widthMaxSpec, heightMeasureSpec);
        }
    }
} 
