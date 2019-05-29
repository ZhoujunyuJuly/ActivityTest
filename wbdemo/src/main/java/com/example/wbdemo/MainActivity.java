package com.example.wbdemo;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wbdemo.info.maindata.HomeTimeLine;
import com.example.wbdemo.net.OkHttpManager;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.wbdemo.info.URLInfo.API_URL;
import static com.example.wbdemo.info.URLInfo.EntireLINK;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button webview_btn;
    private Button html_btn;
    private TextView mResponse;
    private HomeTimeLine mHomeTimeLine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        mResponse.setText(getAPPVersionName());
    }

    private void initView() {
        webview_btn = findViewById(R.id.button_webview);
        html_btn = findViewById(R.id.button_html);
        mResponse = findViewById(R.id.tv_infomation);

        webview_btn.setOnClickListener(this);
        html_btn.setOnClickListener(this);
        mResponse.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    private String getAPPVersionName(){
        PackageManager manager = this.getPackageManager();
        String name = null;
        try{
            PackageInfo info = manager.getPackageInfo(this.getPackageName(),0);
            name = info.packageName;
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }

        return name;
    }

    // TODO: 2019/5/29 返回报错，提示targetVersion的版本过高 
    private String getAPPVersionCode(){
        PackageManager manager = this.getPackageManager();
        long versionCOde = 0;
        try{
            PackageInfo info = manager.getPackageInfo(this.getPackageName(),0);
            versionCOde = info.getLongVersionCode();
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        return String.valueOf(versionCOde);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_html:
                OkHttpManager.getInstance().get(EntireLINK + "&page=1", new Callback() {
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
                        mHomeTimeLine = new Gson().fromJson(repo,HomeTimeLine.class);

                        final String test = mHomeTimeLine.getStatuses().get(0).getUser().getName();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //mResponse.setText(test);
                                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                break;

            case R.id.button_webview:
                //进行授权
                WebViewActivity.start(MainActivity.this, API_URL);

                //查看布局,直接跳转布局，不传token
                //LaunchActivity.start(this,"222");
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
