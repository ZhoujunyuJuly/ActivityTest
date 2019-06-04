package com.example.wbdemo.business.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.example.wbdemo.info.URLInfo.COMMENTS_URL;

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

        SharedPreferences pref = getActivity().getSharedPreferences("access_token", MODE_PRIVATE);
        mToken = pref.getString("access_token", "");

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
                switch (view.getId()) {
                    case R.id.iv_comments_attitude:
                    case R.id.tv_comments_attitudeCounts:
//                        view.setFocusable(true);
//                        view.setFocusableInTouchMode(true);

                       View view_item = LayoutInflater.from(getActivity()).inflate(R.layout.item_comments,null);

                       view_item.requestFocus();

                       mAttitude = view_item.findViewById(R.id.iv_comments_attitude);
                       mAttitudeCounts = view_item.findViewById(R.id.tv_comments_attitudeCounts);
//                        if (!isChosen) {
//                            mAttitude.setImageResource(R.mipmap.chosen_attitude);
//                            int counts = Integer.parseInt(mAttitudeCounts.getText().toString()) + 1;
//                            mAttitudeCounts.setText(String.valueOf(counts));
//                            isChosen = true;
//                        } else {
//                            mAttitude.setImageResource(R.mipmap.attitudes);
//                            int counts = Integer.parseInt(mAttitudeCounts.getText().toString()) - 1;
//                            mAttitudeCounts.setText(String.valueOf(counts));
//                            isChosen = false;
//                        }
                        mAttitude.setImageResource(R.mipmap.chosen_attitude);
                        mAttitudeCounts.setText("333");
                        Toast.makeText(getContext(),"点击事件有效",Toast.LENGTH_LONG).show();
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

                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.notifyDataSetChanged();
                        }
                    });
                }

                EventManager.getInstance().postEvent(CommentsEvent.CommentsEvent(mCommentsList));
            }
        });
    }

    private String getURL() {
        return COMMENTS_URL + "?access_token=" + mToken + "&id=" + mWeiboID;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
