package com.example.doubanmovie;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by chenyuqiao on 2019/3/25.
 */
public class DoubanApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), C.BUGLY_ID, true);
    }
}
