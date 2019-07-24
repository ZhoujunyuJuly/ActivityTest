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

    private String URL_1 = "https://raw.githubusercontent.com/guolindev/eclipse/master/eclipse-inst-win64.exe";
    private String URL_2 = "http://gdown.baidu.com/data/wisegame/77dae776f870e572/PPTVjuli_61.apk";
    private String URL_3 = "http://gdown.baidu.com/data/wisegame/0c843031eaaaa6cd/MoXiuLauncher_459.apk";
    private String URL_4 = "http://gdown.baidu.com/data/wisegame/25d0855f1586ee81/anzhuobizhi_108.apk";


    private int TAG_STATUS = 0;


    private DownloadListAdapter mAdapter;
    private List<DownloadData> data = new ArrayList<DownloadData>();
    private DownloadService.DownloadBinder downloadBinder;
    private ProgressBar mProgressBar;
    private Button mButton;
    private RecyclerView mRecyclerView;
    private TextView mText_Percent;
    private boolean isBind = true;
    //private ProgressChangeReceiver mReceiver;


    //Android系统调用service的OnBind返回与service交互的IBinder,binderService立即返回，但不返回给客户端；
    //客户端需要通过serviceConnection来接收
     private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder)service;
            DownloadService downloadService = downloadBinder.getService();

            //获取当前服务状态，有可能在第二页开启了服务，刷新第一页按钮状态
            if( mButton != null) {
                int service_status = downloadService.getServiceStatus();
                if (service_status != -1) {//如果当前存在运行的服务
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

            //获取当前服务进度的[瞬时]状态/不确定是否必要，当时前后两页状态不一致时修复的bug
            //理论上两个activity都绑定了服务，无需做此操作，直接获取实时进度即可
            int progress = downloadService.getProgress();
            if ( mProgressBar != null) {
                mProgressBar.setProgress(progress);
                mText_Percent.setText(progress + "%");
            }

            //进度条更新，对下载任务中，有实时调用此刷新接口，在connection中进行方法实现
            downloadService.setUpdateProgress(new DownloadService.UpdateProgress() {
                @Override
                public void update(int progress,DownloadTask dt) {
                    if( mRecyclerView != null) {
                        //对list中控件进行tag标记，通过url进行单一映射，找到对应位置控件/标记不能重复，重复的第二个标记控件报空指针
                        ProgressBar progressBar = mRecyclerView.findViewWithTag(dt.getDownURL()).findViewById(R.id.progress_first);
                        TextView textView = mRecyclerView.findViewWithTag(dt.getDownURL() + "a").findViewById(R.id.tv_progress_percent);

                        progressBar.setProgress(progress);
                        textView.setText(progress + "%");
                    }
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //一般情况使用不到，解绑服务时不会调用/只有在服务停止，崩溃时调用
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
        data.add(new DownloadData("C++",URL_3));
        data.add(new DownloadData("Java",URL_4));
        data.add(new DownloadData("CSS",URL_1));


        mRecyclerView = findViewById(R.id.recyclerview_main);
        //经常忘记写这句话！
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

                final String currentURL = data.get(position).getBookURL();

                //根据地址标记list中的进度栏
                mProgressBar.setTag(currentURL);
                mText_Percent.setTag(currentURL + "a");

                switch (view.getId()){
                    case R.id.bt_download :
                        mProgressBar.setVisibility(View.VISIBLE);
                        mText_Percent.setVisibility(View.VISIBLE);

                        //获取控件信息，string类型使用equals，"=="貌似会报错/但也不一定，当时是控件绑错了，懒得验证了！下次注意
                        if(mButton.getText().equals("暂停")){
                            TAG_STATUS = PAUSED;
                        }else {
                            TAG_STATUS = START;
                        }

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
                                    DownloadStatus(TAG_STATUS,currentURL);
                                }
                            },500);


                        } else {
                            mButton.setText("开始");
                            DownloadStatus(TAG_STATUS,currentURL);
                        }



                        break;
                    default:
                        //第二页废了，懒得更新了，同步情况在之前版本里写过，原理一样
                        //Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                        //startActivity(intent);
                }
            }


            //长按操作
            @Override
            public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemChildLongClick(adapter, view, position);
                mProgressBar = mRecyclerView.getChildAt(position).findViewById(R.id.progress_first);
                mButton = mRecyclerView.getChildAt(position).findViewById(R.id.bt_download);
                mText_Percent = mRecyclerView.getChildAt(position).findViewById(R.id.tv_progress_percent);

                //安全判断
                if( downloadBinder == null){
                    return;
                }
                switch (view.getId()){
                    case R.id.bt_download :
                        mProgressBar.setVisibility(View.VISIBLE);
                        mProgressBar.setProgress(0);
                        mText_Percent.setText("0%");

                        downloadBinder.cancelDownload(data.get(position).getBookURL());
                        mButton.setText("重新下载");
                        stopDownloadService();//关闭服务，节省资源
                        break;
                    default:
                        break;
                }
            }
        });

    }

    private void DownloadStatus(int status,String DownloadURL){
        switch (status){
            case START:
                downloadBinder.startDownload(DownloadURL);
                break;
            case PAUSED:
                downloadBinder.pausedDownload(DownloadURL);
                break;
        }
    }
    

    private void startDownloadService(){
        Intent intent = new Intent(this,DownloadService.class);
        startService(intent);

        //有可能的情况：进入到第二页时，此activity生命周期经过onPause()解绑了服务，回退到此页时判断一下
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

        //理论上由第二页回退到此页默认绑定一下
        //绑定服务可以在开启服务之前，它会绑定一个空服务，直到服务开启，再自动创建绑定
        bindService(intent,connection,BIND_AUTO_CREATE);
        super.onStart();
    }

    //进入到第二页时解绑一下服务，不然由第二页回退到第一页，貌似绑定失效了，service回调不到此activity的connection
    //此处调试了三天三夜，高亮！（OK，也没有这么久
    @Override
    protected void onPause() {
        super.onPause();
        if( isBind ) {
            unbindService(connection);
        }
    }


}
