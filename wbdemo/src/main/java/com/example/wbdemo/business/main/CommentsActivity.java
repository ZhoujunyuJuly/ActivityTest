package com.example.wbdemo.business.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.FocusFinder;

import com.example.wbdemo.R;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

public class CommentsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ViewPager mViewPager;
    private CommonTabLayout mCommonTabLayout;
    private List<Fragment> mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);


    }

    private void initView(){
        //mRecyclerView = findViewById(R.id.main_comments_recyclerview);
        mViewPager = findViewById(R.id.comments_viewpager);
        mCommonTabLayout = findViewById(R.id.comments_commonLayout);

        loadView();

    }

    private void loadView(){
        mFragment = new ArrayList<>();
        mFragment.add(new CmtsMainFragment());
        mFragment.add(new CmtsMainFragment());
        mFragment.add(new CmtsMainFragment());

        FragmentPagerAdapter fgAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mFragment.get(i);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }
        };

        mViewPager.setAdapter(fgAdapter);
        mCommonTabLayout.setTabData((ArrayList<CustomTabEntity>)loadDate());
        mCommonTabLayout.setIndicatorColor(R.color.UnderlineRed);
        mCommonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }


    private List<CustomTabEntity> loadDate(){
        List<CustomTabEntity> AllData = new ArrayList<>();
        final List<String> InfoTab = new ArrayList<>();
        InfoTab.add("赞245");
        InfoTab.add("评论2345");
        InfoTab.add("转发890");

        for (int i = 0; i < InfoTab.size(); i++) {
            final int index = i;
            AllData.add(new CustomTabEntity() {
                @Override
                public String getTabTitle() {
                    return InfoTab.get(index);
                }

                @Override
                public int getTabSelectedIcon() {
                    return 0;
                }

                @Override
                public int getTabUnselectedIcon() {
                    return 0;
                }
            });
        }

        return AllData;
    }



    private void HeaderView(){

    }

}
