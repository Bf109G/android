package com.chen.app.utils.gson;

import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * Author by chennan
 * Date on 2021/11/3
 * Description
 */
public class NumberUtil {
    public static boolean isIntOrLong(String str){
        return TextUtils.isDigitsOnly(str);
    }

    public static boolean isFloatOrDouble(String str){
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(str).matches();
    }
} 
