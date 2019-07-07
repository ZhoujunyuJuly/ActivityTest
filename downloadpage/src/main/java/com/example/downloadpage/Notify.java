package com.example.downloadpage;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import static androidx.core.content.ContextCompat.getSystemService;
import static androidx.core.content.ContextCompat.startForegroundService;


/**
 * Created by zhoujunyu on 2019/7/5.
 */
public class Notify {

    public Notify() {
        super();
    }

    public static void DownloadAlarm(Context mContext) {

        NotificationManager notifyManager = (NotificationManager)mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("下载任务")
                .setContentText("正在下载");

        // 兼容  API 26，Android 8.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            // 第三个参数表示通知的重要程度，默认则只在通知栏闪烁一下
            NotificationChannel notificationChannel = new NotificationChannel("AppTestNotificationId", "AppTestNotificationName", NotificationManager.IMPORTANCE_HIGH);
            // 注册通道，注册后除非卸载再安装否则不改变
            notifyManager.createNotificationChannel(notificationChannel);
            builder.setChannelId("AppTestNotificationId");
            builder.setAutoCancel(true);
        }


        notifyManager.notify(10,builder.build());


    }
}
