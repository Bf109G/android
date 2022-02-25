package com.chen.app.utils;

import android.view.View;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Author by chennan
 * Date on 2022/2/25
 * Description
 */
public final class ToastUtil {

    private int gravity = -1;
    private int xOffset = -1;
    private int yOffset = -1;
    private int bgColor = 0xFEFFFFFF;
    private int textColor = 0xFEFFFFFF;
    private int textSize = -1;
    private boolean isLong = false;

    public ToastUtil setGravity(int gravity, int xOffset, int yOffset) {
        this.gravity = gravity;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        return this;
    }

    public ToastUtil setBgColor(int color) {
        bgColor = color;
        return this;
    }

    public ToastUtil setTextColor(int color) {
        textColor = color;
        return this;
    }

    public ToastUtil setTextSize(int size) {
        textSize = size;
        return this;
    }

    public ToastUtil setDurationIsLong(boolean isLong) {
        this.isLong = isLong;
        return this;
    }

    private int getDuration() {
        return isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;
    }

    public void show(CharSequence text) {
        show(text, getDuration(), this);
    }

    public void show(@NotNull View view){
        show(view, getDuration(), this);
    }

    private static void show(@Nullable CharSequence text, int duration, ToastUtil util) {
        show(null, text, duration, util);
    }

    private static void show(@NotNull View view, int duration, ToastUtil util){

    }

    private static void show(@Nullable View view, @Nullable CharSequence text, int duration, ToastUtil util){
        ThreadUtil.runOnUiThread(new Runnable() {
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

    abstract class AbsToast implements IToast{
        protected Toast toast;

        protected ToastUtil toastUtil;

        protected View toastView;

        AbsToast(ToastUtil toastUtil){
//            toast = new Toast();
            this.toastUtil = toastUtil;

        }

        @Override
        public void setToastView(View view) {

        }

        @Override
        public void setToastView(CharSequence text) {

        }

        @Override
        public void show(int duration) {

        }

        @Override
        public void cancel() {

        }
    }
} 
