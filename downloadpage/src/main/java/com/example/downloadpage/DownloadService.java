package com.example.downloadpage;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import static com.example.downloadpage.DownloadTask.ACTION_PROGRESS_BROADCAST;

public class DownloadService extends Service {

    private static final int PROGRESS = 0;
    private static final int PAUSE = 1;


    private DownloadTask downloadTask;
    private String downloadURL;
    private UpdateProgress updateProgress;
    private Intent mIntent_Broadcast;
    private int mProgress_Record =0;
    private int SERVICE_STATUS = -1;

    private DownloadListener listener = new DownloadListener() {
        @Override
        public void onSuccess() {
            downloadTask = null;
            Toast.makeText(DownloadService.this,"Download Success",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onFailed() {
            downloadTask = null;
            Toast.makeText(DownloadService.this,"Download Failed",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPaused() {
            downloadTask = null;
            sendProgressBroadcast(-1);
            Toast.makeText(DownloadService.this,"Paused",Toast.LENGTH_LONG).show();
            SERVICE_STATUS = PAUSE;
        }

        @Override
        public void onProgress(int progress) {
            sendProgressBroadcast(progress);
            mProgress_Record = progress;
            updateProgress.update(progress);
            SERVICE_STATUS = PROGRESS;
        }

        @Override
        public void onCanceled() {
            downloadTask = null;
            Toast.makeText(DownloadService.this,"Canceled",Toast.LENGTH_LONG).show();
            updateProgress.update(0);
        }
    };

    public DownloadService() {
    }

    public int getServiceStatus(){
        return SERVICE_STATUS;
    }

    public interface UpdateProgress{
        void update(int progress);
    }

    public void setUpdateProgress(UpdateProgress updateProgress) {
        this.updateProgress = updateProgress;

    }

    public int getProgress(){
        return mProgress_Record;
    }

    private DownloadBinder mBinder= new DownloadBinder();

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return mBinder;
    }


    public class DownloadBinder extends Binder{
        public DownloadService getService(){
            return DownloadService.this;
        }

        public void startDownload(String url){
            if(downloadTask == null){
                downloadURL = url;
                downloadTask = new DownloadTask(listener,DownloadService.this);
                downloadTask.execute(downloadURL);
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
                    Toast.makeText(DownloadService.this,"Canceled",Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void sendProgressBroadcast(int progress){
        mIntent_Broadcast = new Intent(ACTION_PROGRESS_BROADCAST);
        Bundle bundle = new Bundle();
        bundle.putInt("progress",progress);
        mIntent_Broadcast.putExtras(bundle);

        sendBroadcast(mIntent_Broadcast);
    }

    //重新绑定服务
//    @Override
//    public boolean onUnbind(Intent intent) {
//        //return super.onUnbind(intent);
//        return true;
//    }
//
//
//    @Override
//    public void onRebind(Intent intent) {
//        super.onRebind(intent);
//    }
}
