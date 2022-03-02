package com.chen.app.oom;

import android.content.Context;

/**
 * Author by chennan
 * Date on 2022/3/2
 * Description
 */
public class LoginManager {

    private static LoginManager mInstance;
    private Context mContext;

    private LoginManager(Context context){
//        this.mContext = context;
        //解决
        this.mContext = context.getApplicationContext();
    }

    public static LoginManager getInstance(Context context){
        if(mInstance == null){
            synchronized (LoginManager.class){
                mInstance = new LoginManager(context);
            }
        }
        return mInstance;
    }
} 
