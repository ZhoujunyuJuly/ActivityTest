package com.example.a36kr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class NewsContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);

        WebView webView = findViewById(R.id.web_view);
        Intent intent = getIntent();
        String URL = intent.getStringExtra("url");

        webView.loadUrl(URL);
    }
}
