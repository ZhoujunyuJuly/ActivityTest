package com.example.wbdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import static com.example.wbdemo.Object.URLInfo.HEADER_ACCESS;
import static com.example.wbdemo.Object.URLInfo.TOKEN;

public class WebViewActivity extends AppCompatActivity {


    public static final String TAG_URL = "authurl";
    public static final String api_url = "https://api.weibo.com/oauth2/authorize";
    public static final String postDate = "client_id=621366344&redirect_uri=https://www.baidu.com";
    private String CODE;
    private String AUTH_URL;

    private WebView mWebView;

    public static void start(Context context,String authUrl){
        Intent intent = new Intent(context,WebViewActivity.class);
        intent.putExtra(TAG_URL,authUrl);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        getURL();
        initView();
    }


    private void getURL(){
        Intent intent = getIntent();
        AUTH_URL = intent.getStringExtra(TAG_URL);
    }

    private void initView(){
        mWebView = findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        WebSettings settings = mWebView.getSettings();
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //view.loadUrl(url);
                CODE = url.substring(url.indexOf("code=")+5);//获取code
                Log.d("zjy", "跳转网址为:  "+url + " code = "+CODE);
                view.postUrl(HEADER_ACCESS, TOKEN.getBytes());
                //LaunchActivity.start(WebViewActivity.this,CODE);
                if(url != null) {
                    return true;
                }else {
                    Toast.makeText(getApplicationContext(),"url is null",Toast.LENGTH_LONG).show();
                    return false;
                }
            }
        });

        //mWebView.setWebViewClient(new WebViewClient());


        mWebView.loadUrl(AUTH_URL);
    }

    //使用post加载webview
    private void postWebView(String url,String post){
        mWebView.postUrl(url, post.getBytes());
    }
}
