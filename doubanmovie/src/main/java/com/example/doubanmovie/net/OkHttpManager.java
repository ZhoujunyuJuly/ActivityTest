package com.example.doubanmovie.net;

import android.os.Handler;
import android.os.Looper;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by zhoujunyu on 2019/3/26.
 */
public class OkHttpManager {

    public static final String TAG = OkHttpManager.class.getSimpleName();

    private static OkHttpManager ourInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mDelivery;

    private OkHttpManager() {
        mOkHttpClient = new OkHttpClient();
        mDelivery = new Handler(Looper.getMainLooper());
    }

    public static OkHttpManager getInstance() {
        synchronized (OkHttpManager.class) {
            if (ourInstance == null) {
                ourInstance = new OkHttpManager();
            }
        }
        return ourInstance;
    }

    /**
     * 异步的get请求
     *
     * @param url
     * @param callback 请求回调
     * @param params  所需的额外参数，公共参数里面自动添加
     */
    public void get(String url, Map<String, String> params, final Callback callback) {

        final Request request = new Request.Builder()
                .url(buildGetUrl(url, params))
                .build();
        deliveryResult(callback, request);
    }

    /**
     * 异步的post请求
     *
     * @param url
     * @param callback 请求回调
     * @param params 所需的额外参数，公共参数里面自动添加
     */
    public void post(String url,  Map<String, String> params, final Callback callback) {
        Request request = buildPostRequest(url, params);
        deliveryResult(callback, request);
    }

    /**
     * 构造get请求的参数,将参数拼接起来
     *
     * @param url 请求路径
     * @param params 签名的参数，所有参数按key进行生序排列
     */
    protected String buildGetUrl(String url,Map<String, String> params) {

        if(params == null || params.isEmpty()){
            return url;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(url).append("?");

        for (Map.Entry<String, String> item : params.entrySet()) {
            sb.append(item.getKey()).append("=").append(URLEncoder.encode(item.getValue())).append("&");
        }

        return sb.toString();
    }

    private Request buildPostRequest(String url, Map<String, String> params) {

        FormBody.Builder builder = new FormBody.Builder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }

        RequestBody body = builder.build();

        return new Request.Builder().url(url).post(body).build();
    }

    private void deliveryResult(Callback callback, final Request request) {
       mOkHttpClient.newCall(request).enqueue(callback);
    }
}
