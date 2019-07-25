
# 1.WbApp
方便在Bugly查看崩溃日志。<br>
进行应用热更新。

1.配置<br>

a. 引入依赖
``` 
    //BUGLY
    implementation 'com.tencent.bugly:crashreport_upgrade:latest.release'
    implementation 'com.tencent.bugly:nativecrashreport:latest.release'
  ```
  
b. defaultConfig加入

```
      defaultConfig {
        //BUGLY
        ndk {
            abiFilters 'armeabi'
            }
        }
```
c. 加入权限
```
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.READ_LOGS" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES"/>
```

2.程序运行前[启动BUGLY](https://blog.csdn.net/qq_36467463/article/details/80803786),并进行[热更新UI自定义](https://www.jianshu.com/p/48da0042a488)
```
public class WbApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(this);
        strategy.setAppVersion(BuildConfig.VERSION_NAME);//版本
        strategy.setAppPackageName(BuildConfig.APPLICATION_ID);//包名
        //注意：如果您之前使用过Bugly SDK，请将以下这句注释掉。--在热更新时这句要注释掉
        //CrashReport.initCrashReport(this,"4037d61985",false);


        Beta.autoCheckUpgrade = false;//设置不自动检查
        Beta.upgradeDialogLayoutId = R.layout.bugly_ui;
        Log.i("App","init success");
        //Bugly.init(getApplicationContext(), "336b7711dc", false);
        Bugly.init(getApplicationContext(), "4037d61985", false);

    }
```

# 2.WebViewActivity
* 授权页，[根据用户ID重定向code](https://open.weibo.com/wiki/Oauth2/authorize)<br>
* 再拼接ID与code，[拿到用户token](https://open.weibo.com/wiki/Oauth2/access_token)

1.WebView策略定义:<br>

* 允许调用javascript；<br>
* 拦截重定向跳转，获取code；利用code,继续访问，拿到TOKEN；（shouldOverrideUrlLoading，获取TOKEN见下一步）<br>
* 定义页面加载完的姿势：跳转微博首页(onPageFinished)。<br>

 ```
    private void init(){
        mWebView = findViewById(R.id.webview);
        
        //允许执行javascript脚本，启动javascript调用功能
        mWebView.getSettings().setJavaScriptEnabled(true);
        parseJson();
        //为webview添加可供JS调用的本地方法
        mWebView.addJavascriptInterface(new InJavaScriptLocalObj(), "local_obj");
        mWebView.loadUrl(API_URL);//包括client_id的网址
    }

    private void parseJson() {
        mWebView.setWebViewClient(new WebViewClient() {
            /**
             * 拦截 url 跳转,在里边添加点击链接跳转或者操作
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //view.loadUrl(url);
                CODE = url.substring(url.indexOf("code=") + 5);//利用拦截的url，获取code
                Log.d("zjy", "跳转网址为:  " + HEADER_ACCESS + getPost());
                view.postUrl(HEADER_ACCESS, getPost().getBytes());//使用POST方法跳转页面
                return true;//如果拦截返回true,默认为false
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.loadUrl("javascript:window.local_obj.showSource('<head>'+"
                        + "document.getElementsByTagName('html')[0].innerHTML+'</head>');");

                if (!TextUtils.isEmpty(mToken_content)) {
                    LaunchActivity.start(WebViewActivity.this, mToken_content);
                }
            }
        });
    }
  ```
    
    
 2.提取并保存TOKEN<br>
 
 * 定义js回调的object，解析TOKEN；<br>
 * 为了不让TOKEN消失在风中，使用sharedPrefrences保存信息；
 
 ```
     //提取TOKEN，当界面加载完
    final class InJavaScriptLocalObj {
        @JavascriptInterface
        public void showSource(String html) {
            if (html != null && html.contains("{\"access_token\"")) {
                String json = html.substring(html.indexOf("{\"access_token\""), html.indexOf("</pre>"));
                mToken = new Gson().fromJson(json, Token.class);
                mToken_content = mToken.getAccess_token();

                saveToken();

                if (!TextUtils.isEmpty(mToken_content)) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            LaunchActivity.start(WebViewActivity.this, mToken_content);
                        }
                    });
                }
            }
        }
    }

    //保存token
    private void saveToken() {
        SharedPreferences.Editor editor = getSharedPreferences("access_token", MODE_PRIVATE).edit();
        editor.putString("access_token", mToken.getAccess_token());
        editor.putString("remind_in", mToken.getRemind_in());
        editor.putString("expires_in", String.valueOf(mToken.getExpires_in()));
        editor.putString("uid", mToken.getUid());
        editor.putString("isRealName", mToken.getIsRealName());

        editor.apply();
    }
 ```
    
# 3.LaunchActivity

1.[TabLayout](https://juejin.im/entry/58f70734a0bb9f006ab653b6):有三种已封装好的TabLayout。<br>
由于使用的是底部导航栏，所以选用[CommonLayout](https://blog.csdn.net/ZhangXuxiaoqingnian/article/details/81064400)<br>

    private void initData(){
        mFragments = new ArrayList<>();

        mFragments.add(initMainFragment());
        mFragments.add(new FindFragment());
        mFragments.add(new InfoFragment());
        mFragments.add(new SettingFragment());

        //fragment适配器
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

        //viewpager监听
        mViewPager.setAdapter(mFragPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }
            @Override
            public void onPageSelected(int i) {
                mCommonTabLayout.setCurrentTab(i);
            }
            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        mViewPager.setCurrentItem(0);

        //commantablayout监听
        mCommonTabLayout.setTabData((ArrayList<CustomTabEntity>)getData());

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

2.自定义TAG数据，可一步设置图文
    
    //设置TAB的数据
    private List<CustomTabEntity> getData(){
        List<CustomTabEntity> AllData = new ArrayList<>();
        final List<Integer> unChosenImage = new ArrayList<>();
        unChosenImage.add(0,R.mipmap.main);
        unChosenImage.add(1,R.mipmap.find);
        unChosenImage.add(2,R.mipmap.info);
        unChosenImage.add(3,R.mipmap.setting);
        final List<Integer> ChosenImage = new ArrayList<>();
        ChosenImage.add(0,R.mipmap.main_chosen);
        ChosenImage.add(1,R.mipmap.find_chosen);
        ChosenImage.add(2,R.mipmap.info_chosen);
        ChosenImage.add(3,R.mipmap.setting_chosen);

        for (int i = 0; i < unChosenImage.size(); i++) {
            final int index = i;
            AllData.add(new CustomTabEntity() {
                @Override
                public String getTabTitle() {
                    return "测试tab";
                }
                @Override
                public int getTabSelectedIcon() {
                    return ChosenImage.get(index);
                }
                @Override
                public int getTabUnselectedIcon() {
                    return unChosenImage.get(index);
                }
            });
        }
        return AllData;
    }
    
    
# 4.MainFragment

微博主页面，功能代码都在此。<br>

* 首先获取本地缓存的数据，使用sqlite数据库，当网页刷新时再更新数据；
* 解析网页数据，传入适配器；
* 用户交互如刷新、点击事件实现。

1.实话化Fragment传值

    public static MainFragment newInstance(String token) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(TOKEN, token);
        fragment.setArguments(args);
        return fragment;
    }
    
 调用：
 
     private MainFragment initMainFragment(){
        MainFragment mainFragment = MainFragment.newInstance(getIntent().getStringExtra(TOKEN_TAG));
        return mainFragment;
    }
    
 2.初始化布局、适配器与刷新布局<br>
 
 * 评论、转发点击事件定义，跳转到评论界面；
 * 布局、适配器、刷新模式定义。
 
 ```
  private void initView(View view){

        initRefreshLayout(view);//初始化刷新布局

        mRecyclerView = view.findViewById(R.id.recyclerview_fg_main);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new MyItemDecoration(getContext(),15));
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
                        CommentsActivity.start(getContext(),mStatusesList,position);
                        break;
                }
            }
        });
        
        mMainAdapter = new MainAdapter(R.layout.item_launch_main,mStatusesList,getActivity());
        mRecyclerView.setAdapter(mMainAdapter);
        NineGridView.setImageLoader(new GlideImageLoader());//载入九宫格图片基类
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
 ```
 
 3.解析Json<br>
 
 * 将OKHttp封装为带锁的单例，节省内存；
 * 刷新UI界面时，需在主线程操作；
 * 获取一次网络数据，讲之前数据清空，存入本地数据库。
 
 ```
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
                
                saveDataToSqLite();
            }
        });
    }
 ```
 
 4.本地缓存<br>
 
 * 程序启动时打开数据库；
 * 插入数据时，打开数据库事务，加快写入效率；
 * 取数时，要根据数据类型set数据。
 
 1.在WbApp开启数据库服务：
 ```
         SQLiteStudioService.instance().start(this);
 ```
 
 2.onCreate()时，初始化数据库，并判断数据库是否有数
 ```
        dbHelper = new JsonDbHelper(getContext(),"WbData.db",null,1);
        db = dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery(SQL_SELECT,null);

        //判断数据库里是否有数
        if( c.getCount() > 0){
            getJsonInDatabase();
        }else {
            parseJson();
        }
```

3.取数

    private void getJsonInDatabase(){
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(SQL_SELECT,null,null);
        
        int i = 0;
        if( cursor.moveToFirst()){
            do{
                StatusesBean statusesBean = new StatusesBean();
                UserBean userBean = new UserBean();

                //1.微博昵称
                userBean.setName(getStringDBData(cursor,"name"));
                //2.微博头像
                userBean.setAvatar_hd(getStringDBData(cursor,"portrait"));

                statusesBean.setUser(userBean);

                ...//省略
                
                //9.图片
                String pic = cursor.getString(cursor.getColumnIndex("image"));

                //考虑九格宫，一个字段里有多个图片链接的情况
                List<StatusesBean.PicUrlsBean> pic_List = new ArrayList<>();
                StatusesBean.PicUrlsBean picUrlsBean = new StatusesBean.PicUrlsBean();

                if( pic.contains(",")){
                    String[] picCollection = pic.split(",");
                    for(int index = 0; index < picCollection.length;index ++){
                        picUrlsBean.setThumbnail_pic(picCollection[index]);
                        pic_List.add(picUrlsBean);
                    }
                }else {
                    picUrlsBean.setThumbnail_pic(pic);
                    pic_List.add(picUrlsBean);
                }
                
                statusesBean.setPic_urls(pic_List);
                mStatusesList.add(statusesBean);
                i = i + 1;
            }while (cursor.moveToNext());
        }
        
        mMainAdapter.notifyDataSetChanged();
    }

    //从数据库拿相应行的数据
    private String getStringDBData(Cursor cursor,String columnName){
            return cursor.getString(cursor.getColumnIndex(columnName));
    }

    private int getIntDBData(Cursor cursor,String columnName){
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }
    
4.存数
```
    private void saveDataToSqLite(){
        if( db == null) {
            db = dbHelper.getWritableDatabase();
        }
        //开始事务
        db.beginTransaction();

        Cursor cursor = db.rawQuery(SQL_SELECT,null);

        //删除之前的数据
        if( cursor.getCount() > 0){
            db.delete("StatusesBean",null,null);
        }

        for(StatusesBean item:mStatusesList){
            db.execSQL(SQL_INSERT,new Object[]{null,item.getUser().getName(),item.getUser().getAvatar_hd(),
            item.getCreated_at(),item.getText(),item.getPic_urls(),item.getAttitudes_count(),
                    item.getComments_count(),item.getReposts_count(),item.getShares_count()});
        }
        
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }
```

# 5.MainAdapter

1.使用的是[BaseQuickAdapter](https://blog.csdn.net/qq_20451879/article/details/78520263)

* 微博话题：富文本样式（包括[字符分割](https://www.jianshu.com/p/99e8dc9baf57))
```
        //微博内容
        if(item.getText().contains("http://") || item.getText().contains("#")){
            //SPannableString富文本样式
            String ContentStr = item.getText();
            SpannableString content = new SpannableString(ContentStr);
            //超链接
            if(ContentStr.contains("http://t.cn")) {
                String ALLURL = ContentStr.substring(ContentStr.indexOf("http://t.cn"), ContentStr.length() - 1);
                int lastPosition = ALLURL.indexOf(" ");
                final String videoURL;
                if( lastPosition != -1) {
                     videoURL = ALLURL.substring(0, lastPosition);
                }else {
                    videoURL = ALLURL.substring(0, ALLURL.length()-1);
                }
                content.setSpan(new LINKURLSpan(videoURL), ContentStr.indexOf(videoURL),
                        ContentStr.indexOf(videoURL)+videoURL.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            }
            //话题
            if(ContentStr.contains("#")){
                Matcher m = Pattern.compile("#").matcher(ContentStr);
                int number = 0;
                List<Integer> position = new ArrayList<>();
                while (m.find()){
                    position.add(number,m.end()-1);
                    number  = number +1;
                }

                if(number >=2){
                    for(int i =0;i < number - 1;i = i+2){
                        content.setSpan(new LINKURLSpan(""),position.get(i),position.get(i+1)+1,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    }
                }
            }
```

自定义样式
```
    public class LINKURLSpan extends URLSpan {
        public LINKURLSpan(String url) {
            super(url);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false);//无下划线
            ds.setColor(Color.parseColor("#1E90FF"));//蓝色
        }

        @Override
        public void onClick(View widget) {
            super.onClick(widget);
            if(widget instanceof  TextView && getURL()!= null && !getURL().isEmpty()) {
                WatchVideoActivity.start(mContext, getURL());
            }
        }
    }
```

2.九宫格

```
        //九宫格
        ArrayList<ImageInfo> imageInfo = new ArrayList<>();
        List<StatusesBean.PicUrlsBean> imageDetails = item.getPic_urls();
        if (imageDetails != null) {
            for (StatusesBean.PicUrlsBean imageDetail : imageDetails) {
                ImageInfo info = new ImageInfo();
                info.setThumbnailUrl(imageDetail.getThumbnail_pic());
                info.setBigImageUrl(imageDetail.getThumbnail_pic().replace("thumbnail","large"));
                imageInfo.add(info);
            }
        }

        NineGridView nineGridView = helper.getView(R.id.nine_grid_view);
        nineGridView.setAdapter(new NineGridViewClickAdapter(mContext,imageInfo));
  ```



其他一些功能细节，有缘再写！白了个白。
