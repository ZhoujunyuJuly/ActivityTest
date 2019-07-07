package com.example.downloadpage;

/**
 * Created by zhoujunyu on 2019/7/6.
 */
public interface DownloadListener {

    void onSuccess();

    void onFailed();

    void onPaused();

    void onProgress(int progress);

    void onCanceled();
}
