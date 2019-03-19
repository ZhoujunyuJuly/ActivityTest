package com.example.doubanmovie.model;


/**
 * Created by zhoujunyu on 2019/3/19.
 */
public class DirectorsBean {

    /**
     * avatars : {"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p44818.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p44818.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p44818.jpg"}
     * name_en : Hsiao Chien Lin
     * name : 林孝谦
     * alt : https://movie.douban.com/celebrity/1312860/
     * id : 1312860
     */

    private AvatarsBeanX avatars;
    private String name_en;
    private String name;
    private String alt;
    private String id;

    public AvatarsBeanX getAvatars() {
        return avatars;
    }

    public void setAvatars(AvatarsBeanX avatars) {
        this.avatars = avatars;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
