package com.example.wbdemo.business.main;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
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
import com.example.wbdemo.R;
import com.example.wbdemo.event.CommentsEvent;
import com.example.wbdemo.event.EventManager;
import com.example.wbdemo.info.commentdata.Comments;
import com.example.wbdemo.info.commentdata.CommentsBean;
import com.example.wbdemo.net.OkHttpManager;
import com.example.wbdemo.sqlite.JsonDbHelper;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.spec.EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleBiFunction;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.example.wbdemo.info.URLInfo.ACCESS_TOKEN_HEADER;
import static com.example.wbdemo.info.URLInfo.COMMENTS_URL;
import static com.example.wbdemo.info.URLInfo.TOKEN;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CmtsMainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CmtsMainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CmtsMainFragment extends Fragment {

    private static final String TAG_WBID = "weiboID";
    public static final String ACCESS_TOKEN = "access_token";

    private String mWeiboID;
    private String mToken;
    private RecyclerView mRecyclerView;
    private ImageView mAttitude;
    private TextView mAttitudeCounts;
    private CommentsAdapter mAdapter;
    private Comments mComments;
    private boolean isChosen = false;
    private List<CommentsBean> mCommentsList = new ArrayList<>();



    public CmtsMainFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CmtsMainFragment newInstance(String weiboID) {
        CmtsMainFragment fragment = new CmtsMainFragment();
        Bundle args = new Bundle();
        args.putString(TAG_WBID, weiboID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mWeiboID = getArguments().getString(TAG_WBID);
        }

        SharedPreferences pref = getActivity().getSharedPreferences(ACCESS_TOKEN, MODE_PRIVATE);
        mToken = pref.getString(ACCESS_TOKEN, TOKEN);//获取存好的token，如果没有，默认指为INFO类中已存好的值

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cmts_main, container, false);

        mRecyclerView = view.findViewById(R.id.fg_comments_recyclerview);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//又忘记写这个了！！！

        mAdapter = new CommentsAdapter(R.layout.item_comments, mCommentsList, getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemChildClick(adapter, view, position);
                mAttitudeCounts = mRecyclerView.getChildAt(position).findViewById(R.id.tv_comments_attitudeCounts);
                mAttitude = mRecyclerView.getChildAt(position).findViewById(R.id.iv_comments_attitude);
                switch (view.getId()) {
                    case R.id.tv_comments_attitudeCounts:
                    case R.id.iv_comments_attitude:
                        if (!isChosen) {
                            int counts = mCommentsList.get(position).getUser().getFavourites_count() + 1;
                            String count = String.valueOf(counts);

                            mAttitudeCounts.setText(count);
                            mAttitude.setImageResource(R.mipmap.chosen_attitude);
                            isChosen = true;

                        } else {
                            int counts = mCommentsList.get(position).getUser().getFavourites_count();
                            String count = String.valueOf(counts);

                            mAttitudeCounts.setText(count);
                            mAttitude.setImageResource(R.mipmap.attitudes);
                            isChosen = false;
                        }


                        //TODO:使用notifydatasetchange()导致图片重置无效
//                        mCommentsList.get(position).getUser().setFavourites_count(mCommentsList.get(position).getUser().getFavourites_count() + 1);
//                        mAdapter.notifyDataSetChanged();

                        break;
                }
            }
        });

        parseJson();

        return view;
    }

    private void parseJson() {
        OkHttpManager.getInstance().get(getURL(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                mComments = new Gson().fromJson(json, Comments.class);
                mCommentsList.clear();
                mCommentsList.addAll(mComments.getCommentsBeans());



                Log.d("zjyy", "comments is  " + json);

                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.notifyDataSetChanged();
                        }
                    });
                }

                //EventManager.getInstance().postEvent(CommentsEvent.CommentsEvent(mCommentsList));
            }
        });
    }

    private String getURL() {
        String URL = COMMENTS_URL + ACCESS_TOKEN_HEADER + mToken + "&id=" + mWeiboID;
        try {
            String DecodeURL = URLDecoder.decode(URL,"UTF-8");
            return DecodeURL;
        }
        catch (Exception e){
            Log.e("","toURLEncode error of CmtsMainFragment");
            Toast.makeText(getContext(),"toURLEncode error of CmtsMainFragment",Toast.LENGTH_LONG).show();
        }
        return "";
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
