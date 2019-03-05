package com.example.listviewtest;

public class MyList {
    private int mImg;
    private String mName;
    private String mContent;

    public MyList(){
        super();
    }



    public MyList(String name,String content,int img){
        super();
        this.mName = name;
        this.mContent = content;
        this.mImg = img;
    }


    public int getmImg() {
        return mImg;
    }

    public void setmImg(int mImg) {
        this.mImg = mImg;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }
}
