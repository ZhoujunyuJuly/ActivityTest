package com.example.wbdemo.FunctionModule.Main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.wbdemo.Object.EventTransStatusBean;
import com.example.wbdemo.Object.MainFgData.HomeTimeLine;
import com.example.wbdemo.Object.MainFgData.StatusesBean;
import com.example.wbdemo.R;
import com.example.wbdemo.net.OkHttpManager;
import com.google.gson.Gson;
import com.lzy.ninegrid.NineGridView;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.wbdemo.Object.URLInfo.HOME_TIMELINE_URL;


public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TOKEN = "token";

    // TODO: Rename and change types of parameters
    private String mToken;
    private ImageView mMyPortrait;
    private RecyclerView mRecyclerView;
    private MainAdapter mMainAdapter;

    private HomeTimeLine mHomeTimeLine;
    private List<StatusesBean> mStatusesList = new ArrayList<>();



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

        parseJson();
        initView(view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    private void initView(View view){
        mRecyclerView = view.findViewById(R.id.recyclerview_fg_main);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = 40;
                if (parent.getChildAdapterPosition(view) == 0) {
                    outRect.top = 40;
                }
            }
        });

        mMainAdapter = new MainAdapter(R.layout.launch_main_item,mStatusesList,getActivity());
        mRecyclerView.setAdapter(mMainAdapter);
        NineGridView.setImageLoader(new PicassoImageLoader());
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
                        EventBus.getDefault().post(new EventTransStatusBean(mStatusesList));
                    }
                });
            }
        });
    }


    private String getURL(){
        return HOME_TIMELINE_URL + "?access_token=" + mToken;
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
