package com.example.wbdemo;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by zhoujunyu on 2019/5/24.
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(this);
        strategy.setAppChannel("myWeibo");
        strategy.setAppVersion("0.1");
        strategy.setAppPackageName("com.example.wbdemo");
        CrashReport.initCrashReport(this,"4037d61985",false);
    }
}
