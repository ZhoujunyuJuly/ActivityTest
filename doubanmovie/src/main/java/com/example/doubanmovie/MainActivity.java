package com.example.doubanmovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.example.doubanmovie.model.movie;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public static final String DOUBAN_URL = "https://api.douban.com/v2/movie/in_theaters?apikey=0b2bdeda43b5688921839c8ecb20399b&city=北京&start=0&count=100&client=somemessage&udid=dddddddddddddddddddddd";
    public static final String DOUBAN_URL_TEST = "https://api.douban.com/v2/movie/in_theaters?apikey=0b2bdeda43b5688921839c8ecb20399b&city=北京&start=9&count=100&client=somemessage&udid=dddddddddddddddddddddd";

    private int PAGE = 0;
    private String DOUBAN_URL_FORMAL ="";

    private RecyclerView mRecyclerview;
    private MovieAdapter mMovieAdapter;
    private movie mMovie;

    private ImageView mTopCover;
    private ProgressBar mBeginProgressbar;
    private SmartRefreshLayout mSmartRefreshLayout;


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
        mTopCover = findViewById(R.id.tv_top_cover);
        mBeginProgressbar = findViewById(R.id.progressbar_begin);
        mSmartRefreshLayout = findViewById(R.id.smart_refresh);
    }

    private void init() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(layoutManager);
    }

    private void URLConnection() {

        GetPage();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(DOUBAN_URL_FORMAL).get().build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("zhoujunyu", "onFailure: connection failed!");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                parseData(json);
                Log.d("zjy", "onResponse: success!");

            }
        });
    }

    private void parseData(String json) {
        mMovie = new Gson().fromJson(json, movie.class);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mMovieAdapter = new MovieAdapter(mMovie.getSubjects(), MainActivity.this);
                mRecyclerview.setAdapter(mMovieAdapter);

                mBeginProgressbar.setVisibility(View.GONE);

                setRecylcerviewListener();
                setSmartRefreshLayout();
            }
        });
    }


    private void setRecylcerviewListener() {
        mMovieAdapter.setOnItemClickListen(new IRecyclerviewClick() {
            @Override
            public void onMovieClick(View view, int position) {

                if (mTopCover.getDrawable() != null) {
                    mTopCover.setImageResource(0);
                } else {

                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);

                    String movidID = mMovie.getSubjects().get(position).getId();
                    intent.putExtra("movieID", movidID);

                    startActivity(intent);
                }
            }

            @Override
            public void onCoverClick(View view, int position) {

                Glide.with(MainActivity.this)
                        .load(mMovie.getSubjects().get(position).getImages().getMedium())
                        .centerCrop()
                        .placeholder(R.mipmap.ic_launcher)
                        .into(mTopCover);

                // TODO: 2019/3/25 图片点击放顶部视图，参考
                //对imageview图片进行放大缩小，不要删！！！！！！留着看
//                ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(cover.getLayoutParams());
//                marginLayoutParams.leftMargin = 140;
//                marginLayoutParams.topMargin = 240;
//                RelativeLayout.MarginLayoutParams layoutParams = new RelativeLayout.LayoutParams(marginLayoutParams);
//                layoutParams.height = 350;
//                layoutParams.width = 300;
//                cover.setLayoutParams(layoutParams);
//
//                cover.setScaleType(ImageView.ScaleType.FIT_XY);

                //放至顶层
//                cover.bringToFront();


            }
        });
    }

    private void setSmartRefreshLayout(){
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                URLConnection();
                mSmartRefreshLayout.finishRefresh(2000);
            }
        });

        mSmartRefreshLayout.setOnLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                URLConnection();
                mSmartRefreshLayout.finishLoadMore(2000);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

            }
        });
    }



    private void GetPage(){
        DOUBAN_URL_FORMAL = DOUBAN_URL.replace("start=0","start=" + PAGE);
        PAGE += 1;
        Log.d("zjy", "douban_url is "+ DOUBAN_URL_FORMAL);
    }



}






