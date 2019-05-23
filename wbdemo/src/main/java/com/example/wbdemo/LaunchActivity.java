package com.example.wbdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wbdemo.FunctionModule.FindFragment;
import com.example.wbdemo.FunctionModule.InfoFragment;
import com.example.wbdemo.FunctionModule.MainFragment;
import com.example.wbdemo.FunctionModule.SettingFragment;
import com.example.wbdemo.net.OkHttpManager;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.wbdemo.Object.URLInfo.HOME_TIMELINE_URL;
import static com.example.wbdemo.Object.URLInfo.TOKEN_TAG;

public class LaunchActivity extends AppCompatActivity implements ViewPager.OnClickListener {

    public static final int TAB_MAIN = 0;
    public static final int TAB_FIND = 1;
    public static final int TAB_INFO = 2;
    public static final int TAB_SETTING = 3;


    private String mToken;
    private ViewPager mViewPager;
    private ImageView mMainTab;
    private ImageView mFindTab;
    private ImageView mInfoTab;
    private ImageView mSettingTab;
    private ImageView mTopPortrait;
    private Spinner mTopTab;

    private List<Fragment> mFragments;
    private FragmentPagerAdapter mFragPagerAdapter;

    private TextView mTvPrintJson;

    public static void start(Context context,String token) {
        Intent intent = new Intent(context, LaunchActivity.class);
        intent.putExtra(TOKEN_TAG,token);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.activity_launch);
//
//        initView();
//        initData();


        //查看api
        setContentView(R.layout.print_json);
        mTvPrintJson = findViewById(R.id.tv_print_json);
        parseJson();

    }

    private void initView(){
        mViewPager = findViewById(R.id.launch_viewpager);

        mMainTab = findViewById(R.id.iv_main_tab);
        mFindTab = findViewById(R.id.iv_find_tab);
        mInfoTab = findViewById(R.id.iv_info_tab);
        mSettingTab = findViewById(R.id.iv_setting_tab);
        mTopPortrait = findViewById(R.id.iv_top_portrait);
        mTopTab = findViewById(R.id.launch_top_tab);

        mMainTab.setOnClickListener(this);
        mFindTab.setOnClickListener(this);
        mInfoTab.setOnClickListener(this);
        mSettingTab.setOnClickListener(this);
//        mTopTab.setOnClickListener(this);

    }

    private void initData(){
        mFragments = new ArrayList<>();

        mFragments.add(new MainFragment());
        mFragments.add(new FindFragment());
        mFragments.add(new InfoFragment());
        mFragments.add(new SettingFragment());

        mFragPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mFragments.get(i);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };

        mViewPager.setAdapter(mFragPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mViewPager.setCurrentItem(i);
                resetImg();
                setTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        mMainTab.performClick();
    }

    @Override
    public void onClick(View v) {
        resetImg();

        switch (v.getId()){
            case R.id.iv_main_tab:
                mViewPager.setCurrentItem(TAB_MAIN);
                setTab(TAB_MAIN);
                break;
            case R.id.iv_find_tab:
                mViewPager.setCurrentItem(TAB_FIND);
                setTab(TAB_FIND);
                break;
            case R.id.iv_info_tab:
                mViewPager.setCurrentItem(TAB_INFO);
                setTab(TAB_INFO);
                break;
            case R.id.iv_setting_tab:
                mViewPager.setCurrentItem(TAB_SETTING);
                setTab(TAB_SETTING);
                break;
        }
    }

    private void setTab(int position) {
        switch (position) {
            case TAB_MAIN:
                mMainTab.setImageResource(R.mipmap.main_chosen);
                break;
            case TAB_FIND:
                mFindTab.setImageResource(R.mipmap.find_chosen);
                break;
            case TAB_INFO:
                mInfoTab.setImageResource(R.mipmap.info_chosen);
                break;
            case TAB_SETTING:
                mSettingTab.setImageResource(R.mipmap.setting_chosen);
                break;
        }
    }

    private void resetImg(){
        mMainTab.setImageResource(R.mipmap.main);
        mFindTab.setImageResource(R.mipmap.find);
        mInfoTab.setImageResource(R.mipmap.info);
        mSettingTab.setImageResource(R.mipmap.setting);
    }

    private void parseJson(){
        OkHttpManager.getInstance().get(getURL(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"error okhttp",Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
                        mTvPrintJson.setText(json);
                        Log.d("zjy", "lanuch json is " + json);
                    }
                });
            }
        });
    }

    private String getURL(){
        mToken = getIntent().getStringExtra(TOKEN_TAG);
        Log.d("zjy", "getURL: " + HOME_TIMELINE_URL + "?access_token=" + mToken);
        return HOME_TIMELINE_URL + "?access_token=" + mToken;
    }
}
