package com.example.doubanmovie.main;

import android.content.Context;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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
    private Spinner mSpinner;
    private String mCity = "北京";

    private static final String TYPE = "type";
    public TransData mTransData;



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
        refreshCity();

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            mTransData = (TransData)context;
        }catch (Exception e){
            throw new ClassCastException(context.toString() + "must implement OnArticleSelectedListener");
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void refreshCity() {

        mSpinner = ((MainActivity) getActivity()).getSpinner();

        String[] city = getResources().getStringArray(R.array.city);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, city);//创建Arrayadapter适配器

        mSpinner.setAdapter(adapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mCity = mSpinner.getItemAtPosition(position).toString();
                //Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
                setSmartRefreshLayout();
                mSmartRefreshLayout.autoRefresh();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
        map.put("city", mCity);
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

                        String[] imgURL = new String[5];
                        String[] movieName = new String[5];
                        for (int i = 0; i < 5; i++) {
                            //imgURL[i] = mSubjectsList.get(i).getImages().getSmall();
                            Log.d("zjy", "onResponse: subjects is "+ mMovie.getSubjects().get(i).getImages().getSmall());
                            imgURL[i] = mMovie.getSubjects().get(i).getImages().getSmall();
                            movieName[i] = mMovie.getSubjects().get(i).getTitle();

                           // Log.d("zjy", "onActivityCreated: value is "+imgURL[i]);
                        }



//        mTransData.moviefgToBannerfg(imgURL);
                        //String[] imgURL = {"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2551352209.jpg","0"};

                        mTransData.moviefgToBannerfg(imgURL,movieName);
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
