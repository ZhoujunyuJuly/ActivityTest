package com.example.booklist;

public class MyRecList {
    private int img;
    private String bookName;
    private String description;
    private String chapter;
    private String customer;
    private String bookvalue;

    public MyRecList(){
        super();
    }

    public MyRecList(int img,String bookName,String description,String chapter,String customer,String bookvalue){
        super();
        this.img = img;
        this.bookName = bookName;
        this.description = description;
        this.chapter = chapter;
        this.customer = customer;
        this.bookvalue = bookvalue;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getBookvalue() {
        return bookvalue;
    }

    public void setBookvalue(String bookvalue) {
        this.bookvalue = bookvalue;
    }
}
