package com.example.downloadpage;

/**
 * Created by zhoujunyu on 2019/7/6.
 */
public interface DownloadListener {

    void onSuccess(DownloadTask dt);

    void onFailed(DownloadTask dt);

    void onPaused(DownloadTask dt);

    void onProgress(int progress,DownloadTask dt);

    void onCanceled(DownloadTask dt);
}
