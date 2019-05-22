package com.example.wbdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wbdemo.net.OkHttpManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.wbdemo.Object.URLInfo.API_URL;
import static com.example.wbdemo.Object.URLInfo.CLIENT_ID;
import static com.example.wbdemo.Object.URLInfo.REDIRECT_URL;
import static com.example.wbdemo.Object.URLInfo.REQUEST_URL;
import static com.example.wbdemo.Object.URLInfo.TOKEN;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button webview_btn;
    private Button html_btn;
    private TextView mResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();


    }

    private void initView() {
        webview_btn = findViewById(R.id.button_webview);
        html_btn = findViewById(R.id.button_html);
        mResponse = findViewById(R.id.response);

        webview_btn.setOnClickListener(this);
        html_btn.setOnClickListener(this);
        mResponse.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_html:
                OkHttpManager.getInstance().post(REQUEST_URL, getKey(CLIENT_ID, REDIRECT_URL), new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_LONG).show();

                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        final String repo = response.body().string();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mResponse.setText(repo);
                                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                break;

            case R.id.button_webview:
                //WebViewActivity.start(MainActivity.this, API_URL);
                LaunchActivity.start(this,"222");
                break;

        }

    }

    private Map getKey(String key, String redirect_url) {
        Map<String, String> map = new HashMap<>();
        map.put("client_id", key);
        map.put("redirect_uri", redirect_url);

        return map;
    }

}
