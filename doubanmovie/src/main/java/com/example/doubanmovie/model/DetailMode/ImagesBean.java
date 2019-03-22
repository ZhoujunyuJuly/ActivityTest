package com.example.doubanmovie.model.DetailMode;

/**
 * Created by zhoujunyu on 2019/3/21.
 */
public class ImagesBean {
        /**
         * small : http://img1.doubanio.com/view/photo/s_ratio_poster/public/p2549523952.jpg
         * large : http://img1.doubanio.com/view/photo/s_ratio_poster/public/p2549523952.jpg
         * medium : http://img1.doubanio.com/view/photo/s_ratio_poster/public/p2549523952.jpg
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
