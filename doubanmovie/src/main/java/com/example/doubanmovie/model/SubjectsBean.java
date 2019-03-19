package com.example.doubanmovie.model;

import java.util.List;

/**
 * Created by zhoujunyu on 2019/3/19.
 */
public class SubjectsBean {
    /**
     * rating : {"max":10,"average":5,"details":{"1":601,"3":1056,"2":980,"5":145,"4":328},"stars":"25","min":0}
     * genres : ["爱情"]
     * title : 比悲伤更悲伤的故事
     * casts : [{"avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p31663.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p31663.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p31663.jpg"},"name_en":"Ivy Chen","name":"陈意涵","alt":"https://movie.douban.com/celebrity/1274316/","id":"1274316"},{"avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1511061580.88.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1511061580.88.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1511061580.88.jpg"},"name_en":"Jasper Liu","name":"刘以豪","alt":"https://movie.douban.com/celebrity/1326546/","id":"1326546"},{"avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p31369.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p31369.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p31369.jpg"},"name_en":"Bryan Shu-Hao Chang","name":"张书豪","alt":"https://movie.douban.com/celebrity/1315045/","id":"1315045"}]
     * durations : ["105分钟"]
     * collect_count : 34274
     * mainland_pubdate : 2019-03-14
     * has_video : false
     * original_title : 比悲傷更悲傷的故事
     * subtype : movie
     * directors : [{"avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p44818.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p44818.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p44818.jpg"},"name_en":"Hsiao Chien Lin","name":"林孝谦","alt":"https://movie.douban.com/celebrity/1312860/","id":"1312860"}]
     * pubdates : ["2018-11-30(台湾)","2019-03-14(中国大陆)"]
     * year : 2018
     * images : {"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2549523952.jpg","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2549523952.jpg","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2549523952.jpg"}
     * alt : https://movie.douban.com/subject/27624661/
     * id : 27624661
     */

    private RatingBean rating;
    private String title;
    private int collect_count;
    private String mainland_pubdate;
    private boolean has_video;
    private String original_title;
    private String subtype;
    private String year;
    private ImagesBean images;
    private String alt;
    private String id;
    private List<String> genres;
    private List<CastsBean> casts;
    private List<String> durations;
    private List<DirectorsBean> directors;
    private List<String> pubdates;


    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public String getMainland_pubdate() {
        return mainland_pubdate;
    }

    public void setMainland_pubdate(String mainland_pubdate) {
        this.mainland_pubdate = mainland_pubdate;
    }

    public boolean isHas_video() {
        return has_video;
    }

    public void setHas_video(boolean has_video) {
        this.has_video = has_video;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
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

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<CastsBean> getCasts() {
        return casts;
    }

    public void setCasts(List<CastsBean> casts) {
        this.casts = casts;
    }

    public List<String> getDurations() {
        return durations;
    }

    public void setDurations(List<String> durations) {
        this.durations = durations;
    }

    public List<DirectorsBean> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DirectorsBean> directors) {
        this.directors = directors;
    }

    public List<String> getPubdates() {
        return pubdates;
    }

    public void setPubdates(List<String> pubdates) {
        this.pubdates = pubdates;
    }
}
