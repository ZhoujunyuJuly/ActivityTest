package com.example.doubanmovie.model.DetailMode;

/**
 * Created by zhoujunyu on 2019/3/21.
 */
public class PopularReviewsBean {
    /**
     * rating : {"max":5,"value":2,"min":0}
     * title : 《比悲傷更悲傷的故事》／無病呻吟的悲傷！
     * subject_id : 27624661
     * author : {"uid":"sunline","avatar":"http://img1.doubanio.com/icon/u1336357-4.jpg","signature":"","alt":"https://www.douban.com/people/sunline/","id":"1336357","name":"換日線"}
     * summary : 我很喜歡這部戲的演員，從劉以豪、陳意涵、張書豪、布魯斯（幹嘛改名啊！）以及這部戲野性狂放、美到讓人驚豔的陳庭妮。並且第一次發現布魯斯的聲線真是好聽。 只是這個故事如我預期的一模一樣，完全是我無法忍受...
     * alt : https://movie.douban.com/review/9851170/
     * id : 9851170
     */

    private RatingBeanXX rating;
    private String title;
    private String subject_id;
    private AuthorBeanX author;
    private String summary;
    private String alt;
    private String id;

    public RatingBeanXX getRating() {
        return rating;
    }

    public void setRating(RatingBeanXX rating) {
        this.rating = rating;
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

    public AuthorBeanX getAuthor() {
        return author;
    }

    public void setAuthor(AuthorBeanX author) {
        this.author = author;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    public static class RatingBeanXX {
        /**
         * max : 5
         * value : 2.0
         * min : 0
         */

        private int max;
        private double value;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public static class AuthorBeanX {
        /**
         * uid : sunline
         * avatar : http://img1.doubanio.com/icon/u1336357-4.jpg
         * signature :
         * alt : https://www.douban.com/people/sunline/
         * id : 1336357
         * name : 換日線
         */

        private String uid;
        private String avatar;
        private String signature;
        private String alt;
        private String id;
        private String name;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
