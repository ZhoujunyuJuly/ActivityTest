package com.example.doubanmovie.model;

/**
 * Created by zhoujunyu on 2019/3/19.
 */
public class CastsBean {
    /**
     * avatars : {"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p31663.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p31663.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p31663.jpg"}
     * name_en : Ivy Chen
     * name : 陈意涵
     * alt : https://movie.douban.com/celebrity/1274316/
     * id : 1274316
     */

    private AvatarsBean avatars;
    private String name_en;
    private String name;
    private String alt;
    private String id;

    public AvatarsBean getAvatars() {
        return avatars;
    }

    public void setAvatars(AvatarsBean avatars) {
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
