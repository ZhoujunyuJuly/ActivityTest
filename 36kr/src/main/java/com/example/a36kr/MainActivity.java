package com.example.a36kr;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.a36kr.model.News;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String NEWS_URL = "https://36kr.com/api/newsflash";
    TextView mFailureLog;
    private RecyclerView mRecyclerView;
    private NewsAdapter mNewsAdapter;
    private News mNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();
        loadData();
        initView();

    }

    private void findView() {
        mRecyclerView = findViewById(R.id.recyclerview);
    }


    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void loadData() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(NEWS_URL).get().build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                showrespose();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                parseComplexJson(json);

            }
        });

    }


    private void click() {
        mNewsAdapter.setmOnItemClickListen(new OnItemClickListen() {
            @Override
            public void OnNewsClick(View view, int position) {

                String address = mNews.getData().getItems().get(position).getNews_url();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(address));
                startActivity(intent);

                Log.d("zhoujunyu", "OnNewsClick:success! ");
            }
        });
    }


    private void parseComplexJson(String json) {
        mNews = new Gson().fromJson(json, News.class);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mNews != null && mNews.getData() != null && mNews.getData().getItems() != null) {
                    mNewsAdapter = new NewsAdapter(mNews.getData().getItems(), MainActivity.this);
                }else {
                    // TODO: 2019/3/14 toast提示
                }
                mRecyclerView.setAdapter(mNewsAdapter);
                click();
            }
        });

    }


    private void showrespose() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mFailureLog.setText("failure");
            }
        });
    }


}
