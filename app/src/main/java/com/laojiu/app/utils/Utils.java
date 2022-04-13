package com.laojiu.app.utils;

import android.content.res.Resources;
import android.text.TextUtils;
import android.widget.Toast;

import com.laojiu.app.APP;

public class Utils {
    public static Resources getAppResources() {
        return APP.mContext.getResources();
    }

    public static void showToast(int msgId) {
        String msg = getAppResources().getString(msgId);
        showToast(msg);
    }

    public static void showToast(String msg) {
        if (TextUtils.isEmpty(msg)) return;
        Toast.makeText(APP.mContext, msg, Toast.LENGTH_SHORT).show();
    }



}
