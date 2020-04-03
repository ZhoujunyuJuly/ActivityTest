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
    public static final String EMOTION_URL = "https://api.weibo.com/2/emotions.json";
    public static final String COMMENTS_URL = "https://api.weibo.com/2/comments/show.json";
    public static final String ACCESS_TOKEN_HEADER = "?access_token=";

    //token链接存档
    public static final String REQUET_TOKEN = "client_id=621366344&client_secret=908d762f52c133243a2165e8fc2468d8" +
            "&grant_type=authorization_code&redirect_uri=https://www.baidu.com&code=";//请求token
    public static final String TOKEN = "2.00U1XGpF0olLDg992dbd1487pHDnXE";//某一token
    public static final String EntireLINK = "https://api.weibo.com/2/statuses/home_timeline.json?access_token=2.00U1XGpF0olLDg992dbd1487pHDnXE";
    public static final String code = "5c7e783246d6ce2d86684612d902d32a";

    //表情图片资源前缀
    public static final String EMOTION_SRC = "http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal";

    //sql语句
    public static final String SQL_INSERT_STATUSE = "insert into StatusesBean(" +
            "id,name,portrait,time,content,image,attitude,comment,repost,share)" +
            "values(?,?,?,?,?,?,?,?,?,?)";
    public static final String SQL_INSERT_EMOTION = "insert into Emotion(" +
            "id,value,url)" + "values(?,?,?)";

    public static final String SQL_SELECT_STATUSE = "select * from StatusesBean";
    public static final String SQL_SELECT_EMOTION = "select * from Emotion";
    public static final String DBName = "WbData.db";
    public static final String TABLE_WB = "StatusesBean";
    public static final String TABLE_EM = "Emotion";
}
