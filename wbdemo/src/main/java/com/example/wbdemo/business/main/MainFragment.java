package com.example.wbdemo.business.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.wbdemo.event.EventManager;
import com.example.wbdemo.event.StatusEvent;
import com.example.wbdemo.info.maindata.HomeTimeLine;
import com.example.wbdemo.info.maindata.StatusesBean;
import com.example.wbdemo.R;
import com.example.wbdemo.net.OkHttpManager;
import com.google.gson.Gson;
import com.lzy.ninegrid.NineGridView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.wbdemo.info.URLInfo.HOME_TIMELINE_URL;


public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TOKEN = "token";

    // TODO: Rename and change types of parameters
    private String mToken;
    private int mPage;
    private ImageView mMyPortrait;
    private RecyclerView mRecyclerView;
    private MainAdapter mMainAdapter;
    private SmartRefreshLayout mSmartRefresh;

    private HomeTimeLine mHomeTimeLine;
    private List<StatusesBean> mStatusesList = new ArrayList<>();

    private boolean FirstTime = true;



    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String token) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(TOKEN, token);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mToken = getArguments().getString(TOKEN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mPage = 1;
        parseJson();
        initView(view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    private void initView(View view){

        initRefreshLayout(view);//初始化刷新布局

        mRecyclerView = view.findViewById(R.id.recyclerview_fg_main);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = 40;//item间间隔
                if (parent.getChildAdapterPosition(view) == 0) {
                    outRect.top = 40;
                }
            }
        });
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {

            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemChildClick(adapter, view, position);
                switch (view.getId()){
                    case R.id.layout_comments :
                    case R.id.layout_attitude :
                    case R.id.layout_repost :
                    case R.id.layout_share :

                        ArrayList<Integer> count = new ArrayList<>();
                        count.add(mStatusesList.get(position).getAttitudes_count());
                        count.add(mStatusesList.get(position).getComments_count());
                        count.add(mStatusesList.get(position).getReposts_count());

                        View view_bottom = View.inflate(getContext(),R.layout.item_launch_main_bottom,null);
                        TextView shareCounts = view_bottom.findViewById(R.id.tv_item_share);
                        count.add(Integer.parseInt(shareCounts.getText().toString()));

                        CommentsActivity.start(getContext(),mStatusesList.get(position).getIdstr(),count);
                        break;
                }
            }
        });

        mMainAdapter = new MainAdapter(R.layout.item_launch_main,mStatusesList,getActivity());
        mRecyclerView.setAdapter(mMainAdapter);
        NineGridView.setImageLoader(new PicassoImageLoader());//载入九宫格图片基类

    }

    private void initRefreshLayout(View view){
        mSmartRefresh = view.findViewById(R.id.fg_main_refreshlayout);
        mSmartRefresh.setRefreshHeader(new ClassicsHeader(getContext()));
        mSmartRefresh.setRefreshFooter(new ClassicsFooter(getContext()));
        mSmartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPage = 1;
                parseJson();
                mSmartRefresh.finishRefresh(1000);
            }
        });

        mSmartRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPage = 1;
                parseJson();
                mSmartRefresh.finishLoadMore(1000);
            }
        });
    }

    private void parseJson(){
        OkHttpManager.getInstance().get(getURL(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "error okhttp", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();
                mHomeTimeLine = new Gson().fromJson(json,HomeTimeLine.class);

                if( mHomeTimeLine != null && mHomeTimeLine.getStatuses() != null && !mHomeTimeLine.getStatuses().isEmpty()){
                    mStatusesList.clear();
                    mStatusesList.addAll(mHomeTimeLine.getStatuses());
                }


                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "success", Toast.LENGTH_LONG).show();
                            mMainAdapter.notifyDataSetChanged();
                            if(FirstTime) {
                                EventManager.getInstance().postEvent(StatusEvent.getInstance(mStatusesList));
                                FirstTime = false;
                            }
                    }
                });
            }
        });
    }


    private String getURL(){
        String OriURL = HOME_TIMELINE_URL + "?access_token=" + mToken;
        Log.d("zjyy", "getURL: " + mToken);
        return OriURL + "&page=" + mPage;
    }


    private class PicassoImageLoader implements NineGridView.ImageLoader {
        @Override
        public void onDisplayImage(Context context, ImageView imageView, String url) {
            Picasso.with(context).load(url)//
                    .placeholder(R.drawable.ic_default_image)//
                    .error(R.drawable.ic_default_image)//
                    .into(imageView);
        }
        @Override
        public Bitmap getCacheImage(String url) {
            return null;
        }
    }
}
