package com.example.a36kr;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NewsContentActivity extends AppCompatActivity {

    private WebView mwebView;
    private Notification mNotification;
    private NotificationManager mNotificationManager;
    private NotificationChannel mNotificationChannel;
    private PendingIntent mResultIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);

        init();
        useWebView();
        useNotification();


    }


    private void init() {
        mwebView = findViewById(R.id.web_view);
    }


    private void useWebView() {
        Intent intent = getIntent();
        final String URL = intent.getStringExtra("url");

        mwebView.onResume();//激活网页状态，能正常执行网页需求

        mwebView.getSettings().setJavaScriptEnabled(true);
        mwebView.setWebViewClient(new WebViewClient());
        mwebView.getSettings().setUseWideViewPort(true);
        mwebView.getSettings().setLoadWithOverviewMode(true);
        mwebView.loadUrl(URL);
    }

    private void useNotification() {
        String id = "channel_1";
        String description = "wechat";
        int important = NotificationManager.IMPORTANCE_LOW;

        mNotificationChannel = new NotificationChannel(id, description, important);
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotificationManager.createNotificationChannel(mNotificationChannel);

        Intent intent = new Intent();
        mResultIntent = PendingIntent.getActivity(this, 1, intent, Intent.FLAG_ACTIVITY_NEW_TASK);

        mNotification = new NotificationCompat.Builder(this, id)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("微信")
                .setContentText("一个红包")
                .setContentIntent(mResultIntent)
                .setTicker("收到一个微信通知~")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .build();

        mNotificationManager.notify(1, mNotification);
    }
}
