package com.example.doubanmovie.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.doubanmovie.R;
import com.example.doubanmovie.model.Movie;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,ViewPager.OnPageChangeListener,TransData {
    public static final String DOUBAN_URL = "https://api.douban.com/v2/movie/in_theaters?apikey=0b2bdeda43b5688921839c8ecb20399b&city=北京&start=0&count=100&client=somemessage&udid=dddddddddddddddddddddd";
    public static final String DOUBAN_URL_TEST = "https://api.douban.com/v2/movie/in_theaters?apikey=0b2bdeda43b5688921839c8ecb20399b&city=北京&start=9&count=100&client=somemessage&udid=dddddddddddddddddddddd";
   // TextView mTextPage1;
    //TextView mTextPage2;
    private int PAGE = 0;
    private String DOUBAN_URL_FORMAL = "";
    private RecyclerView mRecyclerview;
    private MovieAdapter mMovieAdapter;
    private Movie mMovie;
    private ImageView mTopCover;
    private ProgressBar mBeginProgressbar;
    private SmartRefreshLayout mSmartRefreshLayout;
    private MovieFragment fg_moive;
    private TextFragment fg_text ;
    private FragmentManager mFragmentManager;
    private RelativeLayout mRelativeLayout;

    private ViewPager mViewPager;
    private FragmentPagerAdapter mFragmentPagerAdapter;
    private Spinner mspinner;
    private String[] mBannerImgURL = new String[5];
    private String[] mMovieName = new String[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_main);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.spinner);


        findViews();
        //mTextPage1.performClick();

         loadBanner();

    }

    public Spinner getSpinner(){
        mspinner = findViewById(R.id.spinner_city);
        return mspinner;
    }

    private void loadBanner(){
        //延迟3秒，直到json解析完数据
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment fragment = new BannerFragment();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.add(R.id.ad_layout,fragment).commit();
            }
        }, 5000);//3秒后执行Runnable中的run方法

    }

    @Override
    public void moviefgToBannerfg(String[] imgURL,String[] movieName) {
        this.mBannerImgURL = imgURL;
        this.mMovieName = movieName;
    }

    public String[] getmBannerImgURL(){
        return mBannerImgURL;
        //String[] imgURL = {"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2551352209.jpg","0"};
        //return imgURL;
    }

    public String[] getmMovieName(){
        return mMovieName;
    }


    private void findViews() {
        mFragmentPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());

//        mTextPage1 = findViewById(R.id.tv_onepage);
      //  mTextPage2 = findViewById(R.id.tv_twopage);

        mViewPager = findViewById(R.id.vpager);
        mViewPager.setAdapter(mFragmentPagerAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(MainActivity.this);

//        mTextPage1.setOnClickListener(this);
       // mTextPage2.setOnClickListener(this);

    }





    public void onClick(View v) {
//        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
//        hideAllFragment(fragmentTransaction);

       // switch (v.getId()) {
        //    case R.id.tv_onepage:
//                if (fg_moive == null) {
//                    fg_moive = MovieFragment.mainFragment(R.layout.fg_movie_one);
//                    fragmentTransaction.add(R.id.vpager, fg_moive);
//                } else {
//                    fragmentTransaction.show(fg_moive);
//
                mViewPager.setCurrentItem(0);
                //loadBanner();

           //     break;
           // case R.id.tv_twopage:
//                if (fg_text == null) {
//                    fg_text = new TextFragment();
//                    fragmentTransaction.add(R.id.vpager, fg_text);
//                } else {
//                    fragmentTransaction.show(fg_text);
//                }
                mViewPager.setCurrentItem(1);

             //   break;
       // }
//        fragmentTransaction.commit();
    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (fg_moive != null) fragmentTransaction.hide(fg_moive);
        if (fg_text != null) fragmentTransaction.hide(fg_text);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

}






