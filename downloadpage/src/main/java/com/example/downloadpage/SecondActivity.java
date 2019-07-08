package com.example.downloadpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.InputStream;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt_start;
    private Button bt_pause;
    private Button bt_cancel;

    private DownloadService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        init();
        StartDownloadService();
    }

    private void init(){
        bt_cancel = findViewById(R.id.bt_cancel);
        bt_start = findViewById(R.id.bt_start);
        bt_pause = findViewById(R.id.bt_pause);

        bt_pause.setOnClickListener(this);
        bt_start.setOnClickListener(this);
        bt_cancel.setOnClickListener(this);

    }

    private void StartDownloadService(){
        Intent intent = new Intent(this,DownloadService.class);
        startService(intent);
        bindService(intent,connection,BIND_AUTO_CREATE);

        if(ContextCompat.checkSelfPermission(SecondActivity.this,Manifest.
                permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(SecondActivity.this,new String[]
                    {Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
    }

    @Override
    public void onClick(View v) {
        if( downloadBinder == null){
            return;
        }

        switch (v.getId()){
            case R.id.bt_start:
                String url = "https://raw.githubusercontent.com/guolindev/eclipse/master/eclipse-inst-win64.exe";
                //String url = "https://avatar.csdn.net/4/E/8/1_y1scp.jpg";
                downloadBinder.startDownload(url);
                break;
            case R.id.bt_cancel:
                downloadBinder.cancelDownload();
                break;
            case R.id.bt_pause:
                downloadBinder.pausedDownload();
                break;
                default:
                    break;

        }

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
        super.onDestroy();
        unbindService(connection);
    }
}
