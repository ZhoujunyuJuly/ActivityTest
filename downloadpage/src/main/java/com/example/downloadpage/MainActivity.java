package com.example.downloadpage;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.downloadpage.DownloadTask.ACTION_PROGRESS_BROADCAST;

public class MainActivity extends AppCompatActivity {

    public static final int START  = 0;
    public static final int PAUSED = 1;
    private String URL = "https://raw.githubusercontent.com/guolindev/eclipse/master/eclipse-inst-win64.exe";
    private int TAG_STATUS = 0;


    private DownloadListAdapter mAdapter;
    private List<String> data = new ArrayList<String>();
    private DownloadService.DownloadBinder downloadBinder;
    private ProgressBar mProgressBar;
    private Button mButton;
    private RecyclerView mRecyclerView;
    private TextView mText_Percent;
    private ProgressChangeReceiver mReceiver;


     private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder)service;
            DownloadService downloadService = downloadBinder.getService();

            Log.d("zjy", "mainactivity come in! ");

  //          int progress = downloadService.getProgress();
//            mProgressBar.setProgress(progress);
//            mText_Percent.setText(progress + "%");

            downloadService.setUpdateProgress(new DownloadService.UpdateProgress() {
                @Override
                public void update(int progress) {
                    if (mProgressBar != null) {
                        mProgressBar.setProgress(progress);
                        mText_Percent.setText(String.valueOf(progress) + "%");
                         Log.d("zjy", "mainactivity update " + progress);
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
        setContentView(R.layout.activity_main);

        init();
        //StartService();启动定时服务
        //StartDownloadService();//启动下载服务
        initAdapter();

    }

    private void init() {
        data.add("Android");
        data.add("IOS");
        data.add("JAVA");
        data.add("C++");
        data.add("CSS");


        mRecyclerView = findViewById(R.id.recyclerview_main);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    private void initAdapter(){

        mAdapter = new DownloadListAdapter(R.layout.download_adapter,data);
        mRecyclerView.setAdapter(mAdapter);

        // TODO: 2019/7/9 了解一下adapter.setonclick
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemChildClick(adapter, view, position);
                mProgressBar = mRecyclerView.getChildAt(position).findViewById(R.id.progress_first);
                mButton = mRecyclerView.getChildAt(position).findViewById(R.id.bt_download);
                mText_Percent = mRecyclerView.getChildAt(position).findViewById(R.id.tv_progress_percent);

                switch (view.getId()){
                    case R.id.bt_download :
                        mProgressBar.setVisibility(View.VISIBLE);
                        mText_Percent.setVisibility(View.VISIBLE);


                        if (TAG_STATUS == START) {
                            startDownloadService();
                            mButton.setText("暂停");

                            //首次开始下载，开启服务，等待服务开启0.5s
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (downloadBinder == null) {
                                        return;
                                    }
                                    DownloadStatus(TAG_STATUS);
                                }
                            },500);


                        } else {
                            mButton.setText("继续");
                            DownloadStatus(TAG_STATUS);
                        }



                        break;
                    default:
                        Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                        startActivity(intent);
                }
            }

            @Override
            public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemChildLongClick(adapter, view, position);
                mProgressBar = mRecyclerView.getChildAt(position).findViewById(R.id.progress_first);
                mButton = mRecyclerView.getChildAt(position).findViewById(R.id.bt_download);
                if( downloadBinder == null){
                    return;
                }
                switch (view.getId()){
                    case R.id.bt_download :
                        mProgressBar.setVisibility(View.VISIBLE);
                        mProgressBar.setProgress(0);
                        mText_Percent.setText("0%");

                        downloadBinder.cancelDownload();
                        mButton.setText("重新下载");
                        TAG_STATUS = START;
                        stopDownloadService();//关闭服务
                        break;
                    default:
                        break;
                }
            }
        });

    }

    private void DownloadStatus(int status){
        switch (status){
            case START:
                downloadBinder.startDownload(URL);
                TAG_STATUS = PAUSED;
                break;
            case PAUSED:
                downloadBinder.pausedDownload();
                TAG_STATUS = START;
                break;
        }
    }

    private void startDownloadService(){
        Intent intent = new Intent(this,DownloadService.class);
        startService(intent);

        //绑定服务
        bindService(intent,connection,BIND_AUTO_CREATE);

        //广播接收器
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction(ACTION_PROGRESS_BROADCAST);
//        mReceiver = new ProgressChangeReceiver();
//        registerReceiver(mReceiver,intentFilter);

        if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.
                permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]
                    {Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }

    }

    private void stopDownloadService(){
        Intent intent = new Intent(this,DownloadService.class);
        unbindService(connection);
        stopService(intent);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "拒绝权限将无法使用程序", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
            default:
        }
    }

    @Override
    protected void onDestroy() {
        unbindService(connection);
        //unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    class ProgressChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            int progress = bundle.getInt("progress");
            Log.d("zjy", "mainactivity receive  " + progress);

            if( progress != -1 ) {
                mProgressBar.setProgress(progress);
                mText_Percent.setText(progress + "%");
            }else {
                mProgressBar.setProgress(50);
                mText_Percent.setText("50%");
            }
            //Toast.makeText(MainActivity.this,"广播发送",Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onStart() {
        Intent intent = new Intent(this,DownloadService.class);

        //绑定服务
        bindService(intent,connection,BIND_AUTO_CREATE);
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(connection);
    }


}
