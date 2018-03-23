package com.jarhead.common.baseapp;

import android.app.Application;

import com.umeng.analytics.MobclickAgent;


/**
 * Created by jiming1 on 2016/10/31.
 */
public class BaseApplication extends Application {

    private static BaseApplication baseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        MobclickAgent.setDebugMode(true);
    }

    public static BaseApplication getBaseApplication() {
        return baseApplication;
    }
}
