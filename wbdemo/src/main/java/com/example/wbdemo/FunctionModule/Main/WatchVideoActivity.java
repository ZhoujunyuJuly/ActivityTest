package com.example.wbdemo.FunctionModule.Main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.wbdemo.R;

public class WatchVideoActivity extends AppCompatActivity {

    private static final String TAG_VIDEO_URL = "video_url";


    public static void start(Context context,String url){
        Intent intent = new Intent(context,WatchVideoActivity.class);
        intent.putExtra(TAG_VIDEO_URL,url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_video);

        WebView webView = findViewById(R.id.watchVideo_webview);
        //webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(getURL());
        //setContentView(webView);
    }

    private String getURL(){
        return  getIntent().getStringExtra(TAG_VIDEO_URL);
    }
}
