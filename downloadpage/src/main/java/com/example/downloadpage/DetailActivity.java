package com.example.downloadpage;

import androidx.appcompat.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import static com.example.downloadpage.DownloadTask.ACTION_PROGRESS_BROADCAST;


public class DetailActivity extends AppCompatActivity {
    //private ProgressChangeReceiver mProgressReceiver;
    private ProChangeReceiver proChangeReceiver;
    private ProgressBar mProgress;
    private TextView mProgress_percent;
    private DownloadService.DownloadBinder downloadBinder;
    private int mProgress_value;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder)service;
            DownloadService downloadService = downloadBinder.getService();

            mProgress_value = downloadService.getProgress();
            mProgress.setProgress(mProgress_value);
            mProgress_percent.setText(mProgress_value + "%");

            downloadService.setUpdateProgress(new DownloadService.UpdateProgress() {
                @Override
                public void update(int progress) {
                    if( mProgress != null) {
                        mProgress.setProgress(progress);
                        mProgress_percent.setText(progress + "%");
                        Log.d("zjy", "detail activity update " + progress);
                    }
                }
            });

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        //接收服务器广播的方法，缺陷：在上一界面取消任务时，接收不到广播；
        //receiveBroadcast();

        //监听服务
        listenService();

    }


    //接收服务器广播
    private void receiveBroadcast(){
        init();
        initReceiver();
    }

    //监听服务
    private void listenService(){
        init();
        bindService();
    }

    private void init(){
        mProgress = findViewById(R.id.progress_detail);
        mProgress_percent = findViewById(R.id.tv_progress_percent_detail);
    }

    private void initReceiver(){

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_PROGRESS_BROADCAST);
        //registerReceiver(mProgressReceiver,intentFilter);
        proChangeReceiver = new ProChangeReceiver();
        registerReceiver(proChangeReceiver,intentFilter);

    }

    private void bindService(){
        Intent intent = new Intent(this,DownloadService.class);
        bindService(intent,connection,BIND_AUTO_CREATE);
    }


    @Override
    protected void onDestroy() {
        unbindService(connection);
        super.onDestroy();
        //unregisterReceiver(proChangeReceiver);
    }

    class ProChangeReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            int progress = bundle.getInt("progress");
            if( progress != -1 ) {
                mProgress.setProgress(progress);
                mProgress_percent.setText(progress + "%");
            }else {
                mProgress.setProgress(50);
                mProgress_percent.setText("50%");
            }
            //Toast.makeText(DetailActivity.this,"广播发送",Toast.LENGTH_LONG).show();
        }
    }

}
