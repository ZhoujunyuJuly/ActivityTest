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
import android.widget.TextView;
import android.widget.Toast;

import com.example.wbdemo.net.OkHttpManager;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.wbdemo.LaunchActivity.TAG_TOKRN;
import static com.example.wbdemo.Object.URLInfo.API_URL;
import static com.example.wbdemo.Object.URLInfo.CLIENT_ID;
import static com.example.wbdemo.Object.URLInfo.CLIENT_SECRET;
import static com.example.wbdemo.Object.URLInfo.GRANT_TYPE;
import static com.example.wbdemo.Object.URLInfo.HEADER_ACCESS;
import static com.example.wbdemo.Object.URLInfo.REDIRECT_URL;
import static com.example.wbdemo.Object.URLInfo.TOKEN;

public class WebViewActivity extends AppCompatActivity {


    public static final String TAG_URL = "authurl";
    public static final String api_url = "https://api.weibo.com/oauth2/authorize";
    public static final String postDate = "client_id=621366344&redirect_uri=https://www.baidu.com";
    private String CODE;
    private String AUTH_URL;

    private WebView mWebView;
    private TextView mTvJson;

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
        mTvJson = findViewById(R.id.tv_json);
        mWebView = findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);

        //webview设置清空缓存模式
//        WebSettings settings = mWebView.getSettings();
//        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        parseJson();
        mWebView.loadUrl(API_URL);
    }


    private void parseJson(){
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //view.loadUrl(url);
                CODE = url.substring(url.indexOf("code=") + 5);//获取code
                Log.d("zjy", "跳转网址为:  " + HEADER_ACCESS + getPost());
                view.postUrl(HEADER_ACCESS, getPost().getBytes());
                //LaunchActivity.start(WebViewActivity.this,CODE);

                OkHttpManager.getInstance().post(HEADER_ACCESS, getMap(), new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),"访问失败",Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String json = response.body().toString();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTvJson.setText(json);
                                Toast.makeText(getApplicationContext(),"成功",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });


                return true;
            }
        });
    }


    private String getPost() {
        String post = MapToString(getMap());
        return post;
    }

    private Map getMap(){
        Map<String, String> map = new HashMap<>();
        map.put("code", CODE);
        map.put("redirect_uri", REDIRECT_URL);
        map.put("grant_type", GRANT_TYPE);
        map.put("client_secret", CLIENT_SECRET);
        map.put("client_id", CLIENT_ID);

        return map;
    }

    private String MapToString(Map<String,String> map){
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, String> item : map.entrySet()) {
            sb.append(item.getKey()).append("=").append(URLEncoder.encode(item.getValue())).append("&");//解码
        }

        sb.deleteCharAt(sb.length()-1);
        return sb.toString();

    }


}
