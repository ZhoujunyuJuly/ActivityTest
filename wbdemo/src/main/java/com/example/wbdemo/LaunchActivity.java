package com.example.wbdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wbdemo.net.OkHttpManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.wbdemo.Object.URLInfo.CLIENT_ID;
import static com.example.wbdemo.Object.URLInfo.CLIENT_SECRET;
import static com.example.wbdemo.Object.URLInfo.GRANT_TYPE;
import static com.example.wbdemo.Object.URLInfo.HEADER_ACCESS;
import static com.example.wbdemo.Object.URLInfo.REDIRECT_URL;

public class LaunchActivity extends AppCompatActivity {

    public static final String TAG_TOKRN = "token_url";
    private TextView mTextJson;
    private String CODE;

    public static void start(Context context, String code){
        Intent intent = new Intent(context,WebViewActivity.class);
        intent.putExtra(TAG_TOKRN,code);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        initView();
        getJson();

    }

    private void initView(){
        mTextJson = findViewById(R.id.token);
        mTextJson.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    private void getJson(){
        OkHttpManager.getInstance().post(HEADER_ACCESS, getPost(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().toString();
                mTextJson.setText(json);
                Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
            }
        });
    }

    private Map getPost(){
        Map<String,String> map = new HashMap<>();
        map.put("client_id",CLIENT_ID);
        map.put("client_secret",CLIENT_SECRET);
        map.put("grant_type",GRANT_TYPE);
        map.put("redirect_uri",REDIRECT_URL);
        map.put("code=",getCODE());

        return map;
    }

    private String getCODE(){
        Intent intent = getIntent();
        CODE = intent.getStringExtra(TAG_TOKRN);
        return CODE;
    }
}
