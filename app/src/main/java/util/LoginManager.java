package util;

import android.content.Context;

import com.chen.app.R;
import com.chen.app.utils.ColorUtils;
import com.chen.app.utils.UtilsBridge;

import androidx.core.content.ContextCompat;

/**
 * Author by chennan
 * Date on 2022/3/1
 * Description
 */
public class LoginManager {
    private static LoginManager mInstance;
    private Context mContext;

    private LoginManager(Context context) {
        this.mContext = context;
    }

    public static LoginManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (LoginManager.class) {
                mInstance = new LoginManager(context);
            }
        }

        return mInstance;
    }

    public void get(){
        ContextCompat.getColor(mContext, R.color.purple_200);
    }
} 
