package com.easychange.admin.smallrain;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2017/8/24.
 */

public class MyApplication extends Application {
    private static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

    }
    public static Context getGloableContext() {
        return application.getApplicationContext();
    }
}

