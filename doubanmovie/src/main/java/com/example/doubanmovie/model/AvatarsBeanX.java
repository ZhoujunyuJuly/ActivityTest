package com.example.doubanmovie.model;

/**
 * Created by zhoujunyu on 2019/3/19.
 */
public class AvatarsBeanX {
    /**
     * small : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p44818.jpg
     * large : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p44818.jpg
     * medium : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p44818.jpg
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
