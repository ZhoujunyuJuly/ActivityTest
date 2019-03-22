package com.example.doubanmovie.model.DetailMode;

/**
 * Created by zhoujunyu on 2019/3/21.
 */
public class PopularCommentsBean {
        /**
         * rating : {"max":5,"value":1,"min":0}
         * useful_count : 560
         * author : {"uid":"149734944","avatar":"http://img1.doubanio.com/icon/u149734944-25.jpg","signature":"Do your best.","alt":"https://www.douban.com/people/149734944/","id":"149734944","name":"黛比兔"}
         * subject_id : 27624661
         * content : 台灣現在只能拍出這種片了嗎⋯⋯在電影院裡看的尬死了
         * created_at : 2019-02-13 11:48:37
         * id : 1672508710
         */

        private RatingBeanX rating;
        private int useful_count;
        private AuthorBean author;
        private String subject_id;
        private String content;
        private String created_at;
        private String id;

        public RatingBeanX getRating() {
            return rating;
        }

        public void setRating(RatingBeanX rating) {
            this.rating = rating;
        }

        public int getUseful_count() {
            return useful_count;
        }

        public void setUseful_count(int useful_count) {
            this.useful_count = useful_count;
        }

        public AuthorBean getAuthor() {
            return author;
        }

        public void setAuthor(AuthorBean author) {
            this.author = author;
        }

        public String getSubject_id() {
            return subject_id;
        }

        public void setSubject_id(String subject_id) {
            this.subject_id = subject_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
}
