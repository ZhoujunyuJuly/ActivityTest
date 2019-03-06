package com.example.recyclerview;

public class MyReclList {
    private int img;
    private String name;
    private String content;

    public MyReclList(){
        super();
    }

    public MyReclList(int img,String name,String content){
        super();
        this.img = img;
        this.name = name;
        this.content = content;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
