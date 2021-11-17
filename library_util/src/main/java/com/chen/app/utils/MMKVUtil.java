package com.chen.app.utils;

import com.tencent.mmkv.MMKV;

/**
 * Author by chennan
 * Date on 2021/11/3
 * Description
 */
public class MMKVUtil {
    private static final String TAG = MMKVUtil.class.getSimpleName();

    public static void putIntValue(String key, int value){
        MMKV.defaultMMKV().encode(key, value);
    }

    public static int getIntValue(String key){
        return MMKV.defaultMMKV().decodeInt(key);
    }

    public static void putStringValue(String key, String value){
        MMKV.defaultMMKV().encode(key, value);
    }

    public static String getStringValue(String key){
        return MMKV.defaultMMKV().decodeString(key, "");
    }

    public static void putBooleanValue(String key, boolean value){
         MMKV.defaultMMKV().encode(key, value);
    }

    public static boolean getBooleanValue(String key){
        return MMKV.defaultMMKV().decodeBool(key);
    }

    public static void set(String key, Object value) {
        MMKV kv = MMKV.defaultMMKV();
        boolean result = false;
        if (value instanceof Integer) {
            result = kv.encode(key, (Integer) value);
        } else if (value instanceof Long) {
            result = kv.encode(key, (Long) value);
        } else if (value instanceof Float) {
            result = kv.encode(key, (Float) value);
        } else if (value instanceof Double) {
            result = kv.encode(key, (Double) value);
        } else if (value instanceof Boolean) {
            result = kv.encode(key, (Boolean) value);
        } else if (value instanceof String) {
            result = kv.encode(key, (String) value);
        } else if (value instanceof byte[]) {
            result = kv.encode(key, (byte[]) value);
        }
    }

    public static <T> T get(String key, T defValue) {
        MMKV kv = MMKV.defaultMMKV();
        Object result = null;
        if (defValue instanceof Integer) {
            result = kv.decodeInt(key, (Integer) defValue);
        } else if (defValue instanceof Long) {
            result = kv.decodeLong(key, (Long) defValue);
        } else if (defValue instanceof Float) {
            result = kv.decodeFloat(key, (Float) defValue);
        } else if (defValue instanceof Double) {
            result = kv.decodeDouble(key, (Double) defValue);
        } else if (defValue instanceof Boolean) {
            result = kv.decodeBool(key, (Boolean) defValue);
        } else if (defValue instanceof String) {
            result = kv.decodeString(key, (String) defValue);
        } else if (defValue instanceof byte[]) {
            result = kv.decodeBytes(key);
        }
        return (T) result;
    }
} 
