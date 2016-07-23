package com.example.mytest;

import android.content.Context;
import android.util.Log;

/**
 * Created by Administrator on 2016/7/18.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static CrashHandler INSTANCE = new CrashHandler();
    private Thread.UncaughtExceptionHandler mDefaultUEH;
    private Context mContext;

    public CrashHandler() {
        mDefaultUEH = Thread.getDefaultUncaughtExceptionHandler();
    }

    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    public void init(Context context) {
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = context;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        Log.e("CrashHandler", throwable.getMessage(), throwable);
        mDefaultUEH.uncaughtException(thread, throwable);
    }
}
