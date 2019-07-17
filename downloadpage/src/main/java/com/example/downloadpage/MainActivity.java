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
    public String URL_1 = "https://raw.githubusercontent.com/guolindev/eclipse/master/eclipse-inst-win64.exe";
    public String URL_2 = "http://gdown.baidu.com/data/wisegame/77dae776f870e572/PPTVjuli_61.apk";
    private int TAG_STATUS = 0;


    private DownloadListAdapter mAdapter;
    private List<DownloadData> data = new ArrayList<>();
    private DownloadService.DownloadBinder downloadBinder;
    private ProgressBar mProgressBar;
    private Button mButton;
    private RecyclerView mRecyclerView;
    private TextView mText_Percent;
    private boolean isBind = true;
    //private ProgressChangeReceiver mReceiver;


     private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder)service;
            DownloadService downloadService = downloadBinder.getService();

            if( mButton != null) {
                int service_status = downloadService.getServiceStatus();
                if (service_status != -1) {//非默认状态，服务启动后检测
                    if (service_status == 0) {//正在运行
                        mProgressBar.setVisibility(View.VISIBLE);
                        mText_Percent.setVisibility(View.VISIBLE);
                        mButton.setText("暂停");
                        TAG_STATUS = PAUSED;
                    } else {
                        mProgressBar.setVisibility(View.VISIBLE);
                        mText_Percent.setVisibility(View.VISIBLE);
                        mButton.setText("开始");
                        TAG_STATUS = START;
                    }
                }
            }

            int progress = downloadService.getProgress();
            if ( mProgressBar != null) {
                mProgressBar.setProgress(progress);
                mText_Percent.setText(progress + "%");
            }

            downloadService.setUpdateProgress(new DownloadService.UpdateProgress() {
                @Override
                public void update(int progress,int position) {
                    if( mRecyclerView != null) {
                        ProgressBar mPB = mRecyclerView.getChildAt(position).findViewById(R.id.progress_first);
                        TextView mTV = mRecyclerView.getChildAt(position).findViewById(R.id.tv_progress_percent);
                        if (mPB != null) {
                            mPB.setProgress(progress);
                            mTV.setText(String.valueOf(progress) + "%");
                        }
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
        initAdapter();

        //StartService();启动定时服务
    }

    private void init() {
        data.add(new DownloadData("Android",URL_1));
        data.add(new DownloadData("IOS",URL_2));
        data.add(new DownloadData("JAVA",URL_1));
        data.add(new DownloadData("C++",URL_2));
        data.add(new DownloadData("CSS"," "));



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
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                super.onItemChildClick(adapter, view, position);
                mProgressBar = mRecyclerView.getChildAt(position).findViewById(R.id.progress_first);
                mButton = mRecyclerView.getChildAt(position).findViewById(R.id.bt_download);
                mText_Percent = mRecyclerView.getChildAt(position).findViewById(R.id.tv_progress_percent);

                switch (view.getId()){
                    case R.id.bt_download :
                        mProgressBar.setVisibility(View.VISIBLE);
                        mText_Percent.setVisibility(View.VISIBLE);

                        String text = mButton.getText().toString();

                        if( text.equals("开始") || text.equals("点击下载") || text.equals("重新下载")){
                            TAG_STATUS = START;
                        }else {
                            TAG_STATUS = PAUSED;
                        }

                        Log.d("zjy", "text " + mButton.getText().toString());

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
                                    DownloadStatus(TAG_STATUS,position);
                                }
                            },500);


                        } else {
                            mButton.setText("开始");
                            DownloadStatus(TAG_STATUS,position);
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
                mText_Percent = mRecyclerView.getChildAt(position).findViewById(R.id.tv_progress_percent);

                if( downloadBinder == null){
                    return;
                }
                switch (view.getId()){
                    case R.id.bt_download :
                        mProgressBar.setVisibility(View.VISIBLE);
                        mProgressBar.setProgress(0);
                        mText_Percent.setText("0%");

                        downloadBinder.cancelDownload(position);
                        mButton.setText("重新下载");
                        TAG_STATUS = START;
                        //stopDownloadService();//关闭服务
                        break;
                    default:
                        break;
                }
            }
        });

    }

    private void DownloadStatus(int status,int position){
        switch (status){
            case START:
                downloadBinder.startDownload(data.get(position).getURL(),position);
                TAG_STATUS = PAUSED;
                break;
            case PAUSED:
                downloadBinder.pausedDownload(position);
                TAG_STATUS = START;
                break;
        }
    }

    private void startDownloadService(){
        Intent intent = new Intent(this,DownloadService.class);
        startService(intent);

        if( !isBind ){
            bindService(intent,connection,BIND_AUTO_CREATE);
        }


        //绑定服务
        //bindService(intent,connection,BIND_AUTO_CREATE);

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
        isBind = false;

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
        if( isBind ) {
            unbindService(connection);
        }
    }


}
