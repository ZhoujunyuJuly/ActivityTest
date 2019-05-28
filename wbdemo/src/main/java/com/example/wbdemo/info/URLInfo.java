package com.example.wbdemo.info;

/**
 * Created by zhoujunyu on 2019/5/20.
 */
public class URLInfo {
    public static final String REQUEST_URL = "https://api.weibo.com/oauth2/authorize";
    public static final String CLIENT_ID = "621366344";
    public static final String REDIRECT_URL = "https://www.baidu.com";
    public static final String API_URL = "https://api.weibo.com/oauth2/authorize?client_id=621366344&redirect_uri=https://www.baidu.com";
    public static final String CLIENT_SECRET = "908d762f52c133243a2165e8fc2468d8";
    public static final String GRANT_TYPE = "authorization_code";
    public static final String HEADER_ACCESS = "https://api.weibo.com/oauth2/access_token";
    public static final String TAG_URL = "authurl";
    public static final String TOKEN_TAG = "token_tag";
    public static final String HOME_TIMELINE_URL = "https://api.weibo.com/2/statuses/home_timeline.json";

    //token链接存档
    public static final String REQUET_TOKEN = "client_id=621366344&client_secret=908d762f52c133243a2165e8fc2468d8" +
            "&grant_type=authorization_code&redirect_uri=https://www.baidu.com&code=";//请求token
    public static final String TOKEN = "2.00U1XGpF0olLDg992dbd1487pHDnXE";//某一token
    public static final String EntireLINK = "https://api.weibo.com/2/statuses/home_timeline.json?access_token=2.00U1XGpF0olLDg992dbd1487pHDnXE";
    public static final String code = "5c7e783246d6ce2d86684612d902d32a";
}
