package com.chen.app.utils;

import android.view.Gravity;
import android.view.LayoutInflater;

import androidx.annotation.LayoutRes;

/**
 * Author by chennan
 * Date on 2021/11/4
 * Description
 */
public final class ToastUtil {

    private int gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
    private int xOffset = 0;
    private int yOffset =  0;

    private ToastUtil(){
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public void setGravity(int gravity, int xOffset, int yOffset){
        this.gravity = gravity;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void setView(@LayoutRes int layoutId){
//        LayoutInflater inflater =

    }
} 
