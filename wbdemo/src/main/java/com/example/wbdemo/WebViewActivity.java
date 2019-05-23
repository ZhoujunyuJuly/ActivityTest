package com.example.wbdemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.wbdemo.Object.Token;
import com.google.gson.Gson;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static com.example.wbdemo.Object.URLInfo.API_URL;
import static com.example.wbdemo.Object.URLInfo.CLIENT_ID;
import static com.example.wbdemo.Object.URLInfo.CLIENT_SECRET;
import static com.example.wbdemo.Object.URLInfo.GRANT_TYPE;
import static com.example.wbdemo.Object.URLInfo.HEADER_ACCESS;
import static com.example.wbdemo.Object.URLInfo.REDIRECT_URL;
import static com.example.wbdemo.Object.URLInfo.TAG_URL;

public class WebViewActivity extends AppCompatActivity {

    private String CODE;
    private String AUTH_URL;

    private WebView mWebView;
    private Token mToken;
    private String mToken_content;


    public static void start(Context context, String authUrl) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(TAG_URL, authUrl);//未使用传参
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        //getURL();
        initView();
    }


    //本activity未使用到传值
    private void getURL() {
        Intent intent = getIntent();
        AUTH_URL = intent.getStringExtra(TAG_URL);
    }

    private void initView() {
        mWebView = findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);

        parseJson();
        mWebView.addJavascriptInterface(new InJavaScriptLocalObj(), "local_obj");
        mWebView.loadUrl(API_URL);
    }

    private void parseJson() {
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //view.loadUrl(url);
                CODE = url.substring(url.indexOf("code=") + 5);//获取code
                Log.d("zjy", "跳转网址为:  " + HEADER_ACCESS + getPost());
                view.postUrl(HEADER_ACCESS, getPost().getBytes());

                //使用OKHTTP获取json
//                OkHttpManager.getInstance().post(HEADER_ACCESS, getMap(), new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toast.makeText(getApplicationContext(), "访问失败", Toast.LENGTH_LONG).show();
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        final String json = response.body().toString();
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                mToken = new Gson().fromJson(json, Token.class);
//                                //saveToken();
//                                mTvJson.setText(json);
//                                Toast.makeText(getApplicationContext(), "成功", Toast.LENGTH_LONG).show();
//                            }
//                        });
//                    }
//                });
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.loadUrl("javascript:window.local_obj.showSource('<head>'+"
                        + "document.getElementsByTagName('html')[0].innerHTML+'</head>');");
                //线程休息3秒
//                Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        if(!TextUtils.isEmpty(mToken_content)) {
//                            LaunchActivity.start(WebViewActivity.this, mToken_content);
//                        }
//                    }
//                }, 5000);//3秒后执行Runnable中的run方法

                if (!TextUtils.isEmpty(mToken_content)) {
                    LaunchActivity.start(WebViewActivity.this, mToken_content);
                }
            }
        });
    }

    //保存token
    private void saveToken() {
        SharedPreferences.Editor editor = getSharedPreferences("access_token", MODE_PRIVATE).edit();
        editor.putString("access_token", mToken.getAccess_token());
        editor.putString("remind_in", mToken.getRemind_in());
        editor.putString("expires_in", String.valueOf(mToken.getExpires_in()));
        editor.putString("uid", mToken.getUid());
        editor.putString("isRealName", mToken.getIsRealName());

        editor.apply();
    }

    //使用code请求token的post信息
    private String getPost() {
        String post = MapToString(getMap());
        return post;
    }

    private Map getMap() {
        Map<String, String> map = new HashMap<>();
        map.put("code", CODE);
        map.put("redirect_uri", REDIRECT_URL);
        map.put("grant_type", GRANT_TYPE);
        map.put("client_secret", CLIENT_SECRET);
        map.put("client_id", CLIENT_ID);

        return map;
    }

    private String MapToString(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> item : map.entrySet()) {
            sb.append(item.getKey()).append("=").append(URLEncoder.encode(item.getValue())).append("&");//解码
        }

        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    //提取TOKEN，当界面加载完
    final class InJavaScriptLocalObj {
        @JavascriptInterface
        public void showSource(String html) {
            Log.d("zjy", "html is " + html);
            if (html != null && html.contains("{\"access_token\"")) {
                String json = html.substring(html.indexOf("{\"access_token\""), html.indexOf("</pre>"));
                mToken = new Gson().fromJson(json, Token.class);
                mToken_content = mToken.getAccess_token();

                if (!TextUtils.isEmpty(mToken_content)) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            LaunchActivity.start(WebViewActivity.this, mToken_content);
                        }
                    });
                }
            }

        }
    }

}
