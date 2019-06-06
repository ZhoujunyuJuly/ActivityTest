package com.example.wbdemo.business.main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.LinearLayout;

import com.example.wbdemo.R;
import com.just.agentweb.AgentWeb;

public class WatchVideoActivity extends AppCompatActivity {

    private static final String TAG_VIDEO_URL = "video_url";


    public static void start(Context context, String url) {
        Intent intent = new Intent(context, WatchVideoActivity.class);
        intent.putExtra(TAG_VIDEO_URL, url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_video);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);


//        WebView webView = findViewById(R.id.watchVideo_webview);
//        //webView.setWebViewClient(new WebViewClient());
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.loadUrl(getURL());
//        //setContentView(webView);

        LinearLayout mLinearLayout = findViewById(R.id.watchVideo_agentWebView);
        AgentWeb.with(this)
                .setAgentWebParent(mLinearLayout, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(getURL());
    }

    private String getURL() {
        return getIntent().getStringExtra(TAG_VIDEO_URL);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        WatchVideoActivity.this.finish();
    }
}

