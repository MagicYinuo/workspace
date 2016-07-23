package com.example.administrator.helloas.activity.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/7/15.
 */
public class PreferenceUtils {

    private static SharedPreferences mPreferences;

    private static SharedPreferences getSp(Context context) {
        if (mPreferences == null) {
            mPreferences = context.getSharedPreferences("HelloAs", Context.MODE_PRIVATE);
        }
        return mPreferences;
    }

    /**
     * 获得boolean类型的值默认为false
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }

    /**
     * 获得boolean类型的数据
     *
     * @param context
     * @param key
     * @param b
     * @return
     */
    public static boolean getBoolean(Context context, String key, boolean defValue) {
        SharedPreferences sp = getSp(context);
        return sp.getBoolean(key, defValue);
    }


    /**
     * 设置boolean类型的值
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = getSp(context);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(key, value);
        edit.commit();

    }

    /**
     * 获取字符类型的值 默认为空
     *
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        return getString(context, key, null);
    }

    /**
     * 获取string类型的数据
     *
     * @param context
     * @param key
     * @param o
     * @return
     */
    public static String getString(Context context, String key, String defValue) {
        SharedPreferences sp = getSp(context);
        return sp.getString(key, defValue);
    }


    /**
     * 设置String类型的数据
     *
     * @param context
     * @param key
     * @param value
     * @return
     */
    public static void putString(Context context, String key, String value) {
        SharedPreferences sp = getSp(context);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, value);
        edit.commit();
    }


    /**
     * 获取int类型的数据没有默认-1
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static int getInt(Context context, String key) {
        return getInt(context, key, -1);
    }

    /**
     * 获取int类型的数据
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static int getInt(Context context, String key, int defValue) {
        SharedPreferences sp = getSp(context);
        return sp.getInt(key, defValue);
    }


    /**
     * 设置int类型的数据
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putInt(Context context, String key, int value) {
        SharedPreferences sp = getSp(context);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(key, value);
        edit.commit();

    }


}
