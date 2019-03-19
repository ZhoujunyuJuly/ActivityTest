package com.example.doubanmovie;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zhoujunyu on 2019/3/19.
 */
public class Movie {


    /**
     * count : 100
     * start : 0
     * total : 30
     * title : 正在上映的电影-北京
     */

    private int count;
    private int start;
    private int total;
    private String title;
    private List<SubjectsBean> subjects;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubjectsBean> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectsBean> subjects) {
        this.subjects = subjects;
    }

    public static class SubjectsBean {
        /**
         * rating : {"max":10,"average":5,"details":{"1":601,"3":1056,"2":980,"5":145,"4":328},"stars":"25","min":0}
         * genres : ["爱情"]
         * title : 比悲伤更悲伤的故事
         * casts : [{"avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p31663.jpg",
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

        public static class RatingBean {
            /**
             * max : 10
             * average : 5.0
             * details : {"1":601,"3":1056,"2":980,"5":145,"4":328}
             * stars : 25
             * min : 0
             */

            private int max;
            private double average;
            private DetailsBean details;
            private String stars;
            private int min;

            public int getMax() {
                return max;
            }

            public void setMax(int max) {
                this.max = max;
            }

            public double getAverage() {
                return average;
            }

            public void setAverage(double average) {
                this.average = average;
            }

            public DetailsBean getDetails() {
                return details;
            }

            public void setDetails(DetailsBean details) {
                this.details = details;
            }

            public String getStars() {
                return stars;
            }

            public void setStars(String stars) {
                this.stars = stars;
            }

            public int getMin() {
                return min;
            }

            public void setMin(int min) {
                this.min = min;
            }

            public static class DetailsBean {
                /**
                 * 1 : 601.0
                 * 3 : 1056.0
                 * 2 : 980.0
                 * 5 : 145.0
                 * 4 : 328.0
                 */

                @SerializedName("1")
                private double _$1;
                @SerializedName("3")
                private double _$3;
                @SerializedName("2")
                private double _$2;
                @SerializedName("5")
                private double _$5;
                @SerializedName("4")
                private double _$4;

                public double get_$1() {
                    return _$1;
                }

                public void set_$1(double _$1) {
                    this._$1 = _$1;
                }

                public double get_$3() {
                    return _$3;
                }

                public void set_$3(double _$3) {
                    this._$3 = _$3;
                }

                public double get_$2() {
                    return _$2;
                }

                public void set_$2(double _$2) {
                    this._$2 = _$2;
                }

                public double get_$5() {
                    return _$5;
                }

                public void set_$5(double _$5) {
                    this._$5 = _$5;
                }

                public double get_$4() {
                    return _$4;
                }

                public void set_$4(double _$4) {
                    this._$4 = _$4;
                }
            }
        }

        public static class ImagesBean {
            /**
             * small : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2549523952.jpg
             * large : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2549523952.jpg
             * medium : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2549523952.jpg
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

        public static class CastsBean {
            /**
             * avatars : {"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p31663.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p31663.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p31663.jpg"}
             * name_en : Ivy Chen
             * name : 陈意涵
             * alt : https://movie.douban.com/celebrity/1274316/
             * id : 1274316
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
        }

        public static class DirectorsBean {
            /**
             * avatars : {"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p44818.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p44818.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p44818.jpg"}
             * name_en : Hsiao Chien Lin
             * name : 林孝谦
             * alt : https://movie.douban.com/celebrity/1312860/
             * id : 1312860
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
        }
    }
}
