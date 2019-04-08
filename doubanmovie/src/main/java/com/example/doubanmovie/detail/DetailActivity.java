package com.example.doubanmovie.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.example.doubanmovie.R;
import com.example.doubanmovie.model.DetailMode.Detail;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DetailActivity extends AppCompatActivity {

    public static final String MOVIEID_KEY = "movieID";

    private static final String DETAIL_URL_HEAD = "https://api.douban.com/v2/movie/subject/";
    private static final String DETAIL_URL_TAIL = "?apikey=0b2bdeda43b5688921839c8ecb20399b&city=%E5%8C%97%E4%BA%AC&client=something&udid=dddddddddddddddddddddd";
    private String DETAIL_URL = "";

    private RecyclerView mRecyclerview;
    private TopDetailAdapter mAdapter;
    private Detail mData;
    private List<Integer> typeList = new ArrayList<>();

    public static void start(Context context, String movieId) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(MOVIEID_KEY, movieId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        AddReturnButton();
        initparam();
        findView();
        init();
        getURL();
        URLConnection();

    }

    private void AddReturnButton() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initparam() {
        typeList.add(0);
        typeList.add(1);
        typeList.add(2);
    }

    private void findView() {
        mRecyclerview = findViewById(R.id.detail_recyclerview);
    }

    private void init() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(layoutManager);
    }

    private void getURL() {
        Intent intent = getIntent();
        String movieID = intent.getStringExtra(MOVIEID_KEY);
        DETAIL_URL = DETAIL_URL_HEAD + movieID + DETAIL_URL_TAIL;
    }

    private void URLConnection() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(DETAIL_URL).get().build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("zjy", "onFailure:url connection is failed!" + DETAIL_URL);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                analyzeData(json);
                Log.d("zjy", "onResponse: url connection is success!");
            }
        });
    }

    private void analyzeData(String json) {
        mData = new Gson().fromJson(json, Detail.class);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapter = new TopDetailAdapter(typeList, mData, DetailActivity.this);
                mRecyclerview.setAdapter(mAdapter);
            }
        });
    }

}
