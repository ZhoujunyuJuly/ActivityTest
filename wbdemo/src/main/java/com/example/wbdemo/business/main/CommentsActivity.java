package com.example.wbdemo.business.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wbdemo.R;
import com.example.wbdemo.event.CommentsEvent;
import com.example.wbdemo.event.CountsEvent;
import com.example.wbdemo.event.EventManager;
import com.example.wbdemo.info.commentdata.CommentsBean;
import com.example.wbdemo.info.maindata.StatusesBean;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;
import com.makeramen.roundedimageview.RoundedImageView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class CommentsActivity extends AppCompatActivity {

    private static final String TAG_WEIBOID = "weiboID";
    public static final String TAG_COUNTS = "countsArray";
    private List<Fragment> mFragment;
    private TabEntity mTabEntity;
    private String [] mTitle = {"0","0","0"};
    private ArrayList<Integer> mCounts = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntityList = new ArrayList<>();
    private List<CommentsBean> mCommentsList = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private CommentsViewPager mViewPager;
    private SlidingTabLayout mSlidingTabLayout;
    private NestedScrollView mScrollview;
    private RoundedImageView mMyPortrait;
    private TextView mUsername;
    private TextView mTime;
    private TextView mContent;
    private NineGridView mNineGridView;

    private TextView mCountsAtti;
    private TextView mCountsCommt;
    private TextView mCountsRepos;
    private TextView mCountsShare;
//    private View mHeaderView;



    public static void start(Context context,String weiboID,ArrayList<Integer> count){
        Intent intent = new Intent(context,CommentsActivity.class);
        intent.putExtra(TAG_WEIBOID,weiboID);
        intent.putIntegerArrayListExtra(TAG_COUNTS,count);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        initView();
        EventManager.getInstance().register(this);

        mScrollview.post(new Runnable() {
            @Override
            public void run() {
                mScrollview.scrollTo(0,mSlidingTabLayout.getMeasuredHeight());
            }
        });
    }

    private void initView(){
        mViewPager = new CommentsViewPager(getApplicationContext());
        mViewPager = findViewById(R.id.comments_viewpager);
        mSlidingTabLayout = findViewById(R.id.comments_slidingTabLayout);
        mScrollview = findViewById(R.id.comment_scrollview);

        //头部
        mMyPortrait = findViewById(R.id.iv_main_portrait);
        mUsername = findViewById(R.id.tv_main_username);
        mTime = findViewById(R.id.tv_main_timeline);
        mContent = findViewById(R.id.tv_main_content);
        mNineGridView = findViewById(R.id.nine_grid_view);
        mCountsAtti = findViewById(R.id.tv_item_attitudes);
        mCountsCommt = findViewById(R.id.tv_item_comments);
        mCountsRepos = findViewById(R.id.tv_item_reposts);
        mCountsShare = findViewById(R.id.tv_item_share);

        loadView();

    }

    private void loadView(){
        mFragment = new ArrayList<>();
        mFragment.add(initCmtsMainFragment());
        mFragment.add(initCmtsMainFragment());
        mFragment.add(initCmtsMainFragment());

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

        //设置viewpager
        mViewPager.setAdapter(fgAdapter);
        mViewPager.setCurrentItem(1);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mSlidingTabLayout.setCurrentTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        mCounts = getIntent().getIntegerArrayListExtra(TAG_COUNTS);
        mTitle[0] = "赞 " + mCounts.get(0).toString();
        mTitle[1] = "评论 " + mCounts.get(1).toString();
        mTitle[2] = "转发 " + mCounts.get(2).toString();


        //加载tab布局
        mSlidingTabLayout.setViewPager(mViewPager,mTitle);

        mSlidingTabLayout.setIndicatorColor(getResources().getColor(R.color.UnderlineRed));
        mSlidingTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });



//        DisplayMetrics metrics = getResources().getDisplayMetrics();//获取屏幕宽度
//        mSlidingTabLayout.getTitleView(0).setWidth((int)metrics.widthPixels/4);
//        mSlidingTabLayout.getTitleView(1).setWidth((int)metrics.widthPixels/2);
//        mSlidingTabLayout.getTitleView(2).setWidth((int)metrics.widthPixels/4);
//
//
//        mSlidingTabLayout.getTitleView(0).setGravity(View.TEXT_ALIGNMENT_VIEW_END);
//        mSlidingTabLayout.getTitleView(1).setGravity(View.TEXT_ALIGNMENT_VIEW_END);
//        mSlidingTabLayout.getTitleView(2).setGravity(View.TEXT_ALIGNMENT_VIEW_END);


        loadData();

    }

    private void loadData(){

        //点赞、转发、评论、分享数
        mCountsAtti.setText(String.valueOf(mCounts.get(0)));
        mCountsCommt.setText(String.valueOf(mCounts.get(1)));
        mCountsRepos.setText(String.valueOf(mCounts.get(2)));
        mCountsShare.setText(String.valueOf(mCounts.get(3)));
    }

    private CmtsMainFragment initCmtsMainFragment(){
        CmtsMainFragment fragment = CmtsMainFragment.newInstance(getIntent().getStringExtra(TAG_WEIBOID));
        return fragment;
    }


    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onEventCommentsThread(CommentsEvent event){
        mCommentsList = event.getCommentsList();
        if(mCommentsList != null) {
            //头像、内容、事件、昵称
            Glide.with(getApplicationContext()).load(mCommentsList.get(0).getStatus().getUser().getAvatar_hd()).into(mMyPortrait);
            mUsername.setText(mCommentsList.get(0).getStatus().getUser().getName());
            mTime.setText(mCommentsList.get(0).getStatus().getCreated_at());
            mContent.setText(mCommentsList.get(0).getStatus().getText());

            //九宫格
            ArrayList<ImageInfo> imageInfo = new ArrayList<>();
            List<StatusesBean.PicUrlsBean> imageDetails = mCommentsList.get(0).getStatus().getPic_urls();
            if (imageDetails != null) {
                for (StatusesBean.PicUrlsBean imageDetail : imageDetails) {
                    ImageInfo info = new ImageInfo();
                    info.setThumbnailUrl(imageDetail.getThumbnail_pic());
                    info.setBigImageUrl(imageDetail.getThumbnail_pic().replace("thumbnail","large"));
                    imageInfo.add(info);
                }
            }

            mNineGridView.setAdapter(new NineGridViewClickAdapter(getApplicationContext(),imageInfo));
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventManager.getInstance().unregister(this);
    }

}
