package com.example.doubanmovie;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.doubanmovie.model.movie;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String DOUBAN_URL = "https://api.douban.com/v2/movie/in_theaters?apikey=0b2bdeda43b5688921839c8ecb20399b&city=北京&start=0&count=100&client=somemessage&udid=dddddddddddddddddddddd";
    public static final String DOUBAN_URL_TEST = "https://api.douban.com/v2/movie/in_theaters?apikey=0b2bdeda43b5688921839c8ecb20399b&city=北京&start=9&count=100&client=somemessage&udid=dddddddddddddddddddddd";
    TextView mTextPage1;
    TextView mTextPage2;
    private int PAGE = 0;
    private String DOUBAN_URL_FORMAL = "";
    private RecyclerView mRecyclerview;
    private MovieAdapter mMovieAdapter;
    private movie mMovie;
    private ImageView mTopCover;
    private ProgressBar mBeginProgressbar;
    private SmartRefreshLayout mSmartRefreshLayout;
    private MainFragment fg_one, fg_two;
    private FragmentManager mFragmentManager;
    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        mTextPage1.performClick();

    }

    private void findViews() {

        mFragmentManager = getSupportFragmentManager();
        mRelativeLayout = findViewById(R.id.frag_layout);
        mTextPage1 = findViewById(R.id.tv_onepage);
        mTextPage2 = findViewById(R.id.tv_twopage);

        mTextPage1.setOnClickListener(this);
        mTextPage2.setOnClickListener(this);

    }


    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        hideAllFragment(fragmentTransaction);

        switch (v.getId()) {
            case R.id.tv_onepage:
                if (fg_one == null) {
                    fg_one = new MainFragment(R.layout.fg_movie_one);
                    fragmentTransaction.add(R.id.frag_layout, fg_one);
                } else {
                    fragmentTransaction.show(fg_one);
                }

                break;
            case R.id.tv_twopage:
                if (fg_two == null) {
                    fg_two = new MainFragment(R.layout.fg_movie_two);
                    fragmentTransaction.add(R.id.frag_layout, fg_two);
                } else {
                    fragmentTransaction.show(fg_two);
                }

                break;
        }

        fragmentTransaction.commit();
    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (fg_one != null) fragmentTransaction.hide(fg_one);
        if (fg_two != null) fragmentTransaction.hide(fg_two);

    }


}






