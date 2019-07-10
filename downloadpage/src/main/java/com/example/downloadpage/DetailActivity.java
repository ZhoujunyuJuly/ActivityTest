package com.example.downloadpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.downloadpage.DownloadTask.ACTION_PROGRESS_BROADCAST;


public class DetailActivity extends AppCompatActivity {
    //private ProgressChangeReceiver mProgressReceiver;
    private ProChangeReceiver proChangeReceiver;
    private ProgressBar mProgress;
    private TextView mProgress_percent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        init();
        initReceiver();

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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(proChangeReceiver);
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
