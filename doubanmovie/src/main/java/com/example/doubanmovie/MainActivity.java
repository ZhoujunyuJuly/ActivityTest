package com.example.doubanmovie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.doubanmovie.model.SubjectsBean;
import com.example.doubanmovie.model.movie;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public static final String DOUBAN_URL = "https://api.douban.com/v2/movie/in_theaters?apikey=0b2bdeda43b5688921839c8ecb20399b&city=北京&start=0&count=100&client=somemessage&udid=dddddddddddddddddddddd";
    private RecyclerView mRecyclerview;
    private MovieAdapter mMovieAdapter;
    private movie mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        init();
        URLConnection();


    }

    private void findViews() {
        mRecyclerview = findViewById(R.id.recycler_view);
    }

    private void init() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(layoutManager);
    }

    private void URLConnection() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(DOUBAN_URL).get().build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("zhoujunyu", "onFailure: connection failed!");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                interpretData(json);
                Log.d("zjy", "onResponse: success!");

            }
        });
    }

    private void interpretData(String json){
        mMovie = new Gson().fromJson(json,movie.class);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mMovieAdapter = new MovieAdapter(mMovie.getSubjects(),MainActivity.this);
                mRecyclerview.setAdapter(mMovieAdapter);
            }
        });
    }
}






