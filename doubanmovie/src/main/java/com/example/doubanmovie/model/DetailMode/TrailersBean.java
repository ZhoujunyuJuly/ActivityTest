package com.example.doubanmovie.model.DetailMode;

/**
 * Created by zhoujunyu on 2019/3/21.
 */
public class TrailersBean {
    /**
     * medium : http://img1.doubanio.com/img/trailer/medium/2550813680.jpg?1552626843
     * title : 预告片：低估爱情版 (中文字幕)
     * subject_id : 27624661
     * alt : https://movie.douban.com/trailer/244500/
     * small : http://img1.doubanio.com/img/trailer/small/2550813680.jpg?1552626843
     * resource_url : http://vt1.doubanio.com/201903200945/2a673c61c8fc9338b73c61379cf0cb55/view/movie/M/302440500.mp4
     * id : 244500
     */

    private String medium;
    private String title;
    private String subject_id;
    private String alt;
    private String small;
    private String resource_url;
    private String id;

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getResource_url() {
        return resource_url;
    }

    public void setResource_url(String resource_url) {
        this.resource_url = resource_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
