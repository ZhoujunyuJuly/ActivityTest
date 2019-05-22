package com.example.wbdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wbdemo.net.OkHttpManager;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.wbdemo.Object.URLInfo.HOME_TIMELINE_URL;
import static com.example.wbdemo.Object.URLInfo.TOKEN_TAG;

public class LaunchActivity extends AppCompatActivity {

    private String mToken;
    private TextView mTv_Json;

    public static void start(Context context,String token) {
        Intent intent = new Intent(context, LaunchActivity.class);
        intent.putExtra(TOKEN_TAG,token);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        initView();
        parseJson();
    }

    private void initView(){
        mTv_Json = findViewById(R.id.tv_json_lanuch);
        mTv_Json.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    private void parseJson(){
        OkHttpManager.getInstance().get(getURL(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"error okhttp",Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTv_Json.setText(json);
                        Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
                        Log.d("zjy", "lanuch json is " + json);
                    }
                });
            }
        });
    }

    private String getURL(){
        mToken = getIntent().getStringExtra(TOKEN_TAG);
        Log.d("zjy", "getURL: " + HOME_TIMELINE_URL + "?access_token=" + mToken);
        return HOME_TIMELINE_URL + "?access_token=" + mToken;
    }
}
