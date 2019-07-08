package com.example.downloadpage;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import java.io.File;

import androidx.core.app.NotificationCompat;

public class DownloadService extends Service {

    private DownloadTask downloadTask;
    private String downloadURL;

    private DownloadListener listener = new DownloadListener() {
        @Override
        public void onSuccess() {
            downloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("Download Success",-1));
            Toast.makeText(DownloadService.this,"Download Success",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onFailed() {
            downloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("Download Failed",-1));
            Toast.makeText(DownloadService.this,"Download Failed",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPaused() {
            downloadTask = null;
            Toast.makeText(DownloadService.this,"Paused",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1,getNotification("Downloading...",progress));
        }

        @Override
        public void onCanceled() {
            downloadTask = null;
            stopForeground(true);
            Toast.makeText(DownloadService.this,"Canceled",Toast.LENGTH_LONG).show();
        }
    };

    public DownloadService() {
    }

    private DownloadBinder mBinder= new DownloadBinder();

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return mBinder;
    }

    class DownloadBinder extends Binder{
        public void startDownload(String url){
            if(downloadTask == null){
                downloadURL = url;
                downloadTask = new DownloadTask(listener);
                downloadTask.execute(downloadURL);
                startForeground(1,getNotification("Downloading...",0));
                Toast.makeText(DownloadService.this,"Downloading...",Toast.LENGTH_LONG).show();
            }
        }

        public void pausedDownload(){
            if(downloadTask != null){
                downloadTask.PauseDownload();
            }
        }

        public void cancelDownload(){
            if(downloadTask != null){
                downloadTask.CancelDownload();
            }
            else {
                if(downloadURL != null){
                    String fileName = downloadURL.substring(downloadURL.lastIndexOf("/"));
                    String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file = new File(directory + fileName);
                    if(file.exists()){
                        file.delete();
                    }
                    getNotificationManager().cancel(1);
                    stopForeground(true);
                    Toast.makeText(DownloadService.this,"Canceled",Toast.LENGTH_LONG).show();
                }
            }
        }
    }



    private NotificationManager getNotificationManager(){
        return (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    }

    private Notification getNotification(String title,int progress){
        Intent intent = new Intent(this,SecondActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this,0,intent,0);


        NotificationManager notifyManager = (NotificationManager)getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                                            .setSmallIcon(R.mipmap.ic_launcher_round)
                                            .setContentTitle(title)
                                            .setContentIntent(pi);

        // 兼容  API 26，Android 8.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            // 第三个参数表示通知的重要程度，默认则只在通知栏闪烁一下
            NotificationChannel notificationChannel = new NotificationChannel("AppTestNotificationId", "AppTestNotificationName", NotificationManager.IMPORTANCE_HIGH);
            // 注册通道，注册后除非卸载再安装否则不改变
            notifyManager.createNotificationChannel(notificationChannel);
            builder.setChannelId("AppTestNotificationId");
            builder.setAutoCancel(true);
        }

        notifyManager.notify(11,builder.build());


        if(progress > 0){
            builder.setContentText(progress + "%");
            builder.setProgress(100,progress,false);
        }
        return builder.build();
    }
}
