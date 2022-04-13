package com.laojiu.app.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.laojiu.app.APP;

/**
 * Created by zhouda on 2018/2/23.
 * sp工具类
 */

public class SpUtils {
    private static Context mContext;
    private static SharedPreferences mAppSp;

    public static SharedPreferences getSp() {
        if (mContext == null) mContext = APP.mContext;
        mAppSp = mContext.getSharedPreferences("app", Context.MODE_PRIVATE);
        return mAppSp;
    }

    public static String getString(String key) {
        if (mAppSp == null) mAppSp = getSp();
        if (TextUtils.isEmpty(key)) return "";
        return mAppSp.getString(key, "");
    }

    public static boolean putString(String key, String msg) {
        if (mAppSp == null) mAppSp = getSp();
        if (TextUtils.isEmpty(key)) return false;
        SharedPreferences.Editor edit = mAppSp.edit();
        edit.putString(key, msg);
        return edit.commit();
    }

    public static int getInt(String key) {
        if (mAppSp == null) mAppSp = getSp();
        if (TextUtils.isEmpty(key)) return -1;
        return mAppSp.getInt(key, -1);
    }

    public static boolean putInt(String key, int msg) {
        if (mAppSp == null) mAppSp = getSp();
        if (TextUtils.isEmpty(key)) return false;
        SharedPreferences.Editor edit = mAppSp.edit();
        edit.putInt(key, msg);
        return edit.commit();
    }

    public static boolean getBoolean(String key) {
        if (mAppSp == null) mAppSp = getSp();
        if (TextUtils.isEmpty(key)) return false;
        return mAppSp.getBoolean(key, false);
    }

    public static boolean putBoolean(String key, boolean msg) {
        if (mAppSp == null) mAppSp = getSp();
        if (TextUtils.isEmpty(key)) return false;
        SharedPreferences.Editor edit = mAppSp.edit();
        edit.putBoolean(key, msg);
        return edit.commit();
    }

}
