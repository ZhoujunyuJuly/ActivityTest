package com.example.downloadpage;

/**
 * Created by zhoujunyu on 2019/7/6.
 */
public interface DownloadListener {

    void onSuccess(int position);

    void onFailed(int position);

    void onPaused(int position);

    void onProgress(int progress,int position);

    void onCanceled(int position);
}
