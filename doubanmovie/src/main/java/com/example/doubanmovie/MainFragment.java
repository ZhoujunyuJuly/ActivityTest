package com.example.doubanmovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.doubanmovie.model.movie;
import com.example.doubanmovie.net.OkHttpManager;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by zhoujunyu on 2019/3/26.
 */
public class MainFragment extends Fragment {


    public static final String DOUBAN_URL = "https://api.douban.com/v2/movie/in_theaters?apikey=0b2bdeda43b5688921839c8ecb20399b&city=北京&start=0&count=100&client=somemessage&udid=dddddddddddddddddddddd";

    private int PAGE = 0;
    private String DOUBAN_URL_FORMAL = "";

    private MovieAdapter mMovieAdapter;
    private movie mMovie;

    private int mLayoutType;
    private RecyclerView mRecyclerView;
    private ProgressBar mBeginProgressbar;
    private SmartRefreshLayout mSmartRefreshLayout;

    public MainFragment(int layoutType) {
        mLayoutType = layoutType;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(mLayoutType, container, false);
        if (mLayoutType == R.layout.fg_movie_one) {
            mRecyclerView = view.findViewById(R.id.pageone_recycler_view);
            mBeginProgressbar = view.findViewById(R.id.progressbar_begin);
            mSmartRefreshLayout = view.findViewById(R.id.smart_refresh);

        } else {
            TextView textView = view.findViewById(R.id.pagetwo);
            textView.setText("你点到第二页了！");
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (mLayoutType == R.layout.fg_movie_one) {
            findViews();
            init();
        }

    }

    private void findViews() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void init() {
        URLConnection();
    }

    private void URLConnection() {

        GetPage();
        OkHttpManager.getInstance().get(DOUBAN_URL_FORMAL, null, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("zjy", "singleton is failed!");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                parseData(json);
                Log.d("zjy", "singleton connection is success!");
            }
        });
    }


    private void parseData(String json) {
        mMovie = new Gson().fromJson(json, movie.class);

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mMovieAdapter = new MovieAdapter(mMovie.getSubjects(), getActivity());
                mRecyclerView.setAdapter(mMovieAdapter);

                mBeginProgressbar.setVisibility(View.GONE);

                setRecylcerviewListener();
                setSmartRefreshLayout();
            }
        });
    }


    private void setSmartRefreshLayout() {
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


    private void setRecylcerviewListener() {
        mMovieAdapter.setOnItemClickListen(new IRecyclerviewClick() {
            @Override
            public void onMovieClick(View view, int position) {

                // TODO: 2019/3/27 不能删，删了就忘了！
                //if (mTopCover.getDrawable() != null) {
                // mTopCover.setImageResource(0);
                //} else {

                Intent intent = new Intent(getActivity(), DetailActivity.class);

                String movidID = mMovie.getSubjects().get(position).getId();
                intent.putExtra("movieID", movidID);

                startActivity(intent);
                //}
            }

            @Override
            public void onCoverClick(View view, int position) {

                String IMG_URL = mMovie.getSubjects().get(position).getImages().getMedium();
                Intent intent = new Intent(getActivity(), WatchIMGActivity.class);

                intent.putExtra("IMG_URL", IMG_URL);
                startActivity(intent);
            }

            //@Override
//            public void onCoverClick(View view, int position) {
//
//                Glide.with(MainActivity.this)
//                        .load(mMovie.getSubjects().get(position).getImages().getMedium())
//                        .centerCrop()
//                        .placeholder(R.mipmap.ic_launcher)
//                        .into(mTopCover);

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


            // }
        });
    }


    private void GetPage() {
        DOUBAN_URL_FORMAL = DOUBAN_URL.replace("start=0", "start=" + PAGE);
        PAGE += 1;
        Log.d("zjy", "douban_url is " + DOUBAN_URL_FORMAL);
    }


}
