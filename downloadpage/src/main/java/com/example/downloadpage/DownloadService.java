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
    private UpdateProgress updateProgress;

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
            Toast.makeText(DownloadService.this,"Paused",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onProgress(int progress) {
            updateProgress.update(progress);
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

    public interface UpdateProgress{
        void update(int progress);
    }

    public void setUpdateProgress(UpdateProgress updateProgress) {
        this.updateProgress = updateProgress;
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
                downloadTask = new DownloadTask(listener);
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

}
