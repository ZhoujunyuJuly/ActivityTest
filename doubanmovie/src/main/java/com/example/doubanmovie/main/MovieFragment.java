package com.example.doubanmovie.main;

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
import android.widget.Toast;

import com.example.doubanmovie.C;
import com.example.doubanmovie.R;
import com.example.doubanmovie.detail.DetailActivity;
import com.example.doubanmovie.model.SubjectsBean;
import com.example.doubanmovie.model.movie;
import com.example.doubanmovie.net.OkHttpManager;
import com.example.doubanmovie.preview.WatchIMGActivity;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by zhoujunyu on 2019/3/26.
 */
public class MovieFragment extends Fragment {

    private static int PAGE = 0;
    private static int PAGE_COUNT = 20;

    private MovieAdapter mMovieAdapter;
    private movie mMovie;

    private List<SubjectsBean> mSubjectsList = new ArrayList<>();

    private int mLayoutType;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSmartRefreshLayout;

    private static final String TYPE = "type";

    public MovieFragment() {

    }

    //工厂设计模式
    public static MovieFragment mainFragment(int layoutType){
        MovieFragment fragment = new MovieFragment();
        Bundle args = new Bundle();
        args.putInt(TYPE,layoutType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if( getArguments() != null){
            mLayoutType = getArguments().getInt(TYPE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_movie_one, container, false);
        mRecyclerView = view.findViewById(R.id.pageone_recycler_view);
        mSmartRefreshLayout = view.findViewById(R.id.smart_refresh);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

        //加载刷新界面
        setSmartRefreshLayout();
        mSmartRefreshLayout.autoRefresh();

        //载入适配器，以便拉取本地数据
        mMovieAdapter = new MovieAdapter(mSubjectsList, getActivity());
        mRecyclerView.setAdapter(mMovieAdapter);

        setRecylcerviewListener();

        return view;
    }

    private void loadNew() {
        PAGE = 0;
        loadPage(PAGE);
    }

    private void loadMore() {
        PAGE = PAGE + 1;
        loadPage(PAGE);
    }

    private void loadPage(final int page) {
        Map<String, String> map = new HashMap<>();
        map.put("apikey", "0b2bdeda43b5688921839c8ecb20399b");
        map.put("city", "北京");
        map.put("start", String.valueOf(page * PAGE_COUNT));

        //每次渲染的item数据
        map.put("count", String.valueOf(PAGE_COUNT));

        map.put("client","somemessage");
        map.put("udid","dddddddddddddddddddddd");

        OkHttpManager.getInstance().get(C.DOUBAN_URL, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("zjy", "singleton is failed!");
                finishRefresh();//完成刷新
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                mMovie = new Gson().fromJson(json, movie.class);

                //判断界面item数大于总item数时，无新数据；扔回主线程
                if (mMovie.getTotal() < PAGE  * PAGE_COUNT) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(),"没有更多数据了", Toast.LENGTH_LONG).show();
                            finishRefresh();
                        }
                    });

                }else {
                    if (mMovie != null && mMovie.getSubjects() != null && !mMovie.getSubjects().isEmpty()) {

                        if (PAGE == 0) {
                            mSubjectsList.clear();

                            //更新数据
                            mSubjectsList.addAll(mMovie.getSubjects());
                        } else {
                            mSubjectsList.addAll(mMovie.getSubjects());
                        }
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mMovieAdapter.notifyDataSetChanged();
                            finishRefresh();
                        }
                    });

                }
            }
        });
    }

    private void finishRefresh() {
        if (PAGE == 0) {
            mSmartRefreshLayout.finishRefresh();
        }else {
            mSmartRefreshLayout.finishLoadMore();
        }
    }

    private void setSmartRefreshLayout() {
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                loadNew();
            }
        });
        mSmartRefreshLayout.setOnLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                loadNew();
            }
        });
    }

    private void setRecylcerviewListener() {
        mMovieAdapter.setOnItemClickListen(new IRecyclerviewClick() {
            @Override
            public void onMovieClick(View view, SubjectsBean subjectsBean) {
                DetailActivity.start(getActivity(), subjectsBean.getId());
            }

            @Override
            public void onCoverClick(View view, SubjectsBean subjectsBean) {
                // TODO: 2019/3/30 仿照上面写法自己写一个
                WatchIMGActivity.start(getActivity(),subjectsBean.getImages().getMedium());
            }
        });

    }


}
