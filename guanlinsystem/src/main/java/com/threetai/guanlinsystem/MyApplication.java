package com.threetai.guanlinsystem;

import android.app.Application;

import com.baidu.apistore.sdk.ApiStoreSDK;

// 请在AndroidManifest.xml中application标签下android:name中指定该类
public class MyApplication extends Application {
    
    @Override
    public void onCreate() {
        super.onCreate();
        ApiStoreSDK.init(this, "a7197b32e94c43a022778b57e1f3c724");
    }
}
