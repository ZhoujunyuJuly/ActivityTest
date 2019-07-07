package com.example.downloadpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        init();
        StartService();

    }

    private void init(){
        bt = findViewById(R.id.bt_second);
        bt.setOnClickListener(this);
    }

    private void StartService(){
        Intent intent = new Intent(this,RunningService.class);
        startService(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_second:
                Intent second = new Intent(this,SecondActivity.class);
                startActivity(second);
        }
    }
}
