package com.example.doubanmovie.model.DetailMode;

/**
 * Created by zhoujunyu on 2019/3/21.
 */
public class CastsBean {
    /**
     * avatars : {"small":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p31663.jpg","large":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p31663.jpg","medium":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p31663.jpg"}
     * name_en : Ivy Chen
     * name : 陈意涵
     * alt : https://movie.douban.com/celebrity/1274316/
     * id : 1274316
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

    public static class AvatarsBeanX {
        /**
         * small : http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p31663.jpg
         * large : http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p31663.jpg
         * medium : http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p31663.jpg
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
}
