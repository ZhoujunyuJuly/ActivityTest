package com.example.doubanmovie.model;

/**
 * Created by zhoujunyu on 2019/3/19.
 */
public class AvatarsBean {
    /**
     * small : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p31663.jpg
     * large : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p31663.jpg
     * medium : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p31663.jpg
     */

    private String small;
    private String large;
    private String medium;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }
}
