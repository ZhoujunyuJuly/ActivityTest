package com.example.downloadpage;

/**
 * Created by zhoujunyu on 2019/7/16.
 */
public class DownloadData {
    private String name;
    private String URL;

    public DownloadData(String name,String URL) {
        this.name = name;
        this.URL = URL;
    }

    public String getName() {
        return name;
    }

    public String getURL() {
        return URL;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
