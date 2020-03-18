package com.example.wbdemo.business.main;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.wbdemo.R;
import com.example.wbdemo.event.EventManager;
import com.example.wbdemo.event.StatusEvent;
import com.example.wbdemo.info.MyItemDecoration;
import com.example.wbdemo.info.maindata.HomeTimeLine;
import com.example.wbdemo.info.maindata.StatusesBean;
import com.example.wbdemo.info.maindata.UserBean;
import com.example.wbdemo.net.OkHttpManager;
import com.example.wbdemo.sqlite.JsonDbHelper;
import com.google.gson.Gson;
import com.lzy.ninegrid.NineGridView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.wbdemo.info.URLInfo.ACCESS_TOKEN_HEADER;
import static com.example.wbdemo.info.URLInfo.HOME_TIMELINE_URL;


public class MainFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TOKEN = "token";

    private String mToken;
    private int mPage;
    private ImageView mMyPortrait;
    private RecyclerView mRecyclerView;
    private MainAdapter mMainAdapter;
    private SmartRefreshLayout mSmartRefresh;

    private HomeTimeLine mHomeTimeLine;
    private List<StatusesBean> mStatusesList = new ArrayList<>();

    private boolean FirstTime = true;

    private JsonDbHelper dbHelper;
    private SQLiteDatabase db;

    public static final String SQL_INSERT = "insert into StatusesBean(" +
            "id,name,portrait,time,content,image,attitude,comment,repost,share)" +
            "values(?,?,?,?,?,?,?,?,?,?)";

    public static final String SQL_SELECT = "select * from StatusesBean";


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

        //TODO:只能解析第一页的问题
        mPage = 1;

        dbHelper = new JsonDbHelper(getContext(), "WbData.db", null, 1);

        db = dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery(SQL_SELECT, null);


        initView(view);

        //判断数据库里是否有数
        if (c.getCount() > 0) {
            getJsonInDatabase();
        } else {
            parseJson();
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    private void initView(View view) {

        initRefreshLayout(view);//初始化刷新布局

        mRecyclerView = view.findViewById(R.id.recyclerview_fg_main);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new MyItemDecoration(getContext(), 15));
        //经验证，可去掉，没啥用;after_descendants:在子控件之后获取焦点
        mRecyclerView.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {

            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemClick(adapter, view, position);
                CommentsActivity.start(getContext(), mStatusesList, position);
            }
        });

        mMainAdapter = new MainAdapter(R.layout.item_launch_main, mStatusesList, getActivity());
        mRecyclerView.setAdapter(mMainAdapter);
        NineGridView.setImageLoader(new GlideImageLoader());//载入九宫格图片基类

    }

    private void initRefreshLayout(View view) {
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


    private void getJsonInDatabase() {
        db = dbHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery(SQL_SELECT, null, null);

        int i = 0;
        if (cursor.moveToFirst()) {
            do {
                StatusesBean statusesBean = new StatusesBean();
                UserBean userBean = new UserBean();

                //1.微博昵称
                userBean.setName(getStringDBData(cursor, "name"));
                //2.微博头像
                userBean.setAvatar_hd(getStringDBData(cursor, "portrait"));

                statusesBean.setUser(userBean);

                //3.发布时间
                statusesBean.setCreated_at(getStringDBData(cursor, "time"));
                //4.内容
                statusesBean.setText(getStringDBData(cursor, "content"));
                //5.点赞次数
                statusesBean.setAttitudes_count(getIntDBData(cursor, "attitude"));
                //6.评论次数
                statusesBean.setShares_count(getIntDBData(cursor, "share"));
                //7.转发次数
                statusesBean.setComments_count(getIntDBData(cursor, "comment"));
                //8.分享次数
                statusesBean.setReposts_count(getIntDBData(cursor, "repost"));


                //9.图片
                String pic = cursor.getString(cursor.getColumnIndex("image"));

                //考虑九格宫，一个字段里有多个图片链接的情况
                List<StatusesBean.PicUrlsBean> pic_List = new ArrayList<>();
                StatusesBean.PicUrlsBean picUrlsBean = new StatusesBean.PicUrlsBean();

                if (pic.contains(",")) {
                    String[] picCollection = pic.split(",");
                    for (int index = 0; index < picCollection.length; index++) {
                        picUrlsBean.setThumbnail_pic(picCollection[index]);
                        pic_List.add(picUrlsBean);
                    }
                } else {
                    picUrlsBean.setThumbnail_pic(pic);
                    pic_List.add(picUrlsBean);
                }

                statusesBean.setPic_urls(pic_List);

                mStatusesList.add(statusesBean);

                i = i + 1;

            } while (cursor.moveToNext());
        }

        mMainAdapter.notifyDataSetChanged();


    }

    //从数据库拿相应行的数据
    private String getStringDBData(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    private int getIntDBData(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    private void parseJson() {
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
                mHomeTimeLine = new Gson().fromJson(json, HomeTimeLine.class);

                if (mHomeTimeLine != null && mHomeTimeLine.getStatuses() != null && !mHomeTimeLine.getStatuses().isEmpty()) {
                    mStatusesList.clear();
                    mStatusesList.addAll(mHomeTimeLine.getStatuses());
                }


                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "success", Toast.LENGTH_LONG).show();
                        mMainAdapter.notifyDataSetChanged();
                        if (FirstTime) {
                            EventManager.getInstance().postEvent(StatusEvent.getInstance(mStatusesList));
                            FirstTime = false;
                        }
                    }
                });

                saveDataToSqLite();
            }
        });
    }


    private void saveDataToSqLite() {
        if (db == null) {
            db = dbHelper.getWritableDatabase();
        }
        //开始事务
        db.beginTransaction();

        Cursor cursor = db.rawQuery(SQL_SELECT, null);

        //删除之前的数据
        if (cursor.getCount() > 0) {
            db.delete("StatusesBean", null, null);
        }


        for (StatusesBean item : mStatusesList) {
            db.execSQL(SQL_INSERT, new Object[]{null, item.getUser().getName(), item.getUser().getAvatar_hd(),
                    item.getCreated_at(), item.getText(), item.getPic_urls(), item.getAttitudes_count(),
                    item.getComments_count(), item.getReposts_count(), item.getShares_count()});

        }

        db.setTransactionSuccessful();
        db.endTransaction();
        //在此处会发生java.lang.IllegalStateException: attempt to re-open an already-closed
        //原因是在刷新时反复对数据库进行操作，而一个线程只能使用一个SQLiteDatabase对象
        //导致A完成读之后调用close(),而B正在进行读写操作
        //解决方法：只在Activity注销或真正不需要数据时关闭数据库
        //db.close();
    }


    private String getURL() {
        String OriURL = HOME_TIMELINE_URL + ACCESS_TOKEN_HEADER + mToken;
        Log.d("zjyy", "getURL: " + mToken);
        return OriURL + "&page=" + mPage;
    }


    private class GlideImageLoader implements NineGridView.ImageLoader {
        @Override
        public void onDisplayImage(Context context, ImageView imageView, String url) {
            Glide.with(context).load(url)//
                    .placeholder(R.drawable.ic_default_image)//
                    .error(R.drawable.ic_default_image)//
                    .into(imageView);
        }

        @Override
        public Bitmap getCacheImage(String url) {
            return null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
