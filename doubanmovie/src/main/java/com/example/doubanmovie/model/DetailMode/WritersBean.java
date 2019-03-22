package com.example.doubanmovie.model.DetailMode;

/**
 * Created by zhoujunyu on 2019/3/21.
 */
public class WritersBean {
    /**
     * avatars : {"small":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1376030118.5.jpg","large":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1376030118.5.jpg","medium":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1376030118.5.jpg"}
     * name_en : Lungshi Lü
     * name : 吕安弦
     * alt : https://movie.douban.com/celebrity/1332731/
     * id : 1332731
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

    public static class AvatarsBean {
        /**
         * small : http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1376030118.5.jpg
         * large : http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1376030118.5.jpg
         * medium : http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1376030118.5.jpg
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
