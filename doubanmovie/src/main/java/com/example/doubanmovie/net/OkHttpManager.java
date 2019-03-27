package com.example.doubanmovie.net;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by zhoujunyu on 2019/3/26.
 */
public class OkHttpManager {
    private static OkHttpManager ourInstance;
    private OkHttpClient mClient;

//    static OkHttpManager getInstance() {
//        return ourInstance;
//    }

    private OkHttpManager() {
        mClient = new OkHttpClient();
    }

    public static synchronized OkHttpManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new OkHttpManager();
        }
        return ourInstance;
    }


    public void get(String address, Map<String, String> params, Callback callback) {
        final Request request = new Request.Builder().url(address).get().build();
        Call call = mClient.newCall(request);

        call.enqueue(callback);

    }

    public String MapToURL(Map<String, String> params) {
        StringBuilder url = new StringBuilder();
        for (String key : params.keySet()) {
            url.append(key).append("=").append(params.get(key)).append("&");
        }
        url.deleteCharAt(url.length() - 1);

        return url.toString();
    }

    public Map<String, String> URLToMap(String url) {
        Map<String, String> map = new HashMap();
        String[] params = url.split("&");
        for (int i = 0; i < params.length; i++) {
            String[] param = params[i].split("=");
            if (param.length == 2) {
                map.put(param[0], param[1]);
            }
        }
        return map;
    }


}
