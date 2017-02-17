package com.ppcrong.testapp;

import android.app.Application;

import com.socks.library.KLog;

public class TestApp extends Application {

    public static final String LOG_TAG = "TestAppDebug";
    @Override
    public void onCreate() {
        super.onCreate();
        KLog.init(BuildConfig.LOG_DEBUG, LOG_TAG); // Unify global debug flag
    }
}
