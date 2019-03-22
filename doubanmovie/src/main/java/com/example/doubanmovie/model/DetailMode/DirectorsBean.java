package com.example.doubanmovie.model.DetailMode;

/**
 * Created by zhoujunyu on 2019/3/21.
 */
public class DirectorsBean {

    /**
     * avatars : {"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p44818.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p44818.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p44818.jpg"}
     * name_en : Hsiao Chien Lin
     * name : 林孝谦
     * alt : https://movie.douban.com/celebrity/1312860/
     * id : 1312860
     */

    private AvatarsBeanXX avatars;
    private String name_en;
    private String name;
    private String alt;
    private String id;

    public AvatarsBeanXX getAvatars() {
        return avatars;
    }

    public void setAvatars(AvatarsBeanXX avatars) {
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

    public static class AvatarsBeanXX {
        /**
         * small : http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p44818.jpg
         * large : http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p44818.jpg
         * medium : http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p44818.jpg
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
