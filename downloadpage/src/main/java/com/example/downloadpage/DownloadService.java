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
import android.provider.ContactsContract;
import android.util.ArrayMap;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import static android.os.AsyncTask.THREAD_POOL_EXECUTOR;
import static com.example.downloadpage.DownloadTask.ACTION_PROGRESS_BROADCAST;

public class DownloadService extends Service {

    private static final int PROGRESS = 0;
    private static final int PAUSE = 1;


    private String downloadURL;
    private UpdateProgress updateProgress;
    private Intent mIntent_Broadcast;
    private int mProgress_Record =0;
    private int SERVICE_STATUS = -1;

    private List<DownloadTask> mTasks = new ArrayList<>();
    private Map<Integer,String> mInfo =new HashMap<>();



    private DownloadListener listener = new DownloadListener() {
        @Override
        public void onSuccess(int position) {
            mTasks.remove(position);
            Toast.makeText(DownloadService.this,"Download Success",Toast.LENGTH_LONG).show();
        }



        @Override
        public void onFailed(int position) {
            mTasks.remove(position);
            Toast.makeText(DownloadService.this,"Download Failed",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPaused(int position) {
            mTasks.remove(position);
            sendProgressBroadcast(-1);
            Toast.makeText(DownloadService.this,"Paused",Toast.LENGTH_LONG).show();
            SERVICE_STATUS = PAUSE;
        }

        @Override
        public void onProgress(int progress,int position) {
            sendProgressBroadcast(progress);
            mProgress_Record = progress;
            updateProgress.update(progress,position);
            SERVICE_STATUS = PROGRESS;

        }

        @Override
        public void onCanceled(int position) {
            mTasks.remove(position);
            Toast.makeText(DownloadService.this,"Canceled",Toast.LENGTH_LONG).show();
            updateProgress.update(0,position);
        }
    };

    public DownloadService() {
    }

    public int getServiceStatus(){
        return SERVICE_STATUS;
    }

    public interface UpdateProgress{
        void update(int progress,int position);
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

        public void startDownload(String url,int position){
            //if(downloadTask == null){
                downloadURL = url;
                mInfo.put(position,url);
                DownloadTask dt = new DownloadTask(listener,DownloadService.this,position);
                dt.executeOnExecutor(THREAD_POOL_EXECUTOR,downloadURL);

                mTasks.add(position,dt);

                Toast.makeText(DownloadService.this,"Downloading...",Toast.LENGTH_LONG).show();
            //}
        }

        public void pausedDownload(int position){
            if(mTasks.get(position) != null){
                mTasks.get(position).PauseDownload();
            }
        }

        public void cancelDownload(int position){
            if(mTasks.get(position) != null){
                mTasks.get(position).CancelDownload();
            }
            else {
                if( mInfo.get(position) != null){
                    String mapURL = mInfo.get(position);
                    String fileName = mapURL.substring(mapURL.lastIndexOf("/"));
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
