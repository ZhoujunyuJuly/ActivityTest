package com.example.downloadpage;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import java.io.File;
import java.util.HashMap;

import static android.os.AsyncTask.THREAD_POOL_EXECUTOR;
import static com.example.downloadpage.DownloadTask.ACTION_PROGRESS_BROADCAST;

public class DownloadService extends Service {

    private static final int PROGRESS = 0;
    private static final int PAUSE = 1;

    private UpdateProgress updateProgress;
    private Intent mIntent_Broadcast;
    private int mProgress_Record =0;
    private int SERVICE_STATUS = -1;
    private HashMap<String,DownloadTask> mTaskMap = new HashMap<>();


    //下载结果监听
    private DownloadListener listener = new DownloadListener() {
        @Override
        public void onSuccess(DownloadTask dt) {
            if( dt != null ) {
                mTaskMap.remove(dt.getDownURL());
                dt = null;
                Toast.makeText(DownloadService.this, "Download Success", Toast.LENGTH_LONG).show();
            }
        }



        @Override
        public void onFailed(DownloadTask dt) {
            if( dt != null) {
                mTaskMap.remove(dt.getDownURL());
                dt = null;
                Toast.makeText(DownloadService.this, "Download Failed", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onPaused(DownloadTask dt) {
            if( dt != null) {
                mTaskMap.remove(dt.getDownURL());
                dt = null;
                Toast.makeText(DownloadService.this,"Paused",Toast.LENGTH_LONG).show();
                SERVICE_STATUS = PAUSE;
            }
        }

        @Override
        public void onProgress(int progress,DownloadTask dt) {
            if( dt != null ) {
                mProgress_Record = progress;
                //实时调用接口与客户端进行通信，对UI线程更新
                updateProgress.update(progress,dt);
                SERVICE_STATUS = PROGRESS;
            }
        }

        @Override
        public void onCanceled(DownloadTask dt) {
            if( dt != null) {
                mTaskMap.remove(dt.getDownURL());
                dt = null;
                Toast.makeText(DownloadService.this, "Canceled", Toast.LENGTH_LONG).show();
                updateProgress.update(0, dt);
            }
        }
    };

    public DownloadService() {
    }

    public int getServiceStatus(){
        return SERVICE_STATUS;
    }

    public interface UpdateProgress{
        void update(int progress,DownloadTask dt);
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


    //Service与activity建立联系的跨进程通信方式；Binder创建一个内存接收缓存空间，使用用户空间传送数据时只需要拷贝一次
    //绑定服务后，系统可调用service的OnBinder返回与service交互的IBinder,并通过Binder对服务进行操作
    public class DownloadBinder extends Binder{
        public DownloadService getService(){
            return DownloadService.this;
        }

        public void startDownload(String url){

            if(mTaskMap.containsKey(url)) {
                return;
            }

            //创建下载任务
            DownloadTask downloadTask = new DownloadTask(listener, DownloadService.this,url);
            downloadTask.executeOnExecutor(THREAD_POOL_EXECUTOR, url);

            mTaskMap.put(url, downloadTask);
            Toast.makeText(DownloadService.this,"Downloading...",Toast.LENGTH_LONG).show();

        }

        public void pausedDownload(String url){
                if (mTaskMap.size() > 0 && mTaskMap.containsKey(url)) {
                    DownloadTask task = mTaskMap.get(url);
                    if( task != null){
                        task.pauseDownload();
                    }
                }
        }

        public void cancelDownload(String url) {
            if ( mTaskMap.containsKey(url)) {
                DownloadTask task = mTaskMap.get(url);
                if (task != null) {
                    task.cancelDownload();
                }
            } else {
                if (url != null) {
                    String fileName = url.substring(url.lastIndexOf("/"));
                    String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file = new File(directory + fileName);
                    if (file.exists()) {
                        file.delete();
                    }
                    Toast.makeText(DownloadService.this, "Canceled", Toast.LENGTH_LONG).show();
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

    //重新绑定服务,了解activity生命周期
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
