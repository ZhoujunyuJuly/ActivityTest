package com.example.downloadpage;

/**
 * Created by zhoujunyu on 2019/7/17.
 */
public class DownloadData {
    private String BookName;
    private String BookURL;

    public DownloadData(String bookName,String url) {
        this.BookName = bookName;
        this.BookURL = url;
    }

    public String getBookName() {
        return BookName;
    }

    public String getBookURL() {
        return BookURL;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public void setBookURL(String bookURL) {
        BookURL = bookURL;
    }
}
