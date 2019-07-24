package com.example.wbdemo;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.crashreport.CrashReport;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

/**
 * Created by zhoujunyu on 2019/5/24.
 */
public class WbApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SQLiteStudioService.instance().start(this);

        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(this);
        strategy.setAppVersion(BuildConfig.VERSION_NAME);
        strategy.setAppPackageName(BuildConfig.APPLICATION_ID);
        //CrashReport.initCrashReport(this,"4037d61985",false);


        Beta.autoCheckUpgrade = false;//设置不自动检查
        Beta.upgradeDialogLayoutId = R.layout.bugly_ui;
        Log.i("App","init success");
        //Bugly.init(getApplicationContext(), "336b7711dc", false);
        Bugly.init(getApplicationContext(), "4037d61985", false);
    }

//    private String getAPPVersionName(){
//        PackageManager manager = this.getPackageManager();
//        String name = null;
//        try{
//            PackageInfo info = manager.getPackageInfo(this.getPackageName(),0);
//            name = info.packageName;
//        }catch (PackageManager.NameNotFoundException e){
//            e.printStackTrace();
//        }
//
//        return name;
//    }
//
//    // TODO: 2019/5/29 返回报错，提示targetVersion的版本过高
//    private String getAPPVersionCode(){
//        PackageManager manager = this.getPackageManager();
//        long versionCOde = 0;
//        try{
//            PackageInfo info = manager.getPackageInfo(this.getPackageName(),0);
//            versionCOde = info.getLongVersionCode();
//        }catch (PackageManager.NameNotFoundException e){
//            e.printStackTrace();
//        }
//        return String.valueOf(versionCOde);
//    }


}
