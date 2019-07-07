package com.example.downloadpage;

import android.net.sip.SipSession;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Random;

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

    private boolean isCanceled = false;
    private boolean isPaused = false;
    private int lastProgress;

    private DownloadListener downloadListener;

    public DownloadTask(DownloadListener downloadListener) {
        this.downloadListener = downloadListener;
    }


    @Override
    protected Integer doInBackground(String... params) {
        InputStream is = null;
        RandomAccessFile savedFile = null;
        File file = null;

        try{
            long downFileLength = 0;
            String downloadURL = params[0];
            String fileName = downloadURL.substring(downloadURL.lastIndexOf("/"));
            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            file = new File(directory + fileName);
            if(file.exists()){
                downFileLength = file.length();
            }

            long contentLength = getContentLength(directory);
            if(contentLength == 0){
                return FAILED;
            }else if(contentLength == downFileLength){
                return SUCCESS;
            }

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().addHeader("RANGE","bytes=" + downFileLength + "-")
                                                   .url(downloadURL)
                                                   .build();
            Response response = client.newCall(request).execute();
            if(response != null){
                is = response.body().byteStream();
                savedFile = new RandomAccessFile(file,"w");
                savedFile.seek(downFileLength);
                byte[] b = new byte[1024];
                int total = 0;
                int len;
                while ((len = is.read(b)) != -1){
                    if(isCanceled){
                        return CANCEL;
                    }else if(isPaused){
                        return PAUSED;
                    }else {
                        total += len;
                        savedFile.write(b,0,len);
                        int progressLength = (int)((total + downFileLength)*100/contentLength);
                        publishProgress(progressLength);
                    }
                }
                response.body().close();
                return SUCCESS;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
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
            downloadListener.onProgress(progress);
            lastProgress = progress;
        }
    }

    @Override
    protected void onPostExecute(Integer integer) {
        switch (integer){
            case SUCCESS:
                downloadListener.onSuccess();
                break;
            case FAILED:
                downloadListener.onFailed();
                break;
            case PAUSED:
                downloadListener.onPaused();
                break;
            case CANCEL:
                downloadListener.onCanceled();
                default:
                    break;
        }
    }

    public void PauseDownload(){
        isPaused = true;
    }

    public void CancelDownload(){
        isCanceled = true;
    }



    private long getContentLength(String downloadUrl)throws IOException{
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                                     .url(downloadUrl)
                                     .build();
        Response response = okHttpClient.newCall(request).execute();
        if (response != null && response.isSuccessful()){
            long contentLength = request.body().contentLength();
            response.close();
            return contentLength;
        }

        return 0;
    }
}
