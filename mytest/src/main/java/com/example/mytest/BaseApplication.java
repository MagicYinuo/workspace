package com.example.mytest;

import android.app.Application;

/**
 * Created by Administrator on 2016/7/18.
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this);
    }
}
