package com.example.downloadpage;

import android.content.Context;
import android.content.Intent;
import android.net.sip.SipSession;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Random;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by zhoujunyu on 2019/7/6.
 */
public class DownloadTask extends AsyncTask<String,Integer,Integer> {

    public static final int SUCCESS  = 0;
    public static final int FAILED = 1;
    public static final int PAUSED = 2;
    public static final int CANCEL = 3;
    public static final String ACTION_PROGRESS_BROADCAST = "com.example.downloadpage.PROGRESS_CHANGE";


    private boolean isCanceled = false;
    private boolean isPaused = false;
    private int lastProgress;
    private Context mContext;

    private String downURL;

    private DownloadListener downloadListener;
    private LocalBroadcastManager broadcastManager;

    public DownloadTask(DownloadListener downloadListener, Context context,String url) {
        this.downloadListener = downloadListener;
        this.mContext = context;
        this.downURL = url;
    }


    @Override
    protected Integer doInBackground(String... params) {
        InputStream is = null;
        RandomAccessFile savedFile = null;//可随机跳到文件指定位置读写；记录当前位置指针；可追加内容；在指定位置追加会覆盖
        File file = null;

        try{
            long downFileLength = 0;
            //获取下载参数，如下载地址
            String downloadURL = params[0];
            String fileName = downloadURL.substring(downloadURL.lastIndexOf("/"));

            //默认保存在系统文件
            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            file = new File(directory + fileName);
            if(file.exists()){
                downFileLength = file.length();
            }

            long contentLength = getContentLength(downloadURL);
            //对下载任务初始判断：如获取不到下载内容，返回失败；如已存在文件大小与下载任务一致，为下载完成，不需进行下载
            if(contentLength == 0){
                return FAILED;
            }else if(contentLength == downFileLength){
                return SUCCESS;
            }

            Request request = new Request.Builder().addHeader("RANGE","bytes=" + downFileLength + "-")
                                                   .url(downloadURL)
                                                   .build();
            Response response = OKHttpManager.getInstance().get(request);


            if(response != null){
                is = response.body().byteStream();//为防止内存泄漏，要关闭字节流
                savedFile = new RandomAccessFile(file,"rw");
                savedFile.seek(downFileLength);//跳到已下载的文件位置
                byte[] b = new byte[1024];
                int total = 0;
                int len;
                while ((len = is.read(b)) != -1){//文件持续不断读取，当有暂停时跳出循环
                    if(isCanceled){
                        return CANCEL;
                    }else if(isPaused){
                        return PAUSED;
                    }else {
                        total += len;
                        savedFile.write(b,0,len);//offset指从当前指针处开始写入字节数组，一次性写len长度
                        int progressLength = (int)((total + downFileLength)*100/contentLength);
                        publishProgress(progressLength);
                        //broadcastManager.sendBroadcast(Broadcast_Intent);//发送广播
                    }
                }
                response.body().close();
                return SUCCESS;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {//记得关闭文件
                if (is != null) {
                    is.close();
                }
                if (savedFile != null) {
                    savedFile.close();
                }
                if (isCanceled && file != null) {
                    file.delete();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return FAILED;

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        if(progress > lastProgress){
            //调用监听接口
            downloadListener.onProgress(progress,this);
            lastProgress = progress;

        }
    }

    @Override
    protected void onPostExecute(Integer integer) {
        //返回下载结果
        switch (integer){
            case SUCCESS:
                downloadListener.onSuccess(this);
                break;
            case FAILED:
                downloadListener.onFailed(this);
                break;
            case PAUSED:
                downloadListener.onPaused(this);
                break;
            case CANCEL:
                downloadListener.onCanceled(this);
                default:
                    break;
        }
    }

    public void pauseDownload(){
        isPaused = true;
    }

    public void cancelDownload(){
        isCanceled = true;
    }


    //获取文件内容长度
    private long getContentLength(String downloadUrl)throws IOException{
        //OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                                     .url(downloadUrl)
                                     .build();
        //Response response = okHttpClient.newCall(request).execute();
        Response response = OKHttpManager.getInstance().get(request);
        //获取文件内容
        if (response != null && response.isSuccessful()){
            long contentLength = response.body().contentLength();
            response.close();
            return contentLength;
        }

        return 0;
    }

    //获取当前下载任务的地址，通过地址进行单一映射
    public String getDownURL() {
        return downURL;
    }
}
