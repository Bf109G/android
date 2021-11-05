package com.chen.app.net.cookie;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import com.chen.app.net.utils.MetaDataUtil;

/**
 * Author by chennan
 * Date on 2021/11/3
 * Description
 */
public class CookieUtil {
    private final String KCookieDel = "deleteMe";
    public static final String KJsessionidCookie = "JSESSIONID_COOKIE";
    private Context mContext;
    private String mCookies = "";
    private static CookieUtil mCookieUtils = null;

    public CookieUtil(Context context) {
        this.mContext = context;
    }

    public static synchronized CookieUtil getInstance(Context context) {
        if (mCookieUtils == null) {
            mCookieUtils = new CookieUtil(context);
        }
        return mCookieUtils;
    }

    /**
     * 保存cookie
     * * @author
     */
    public void saveCookie(String cookie) {
        if (TextUtils.isEmpty(cookie) || KCookieDel.equals(cookie)) {
            return;
        }
        String cookieStr = KJsessionidCookie + "=" + cookie;
        if (cookieStr.equals(mCookies)) {
            return;
        }
        mCookies = cookieStr;
    }

    /**
     * 同步一下webview cookie
     *
     * @author
     */
    public void synCookies(String url) {
        if (TextUtils.isEmpty(mCookies) || KCookieDel.equals(mCookies)) {
            return;
        }
        CookieSyncManager.createInstance(mContext);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cookieManager.setCookie(MetaDataUtil.getBaseUrl(), mCookies);
        CookieSyncManager.getInstance().sync();
    }

    public String getCookie() {
        return mCookies;
    }
} 
