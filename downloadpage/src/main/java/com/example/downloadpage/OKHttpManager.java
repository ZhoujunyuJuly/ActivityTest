package com.example.downloadpage;

import android.os.Looper;

import java.io.IOException;
import java.util.logging.Handler;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhoujunyu on 2019/7/10.
 */
public class OKHttpManager {
    public static OKHttpManager mOkHttpManager;
    private OkHttpClient mClient;
    private android.os.Handler mHandler;

    private OKHttpManager(){
        mClient = new OkHttpClient();
        mHandler = new android.os.Handler(Looper.getMainLooper());

    }



    public static OKHttpManager getInstance() {
        synchronized (OKHttpManager.class){
           if( mOkHttpManager == null){
               mOkHttpManager = new OKHttpManager();
           }
        }
        return mOkHttpManager;
    }

    public Response get(Request request) throws IOException {
        Response response = mClient.newCall(request).execute();
        return response;
    }




}
