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
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;
import static com.example.downloadpage.DownloadTask.ACTION_PROGRESS_BROADCAST;


public class DetailActivity extends AppCompatActivity {
    //private ProgressChangeReceiver mProgressReceiver;
    private ProChangeReceiver proChangeReceiver;
    private ProgressBar mProgress;
    private TextView mProgress_percent;
    private DownloadService.DownloadBinder downloadBinder;
    private Button mBt;
    private int STATUS = 1;

    private ServiceConnection connection_detail = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder)service;
            DownloadService downloadService = downloadBinder.getService();

            if( mBt != null) {
                int service_status = downloadService.getServiceStatus();
                if (service_status != -1) {
                    if (service_status == 0) {//正在运行
                        mBt.setText("暂停");
                        STATUS = service_status;
                    } else {
                        mBt.setText("开始");
                        STATUS = service_status;
                    }
                }
            }

            int mProgress_value = downloadService.getProgress();
            if(mProgress != null) {
                mProgress.setProgress(mProgress_value);
                mProgress_percent.setText(mProgress_value + "%");
            }


            downloadService.setUpdateProgress(new DownloadService.UpdateProgress() {
                @Override
                public void update(int progress,DownloadTask dt) {
                    if( mProgress != null) {
                        mProgress.setProgress(progress);
                        mProgress_percent.setText(progress + "%");
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


    //接收服务器广播-----------------------------------------------
    private void receiveBroadcast(){
        init();
        initReceiver();
    }

    private void init(){
        mProgress = findViewById(R.id.progress_detail);
        mProgress_percent = findViewById(R.id.tv_progress_percent_detail);
        mBt = findViewById(R.id.bt_pause);
    }

    private void initReceiver(){

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_PROGRESS_BROADCAST);
        //registerReceiver(mProgressReceiver,intentFilter);
        proChangeReceiver = new ProChangeReceiver();
        registerReceiver(proChangeReceiver,intentFilter);

    }

    //--------------------------------------------------------
    //监听服务
    private void listenService(){
        init();
        bindService();

        mBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( STATUS == 1) {//正在运行
                    //bindService();
                    mBt.setText("暂停");
                    downloadBinder.startDownload("https://raw.githubusercontent.com/guolindev/eclipse/master/eclipse-inst-win64.exe");
                    STATUS = 0;
                }else {//暂停
                    downloadBinder.pausedDownload("https://raw.githubusercontent.com/guolindev/eclipse/master/eclipse-inst-win64.exe");
                    mBt.setText("开始");
                    STATUS = 1;
                }
            }
        });

    }


    private void bindService(){
        Intent intent = new Intent(this,DownloadService.class);
        startService(intent);
        bindService(intent,connection_detail,BIND_AUTO_CREATE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {

        super.onPause();
        unbindService(connection_detail);
        connection_detail = null;
    }

    @Override
    protected void onResume() {
        super.onResume();
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
                mProgress.setProgress(50) ;
                mProgress_percent.setText("50%");
            }
            //Toast.makeText(DetailActivity.this,"广播发送",Toast.LENGTH_LONG).show();
        }
    }

}
