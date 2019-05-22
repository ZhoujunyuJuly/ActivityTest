package com.example.wbdemo.Object;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zhoujunyu on 2019/5/21.
 */
public class homeTimeLine {

    /**
     * statuses : [{"created_at":"Tue May 21 19:08:49 +0800 2019","id":4374463896095038,"idstr":"4374463896095038","mid":"4374463896095038","can_edit":false,"show_additional_indication":0,"text":"【竖版微视频丨任正非央视专访回应热点】21日下午，任正非在华为总部接受中央广播电视总台专访，回应热点。戳视频，看详情↓http://t.cn/E9IC9s7  http://t.cn/E9MvA1A \u200b","textLength":156,"source_allowclick":0,"source_type":1,"source":"<a href=\"http://app.weibo.com/t/feed/19DcdW\" rel=\"nofollow\">微博视频<\/a>","favorited":false,"truncated":false,"in_reply_to_status_id":"","in_reply_to_user_id":"","in_reply_to_screen_name":"","pic_urls":[],"geo":null,"is_paid":false,"mblog_vip_type":0,"user":{"id":1867571077,"idstr":"1867571077","class":1,"screen_name":"中央人民广播电台","name":"中央人民广播电台","province":"11","city":"2","location":"北京 西城区","description":"中国国家广播电台。中国最重要、最具有影响力的传媒之一。","url":"","profile_image_url":"http://tva2.sinaimg.cn/crop.4.6.173.173.50/6f50df85jw8ev747og30lj2050050glq.jpg","cover_image_phone":"http://ww1.sinaimg.cn/crop.0.0.640.640.640/638f41a8jw1exw22gdukxj20hs0hstgp.jpg","profile_url":"cnr","domain":"cnr","weihao":"","gender":"m","followers_count":3787820,"friends_count":617,"pagefriends_count":52,"statuses_count":31313,"video_status_count":0,"favourites_count":1463,"created_at":"Fri Nov 19 15:02:53 +0800 2010","following":true,"allow_all_act_msg":true,"geo_enabled":true,"verified":true,"verified_type":3,"remark":"","insecurity":{"sexual_content":false},"ptype":0,"allow_all_comment":true,"avatar_large":"http://tva2.sinaimg.cn/crop.4.6.173.173.180/6f50df85jw8ev747og30lj2050050glq.jpg","avatar_hd":"http://tva2.sinaimg.cn/crop.4.6.173.173.1024/6f50df85jw8ev747og30lj2050050glq.jpg","verified_reason":"中央人民广播电台·央广网微博","verified_trade":"","verified_reason_url":"","verified_source":"","verified_source_url":"","verified_state":0,"verified_level":3,"verified_type_ext":0,"has_service_tel":false,"verified_reason_modified":"中央人民广播电台·央广网微博","verified_contact_name":"","verified_contact_email":"","verified_contact_mobile":"","follow_me":false,"like":false,"like_me":false,"online_status":0,"bi_followers_count":347,"lang":"zh-cn","star":0,"mbtype":12,"mbrank":5,"block_word":1,"block_app":1,"credit_score":80,"user_ability":10814216,"cardid":"star_583","urank":48,"story_read_state":-1,"vclub_member":0,"is_teenager":0,"is_guardian":0,"is_teenager_list":0,"tab_manage":"[0, 0]"},"annotations":[{"mapi_request":true}],"reposts_count":0,"comments_count":0,"attitudes_count":0,"pending_approval_count":0,"isLongText":false,"hide_multi_attitude":1,"reward_exhibition_type":0,"hide_flag":1,"mlevel":0,"visible":{"type":0,"list_id":0},"biz_ids":[0,230444],"biz_feature":4294967304,"hasActionTypeCard":0,"darwin_tags":[],"hot_weibo_tags":[],"text_tag_tips":[],"mblogtype":0,"rid":"0_0_1_1413079424816173325_0_0_0","userType":0,"more_info_type":0,"cardid":"star_583","positive_recom_flag":0,"content_auth":0,"gif_ids":"","is_show_bulletin":2,"comment_manage_info":{"comment_permission_type":-1,"approval_comment_type":0}}]
     * advertises : []
     * ad : []
     * hasvisible : false
     * previous_cursor : 0
     * next_cursor : 4374461723925817
     * total_number : 150
     * interval : 2000
     * uve_blank : -1
     * since_id : 4374463896095038
     * max_id : 4374461723925817
     * has_unread : 0
     */

    private boolean hasvisible;
    private int previous_cursor;
    private long next_cursor;
    private int total_number;
    private int interval;
    private int uve_blank;
    private long since_id;
    private long max_id;
    private int has_unread;
    private List<StatusesBean> statuses;
    private List<?> advertises;
    private List<?> ad;

    public boolean isHasvisible() {
        return hasvisible;
    }

    public void setHasvisible(boolean hasvisible) {
        this.hasvisible = hasvisible;
    }

    public int getPrevious_cursor() {
        return previous_cursor;
    }

    public void setPrevious_cursor(int previous_cursor) {
        this.previous_cursor = previous_cursor;
    }

    public long getNext_cursor() {
        return next_cursor;
    }

    public void setNext_cursor(long next_cursor) {
        this.next_cursor = next_cursor;
    }

    public int getTotal_number() {
        return total_number;
    }

    public void setTotal_number(int total_number) {
        this.total_number = total_number;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getUve_blank() {
        return uve_blank;
    }

    public void setUve_blank(int uve_blank) {
        this.uve_blank = uve_blank;
    }

    public long getSince_id() {
        return since_id;
    }

    public void setSince_id(long since_id) {
        this.since_id = since_id;
    }

    public long getMax_id() {
        return max_id;
    }

    public void setMax_id(long max_id) {
        this.max_id = max_id;
    }

    public int getHas_unread() {
        return has_unread;
    }

    public void setHas_unread(int has_unread) {
        this.has_unread = has_unread;
    }

    public List<StatusesBean> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<StatusesBean> statuses) {
        this.statuses = statuses;
    }

    public List<?> getAdvertises() {
        return advertises;
    }

    public void setAdvertises(List<?> advertises) {
        this.advertises = advertises;
    }

    public List<?> getAd() {
        return ad;
    }

    public void setAd(List<?> ad) {
        this.ad = ad;
    }

    public static class StatusesBean {
        /**
         * created_at : Tue May 21 19:08:49 +0800 2019
         * id : 4374463896095038
         * idstr : 4374463896095038
         * mid : 4374463896095038
         * can_edit : false
         * show_additional_indication : 0
         * text : 【竖版微视频丨任正非央视专访回应热点】21日下午，任正非在华为总部接受中央广播电视总台专访，回应热点。戳视频，看详情↓http://t.cn/E9IC9s7  http://t.cn/E9MvA1A ​
         * textLength : 156
         * source_allowclick : 0
         * source_type : 1
         * source : <a href="http://app.weibo.com/t/feed/19DcdW" rel="nofollow">微博视频</a>
         * favorited : false
         * truncated : false
         * in_reply_to_status_id :
         * in_reply_to_user_id :
         * in_reply_to_screen_name :
         * pic_urls : []
         * geo : null
         * is_paid : false
         * mblog_vip_type : 0
         * user : {"id":1867571077,"idstr":"1867571077","class":1,"screen_name":"中央人民广播电台","name":"中央人民广播电台","province":"11","city":"2","location":"北京 西城区","description":"中国国家广播电台。中国最重要、最具有影响力的传媒之一。","url":"","profile_image_url":"http://tva2.sinaimg.cn/crop.4.6.173.173.50/6f50df85jw8ev747og30lj2050050glq.jpg","cover_image_phone":"http://ww1.sinaimg.cn/crop.0.0.640.640.640/638f41a8jw1exw22gdukxj20hs0hstgp.jpg","profile_url":"cnr","domain":"cnr","weihao":"","gender":"m","followers_count":3787820,"friends_count":617,"pagefriends_count":52,"statuses_count":31313,"video_status_count":0,"favourites_count":1463,"created_at":"Fri Nov 19 15:02:53 +0800 2010","following":true,"allow_all_act_msg":true,"geo_enabled":true,"verified":true,"verified_type":3,"remark":"","insecurity":{"sexual_content":false},"ptype":0,"allow_all_comment":true,"avatar_large":"http://tva2.sinaimg.cn/crop.4.6.173.173.180/6f50df85jw8ev747og30lj2050050glq.jpg","avatar_hd":"http://tva2.sinaimg.cn/crop.4.6.173.173.1024/6f50df85jw8ev747og30lj2050050glq.jpg","verified_reason":"中央人民广播电台·央广网微博","verified_trade":"","verified_reason_url":"","verified_source":"","verified_source_url":"","verified_state":0,"verified_level":3,"verified_type_ext":0,"has_service_tel":false,"verified_reason_modified":"中央人民广播电台·央广网微博","verified_contact_name":"","verified_contact_email":"","verified_contact_mobile":"","follow_me":false,"like":false,"like_me":false,"online_status":0,"bi_followers_count":347,"lang":"zh-cn","star":0,"mbtype":12,"mbrank":5,"block_word":1,"block_app":1,"credit_score":80,"user_ability":10814216,"cardid":"star_583","urank":48,"story_read_state":-1,"vclub_member":0,"is_teenager":0,"is_guardian":0,"is_teenager_list":0,"tab_manage":"[0, 0]"}
         * annotations : [{"mapi_request":true}]
         * reposts_count : 0
         * comments_count : 0
         * attitudes_count : 0
         * pending_approval_count : 0
         * isLongText : false
         * hide_multi_attitude : 1
         * reward_exhibition_type : 0
         * hide_flag : 1
         * mlevel : 0
         * visible : {"type":0,"list_id":0}
         * biz_ids : [0,230444]
         * biz_feature : 4294967304
         * hasActionTypeCard : 0
         * darwin_tags : []
         * hot_weibo_tags : []
         * text_tag_tips : []
         * mblogtype : 0
         * rid : 0_0_1_1413079424816173325_0_0_0
         * userType : 0
         * more_info_type : 0
         * cardid : star_583
         * positive_recom_flag : 0
         * content_auth : 0
         * gif_ids :
         * is_show_bulletin : 2
         * comment_manage_info : {"comment_permission_type":-1,"approval_comment_type":0}
         */

        private String created_at;
        private long id;
        private String idstr;
        private String mid;
        private boolean can_edit;
        private int show_additional_indication;
        private String text;
        private int textLength;
        private int source_allowclick;
        private int source_type;
        private String source;
        private boolean favorited;
        private boolean truncated;
        private String in_reply_to_status_id;
        private String in_reply_to_user_id;
        private String in_reply_to_screen_name;
        private Object geo;
        private boolean is_paid;
        private int mblog_vip_type;
        private UserBean user;
        private int reposts_count;
        private int comments_count;
        private int attitudes_count;
        private int pending_approval_count;
        private boolean isLongText;
        private int hide_multi_attitude;
        private int reward_exhibition_type;
        private int hide_flag;
        private int mlevel;
        private VisibleBean visible;
        private long biz_feature;
        private int hasActionTypeCard;
        private int mblogtype;
        private String rid;
        private int userType;
        private int more_info_type;
        private String cardid;
        private int positive_recom_flag;
        private int content_auth;
        private String gif_ids;
        private int is_show_bulletin;
        private CommentManageInfoBean comment_manage_info;
        private List<?> pic_urls;
        private List<AnnotationsBean> annotations;
        private List<Integer> biz_ids;
        private List<?> darwin_tags;
        private List<?> hot_weibo_tags;
        private List<?> text_tag_tips;

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getIdstr() {
            return idstr;
        }

        public void setIdstr(String idstr) {
            this.idstr = idstr;
        }

        public String getMid() {
            return mid;
        }

        public void setMid(String mid) {
            this.mid = mid;
        }

        public boolean isCan_edit() {
            return can_edit;
        }

        public void setCan_edit(boolean can_edit) {
            this.can_edit = can_edit;
        }

        public int getShow_additional_indication() {
            return show_additional_indication;
        }

        public void setShow_additional_indication(int show_additional_indication) {
            this.show_additional_indication = show_additional_indication;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getTextLength() {
            return textLength;
        }

        public void setTextLength(int textLength) {
            this.textLength = textLength;
        }

        public int getSource_allowclick() {
            return source_allowclick;
        }

        public void setSource_allowclick(int source_allowclick) {
            this.source_allowclick = source_allowclick;
        }

        public int getSource_type() {
            return source_type;
        }

        public void setSource_type(int source_type) {
            this.source_type = source_type;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public boolean isFavorited() {
            return favorited;
        }

        public void setFavorited(boolean favorited) {
            this.favorited = favorited;
        }

        public boolean isTruncated() {
            return truncated;
        }

        public void setTruncated(boolean truncated) {
            this.truncated = truncated;
        }

        public String getIn_reply_to_status_id() {
            return in_reply_to_status_id;
        }

        public void setIn_reply_to_status_id(String in_reply_to_status_id) {
            this.in_reply_to_status_id = in_reply_to_status_id;
        }

        public String getIn_reply_to_user_id() {
            return in_reply_to_user_id;
        }

        public void setIn_reply_to_user_id(String in_reply_to_user_id) {
            this.in_reply_to_user_id = in_reply_to_user_id;
        }

        public String getIn_reply_to_screen_name() {
            return in_reply_to_screen_name;
        }

        public void setIn_reply_to_screen_name(String in_reply_to_screen_name) {
            this.in_reply_to_screen_name = in_reply_to_screen_name;
        }

        public Object getGeo() {
            return geo;
        }

        public void setGeo(Object geo) {
            this.geo = geo;
        }

        public boolean isIs_paid() {
            return is_paid;
        }

        public void setIs_paid(boolean is_paid) {
            this.is_paid = is_paid;
        }

        public int getMblog_vip_type() {
            return mblog_vip_type;
        }

        public void setMblog_vip_type(int mblog_vip_type) {
            this.mblog_vip_type = mblog_vip_type;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public int getReposts_count() {
            return reposts_count;
        }

        public void setReposts_count(int reposts_count) {
            this.reposts_count = reposts_count;
        }

        public int getComments_count() {
            return comments_count;
        }

        public void setComments_count(int comments_count) {
            this.comments_count = comments_count;
        }

        public int getAttitudes_count() {
            return attitudes_count;
        }

        public void setAttitudes_count(int attitudes_count) {
            this.attitudes_count = attitudes_count;
        }

        public int getPending_approval_count() {
            return pending_approval_count;
        }

        public void setPending_approval_count(int pending_approval_count) {
            this.pending_approval_count = pending_approval_count;
        }

        public boolean isIsLongText() {
            return isLongText;
        }

        public void setIsLongText(boolean isLongText) {
            this.isLongText = isLongText;
        }

        public int getHide_multi_attitude() {
            return hide_multi_attitude;
        }

        public void setHide_multi_attitude(int hide_multi_attitude) {
            this.hide_multi_attitude = hide_multi_attitude;
        }

        public int getReward_exhibition_type() {
            return reward_exhibition_type;
        }

        public void setReward_exhibition_type(int reward_exhibition_type) {
            this.reward_exhibition_type = reward_exhibition_type;
        }

        public int getHide_flag() {
            return hide_flag;
        }

        public void setHide_flag(int hide_flag) {
            this.hide_flag = hide_flag;
        }

        public int getMlevel() {
            return mlevel;
        }

        public void setMlevel(int mlevel) {
            this.mlevel = mlevel;
        }

        public VisibleBean getVisible() {
            return visible;
        }

        public void setVisible(VisibleBean visible) {
            this.visible = visible;
        }

        public long getBiz_feature() {
            return biz_feature;
        }

        public void setBiz_feature(long biz_feature) {
            this.biz_feature = biz_feature;
        }

        public int getHasActionTypeCard() {
            return hasActionTypeCard;
        }

        public void setHasActionTypeCard(int hasActionTypeCard) {
            this.hasActionTypeCard = hasActionTypeCard;
        }

        public int getMblogtype() {
            return mblogtype;
        }

        public void setMblogtype(int mblogtype) {
            this.mblogtype = mblogtype;
        }

        public String getRid() {
            return rid;
        }

        public void setRid(String rid) {
            this.rid = rid;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public int getMore_info_type() {
            return more_info_type;
        }

        public void setMore_info_type(int more_info_type) {
            this.more_info_type = more_info_type;
        }

        public String getCardid() {
            return cardid;
        }

        public void setCardid(String cardid) {
            this.cardid = cardid;
        }

        public int getPositive_recom_flag() {
            return positive_recom_flag;
        }

        public void setPositive_recom_flag(int positive_recom_flag) {
            this.positive_recom_flag = positive_recom_flag;
        }

        public int getContent_auth() {
            return content_auth;
        }

        public void setContent_auth(int content_auth) {
            this.content_auth = content_auth;
        }

        public String getGif_ids() {
            return gif_ids;
        }

        public void setGif_ids(String gif_ids) {
            this.gif_ids = gif_ids;
        }

        public int getIs_show_bulletin() {
            return is_show_bulletin;
        }

        public void setIs_show_bulletin(int is_show_bulletin) {
            this.is_show_bulletin = is_show_bulletin;
        }

        public CommentManageInfoBean getComment_manage_info() {
            return comment_manage_info;
        }

        public void setComment_manage_info(CommentManageInfoBean comment_manage_info) {
            this.comment_manage_info = comment_manage_info;
        }

        public List<?> getPic_urls() {
            return pic_urls;
        }

        public void setPic_urls(List<?> pic_urls) {
            this.pic_urls = pic_urls;
        }

        public List<AnnotationsBean> getAnnotations() {
            return annotations;
        }

        public void setAnnotations(List<AnnotationsBean> annotations) {
            this.annotations = annotations;
        }

        public List<Integer> getBiz_ids() {
            return biz_ids;
        }

        public void setBiz_ids(List<Integer> biz_ids) {
            this.biz_ids = biz_ids;
        }

        public List<?> getDarwin_tags() {
            return darwin_tags;
        }

        public void setDarwin_tags(List<?> darwin_tags) {
            this.darwin_tags = darwin_tags;
        }

        public List<?> getHot_weibo_tags() {
            return hot_weibo_tags;
        }

        public void setHot_weibo_tags(List<?> hot_weibo_tags) {
            this.hot_weibo_tags = hot_weibo_tags;
        }

        public List<?> getText_tag_tips() {
            return text_tag_tips;
        }

        public void setText_tag_tips(List<?> text_tag_tips) {
            this.text_tag_tips = text_tag_tips;
        }

        public static class UserBean {
            /**
             * id : 1867571077
             * idstr : 1867571077
             * class : 1
             * screen_name : 中央人民广播电台
             * name : 中央人民广播电台
             * province : 11
             * city : 2
             * location : 北京 西城区
             * description : 中国国家广播电台。中国最重要、最具有影响力的传媒之一。
             * url :
             * profile_image_url : http://tva2.sinaimg.cn/crop.4.6.173.173.50/6f50df85jw8ev747og30lj2050050glq.jpg
             * cover_image_phone : http://ww1.sinaimg.cn/crop.0.0.640.640.640/638f41a8jw1exw22gdukxj20hs0hstgp.jpg
             * profile_url : cnr
             * domain : cnr
             * weihao :
             * gender : m
             * followers_count : 3787820
             * friends_count : 617
             * pagefriends_count : 52
             * statuses_count : 31313
             * video_status_count : 0
             * favourites_count : 1463
             * created_at : Fri Nov 19 15:02:53 +0800 2010
             * following : true
             * allow_all_act_msg : true
             * geo_enabled : true
             * verified : true
             * verified_type : 3
             * remark :
             * insecurity : {"sexual_content":false}
             * ptype : 0
             * allow_all_comment : true
             * avatar_large : http://tva2.sinaimg.cn/crop.4.6.173.173.180/6f50df85jw8ev747og30lj2050050glq.jpg
             * avatar_hd : http://tva2.sinaimg.cn/crop.4.6.173.173.1024/6f50df85jw8ev747og30lj2050050glq.jpg
             * verified_reason : 中央人民广播电台·央广网微博
             * verified_trade :
             * verified_reason_url :
             * verified_source :
             * verified_source_url :
             * verified_state : 0
             * verified_level : 3
             * verified_type_ext : 0
             * has_service_tel : false
             * verified_reason_modified : 中央人民广播电台·央广网微博
             * verified_contact_name :
             * verified_contact_email :
             * verified_contact_mobile :
             * follow_me : false
             * like : false
             * like_me : false
             * online_status : 0
             * bi_followers_count : 347
             * lang : zh-cn
             * star : 0
             * mbtype : 12
             * mbrank : 5
             * block_word : 1
             * block_app : 1
             * credit_score : 80
             * user_ability : 10814216
             * cardid : star_583
             * urank : 48
             * story_read_state : -1
             * vclub_member : 0
             * is_teenager : 0
             * is_guardian : 0
             * is_teenager_list : 0
             * tab_manage : [0, 0]
             */

            private int id;
            private String idstr;
            @SerializedName("class")
            private int classX;
            private String screen_name;
            private String name;
            private String province;
            private String city;
            private String location;
            private String description;
            private String url;
            private String profile_image_url;
            private String cover_image_phone;
            private String profile_url;
            private String domain;
            private String weihao;
            private String gender;
            private int followers_count;
            private int friends_count;
            private int pagefriends_count;
            private int statuses_count;
            private int video_status_count;
            private int favourites_count;
            private String created_at;
            private boolean following;
            private boolean allow_all_act_msg;
            private boolean geo_enabled;
            private boolean verified;
            private int verified_type;
            private String remark;
            private InsecurityBean insecurity;
            private int ptype;
            private boolean allow_all_comment;
            private String avatar_large;
            private String avatar_hd;
            private String verified_reason;
            private String verified_trade;
            private String verified_reason_url;
            private String verified_source;
            private String verified_source_url;
            private int verified_state;
            private int verified_level;
            private int verified_type_ext;
            private boolean has_service_tel;
            private String verified_reason_modified;
            private String verified_contact_name;
            private String verified_contact_email;
            private String verified_contact_mobile;
            private boolean follow_me;
            private boolean like;
            private boolean like_me;
            private int online_status;
            private int bi_followers_count;
            private String lang;
            private int star;
            private int mbtype;
            private int mbrank;
            private int block_word;
            private int block_app;
            private int credit_score;
            private int user_ability;
            private String cardid;
            private int urank;
            private int story_read_state;
            private int vclub_member;
            private int is_teenager;
            private int is_guardian;
            private int is_teenager_list;
            private String tab_manage;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIdstr() {
                return idstr;
            }

            public void setIdstr(String idstr) {
                this.idstr = idstr;
            }

            public int getClassX() {
                return classX;
            }

            public void setClassX(int classX) {
                this.classX = classX;
            }

            public String getScreen_name() {
                return screen_name;
            }

            public void setScreen_name(String screen_name) {
                this.screen_name = screen_name;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getProfile_image_url() {
                return profile_image_url;
            }

            public void setProfile_image_url(String profile_image_url) {
                this.profile_image_url = profile_image_url;
            }

            public String getCover_image_phone() {
                return cover_image_phone;
            }

            public void setCover_image_phone(String cover_image_phone) {
                this.cover_image_phone = cover_image_phone;
            }

            public String getProfile_url() {
                return profile_url;
            }

            public void setProfile_url(String profile_url) {
                this.profile_url = profile_url;
            }

            public String getDomain() {
                return domain;
            }

            public void setDomain(String domain) {
                this.domain = domain;
            }

            public String getWeihao() {
                return weihao;
            }

            public void setWeihao(String weihao) {
                this.weihao = weihao;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public int getFollowers_count() {
                return followers_count;
            }

            public void setFollowers_count(int followers_count) {
                this.followers_count = followers_count;
            }

            public int getFriends_count() {
                return friends_count;
            }

            public void setFriends_count(int friends_count) {
                this.friends_count = friends_count;
            }

            public int getPagefriends_count() {
                return pagefriends_count;
            }

            public void setPagefriends_count(int pagefriends_count) {
                this.pagefriends_count = pagefriends_count;
            }

            public int getStatuses_count() {
                return statuses_count;
            }

            public void setStatuses_count(int statuses_count) {
                this.statuses_count = statuses_count;
            }

            public int getVideo_status_count() {
                return video_status_count;
            }

            public void setVideo_status_count(int video_status_count) {
                this.video_status_count = video_status_count;
            }

            public int getFavourites_count() {
                return favourites_count;
            }

            public void setFavourites_count(int favourites_count) {
                this.favourites_count = favourites_count;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public boolean isFollowing() {
                return following;
            }

            public void setFollowing(boolean following) {
                this.following = following;
            }

            public boolean isAllow_all_act_msg() {
                return allow_all_act_msg;
            }

            public void setAllow_all_act_msg(boolean allow_all_act_msg) {
                this.allow_all_act_msg = allow_all_act_msg;
            }

            public boolean isGeo_enabled() {
                return geo_enabled;
            }

            public void setGeo_enabled(boolean geo_enabled) {
                this.geo_enabled = geo_enabled;
            }

            public boolean isVerified() {
                return verified;
            }

            public void setVerified(boolean verified) {
                this.verified = verified;
            }

            public int getVerified_type() {
                return verified_type;
            }

            public void setVerified_type(int verified_type) {
                this.verified_type = verified_type;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public InsecurityBean getInsecurity() {
                return insecurity;
            }

            public void setInsecurity(InsecurityBean insecurity) {
                this.insecurity = insecurity;
            }

            public int getPtype() {
                return ptype;
            }

            public void setPtype(int ptype) {
                this.ptype = ptype;
            }

            public boolean isAllow_all_comment() {
                return allow_all_comment;
            }

            public void setAllow_all_comment(boolean allow_all_comment) {
                this.allow_all_comment = allow_all_comment;
            }

            public String getAvatar_large() {
                return avatar_large;
            }

            public void setAvatar_large(String avatar_large) {
                this.avatar_large = avatar_large;
            }

            public String getAvatar_hd() {
                return avatar_hd;
            }

            public void setAvatar_hd(String avatar_hd) {
                this.avatar_hd = avatar_hd;
            }

            public String getVerified_reason() {
                return verified_reason;
            }

            public void setVerified_reason(String verified_reason) {
                this.verified_reason = verified_reason;
            }

            public String getVerified_trade() {
                return verified_trade;
            }

            public void setVerified_trade(String verified_trade) {
                this.verified_trade = verified_trade;
            }

            public String getVerified_reason_url() {
                return verified_reason_url;
            }

            public void setVerified_reason_url(String verified_reason_url) {
                this.verified_reason_url = verified_reason_url;
            }

            public String getVerified_source() {
                return verified_source;
            }

            public void setVerified_source(String verified_source) {
                this.verified_source = verified_source;
            }

            public String getVerified_source_url() {
                return verified_source_url;
            }

            public void setVerified_source_url(String verified_source_url) {
                this.verified_source_url = verified_source_url;
            }

            public int getVerified_state() {
                return verified_state;
            }

            public void setVerified_state(int verified_state) {
                this.verified_state = verified_state;
            }

            public int getVerified_level() {
                return verified_level;
            }

            public void setVerified_level(int verified_level) {
                this.verified_level = verified_level;
            }

            public int getVerified_type_ext() {
                return verified_type_ext;
            }

            public void setVerified_type_ext(int verified_type_ext) {
                this.verified_type_ext = verified_type_ext;
            }

            public boolean isHas_service_tel() {
                return has_service_tel;
            }

            public void setHas_service_tel(boolean has_service_tel) {
                this.has_service_tel = has_service_tel;
            }

            public String getVerified_reason_modified() {
                return verified_reason_modified;
            }

            public void setVerified_reason_modified(String verified_reason_modified) {
                this.verified_reason_modified = verified_reason_modified;
            }

            public String getVerified_contact_name() {
                return verified_contact_name;
            }

            public void setVerified_contact_name(String verified_contact_name) {
                this.verified_contact_name = verified_contact_name;
            }

            public String getVerified_contact_email() {
                return verified_contact_email;
            }

            public void setVerified_contact_email(String verified_contact_email) {
                this.verified_contact_email = verified_contact_email;
            }

            public String getVerified_contact_mobile() {
                return verified_contact_mobile;
            }

            public void setVerified_contact_mobile(String verified_contact_mobile) {
                this.verified_contact_mobile = verified_contact_mobile;
            }

            public boolean isFollow_me() {
                return follow_me;
            }

            public void setFollow_me(boolean follow_me) {
                this.follow_me = follow_me;
            }

            public boolean isLike() {
                return like;
            }

            public void setLike(boolean like) {
                this.like = like;
            }

            public boolean isLike_me() {
                return like_me;
            }

            public void setLike_me(boolean like_me) {
                this.like_me = like_me;
            }

            public int getOnline_status() {
                return online_status;
            }

            public void setOnline_status(int online_status) {
                this.online_status = online_status;
            }

            public int getBi_followers_count() {
                return bi_followers_count;
            }

            public void setBi_followers_count(int bi_followers_count) {
                this.bi_followers_count = bi_followers_count;
            }

            public String getLang() {
                return lang;
            }

            public void setLang(String lang) {
                this.lang = lang;
            }

            public int getStar() {
                return star;
            }

            public void setStar(int star) {
                this.star = star;
            }

            public int getMbtype() {
                return mbtype;
            }

            public void setMbtype(int mbtype) {
                this.mbtype = mbtype;
            }

            public int getMbrank() {
                return mbrank;
            }

            public void setMbrank(int mbrank) {
                this.mbrank = mbrank;
            }

            public int getBlock_word() {
                return block_word;
            }

            public void setBlock_word(int block_word) {
                this.block_word = block_word;
            }

            public int getBlock_app() {
                return block_app;
            }

            public void setBlock_app(int block_app) {
                this.block_app = block_app;
            }

            public int getCredit_score() {
                return credit_score;
            }

            public void setCredit_score(int credit_score) {
                this.credit_score = credit_score;
            }

            public int getUser_ability() {
                return user_ability;
            }

            public void setUser_ability(int user_ability) {
                this.user_ability = user_ability;
            }

            public String getCardid() {
                return cardid;
            }

            public void setCardid(String cardid) {
                this.cardid = cardid;
            }

            public int getUrank() {
                return urank;
            }

            public void setUrank(int urank) {
                this.urank = urank;
            }

            public int getStory_read_state() {
                return story_read_state;
            }

            public void setStory_read_state(int story_read_state) {
                this.story_read_state = story_read_state;
            }

            public int getVclub_member() {
                return vclub_member;
            }

            public void setVclub_member(int vclub_member) {
                this.vclub_member = vclub_member;
            }

            public int getIs_teenager() {
                return is_teenager;
            }

            public void setIs_teenager(int is_teenager) {
                this.is_teenager = is_teenager;
            }

            public int getIs_guardian() {
                return is_guardian;
            }

            public void setIs_guardian(int is_guardian) {
                this.is_guardian = is_guardian;
            }

            public int getIs_teenager_list() {
                return is_teenager_list;
            }

            public void setIs_teenager_list(int is_teenager_list) {
                this.is_teenager_list = is_teenager_list;
            }

            public String getTab_manage() {
                return tab_manage;
            }

            public void setTab_manage(String tab_manage) {
                this.tab_manage = tab_manage;
            }

            public static class InsecurityBean {
                /**
                 * sexual_content : false
                 */

                private boolean sexual_content;

                public boolean isSexual_content() {
                    return sexual_content;
                }

                public void setSexual_content(boolean sexual_content) {
                    this.sexual_content = sexual_content;
                }
            }
        }

        public static class VisibleBean {
            /**
             * type : 0
             * list_id : 0
             */

            private int type;
            private int list_id;

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getList_id() {
                return list_id;
            }

            public void setList_id(int list_id) {
                this.list_id = list_id;
            }
        }

        public static class CommentManageInfoBean {
            /**
             * comment_permission_type : -1
             * approval_comment_type : 0
             */

            private int comment_permission_type;
            private int approval_comment_type;

            public int getComment_permission_type() {
                return comment_permission_type;
            }

            public void setComment_permission_type(int comment_permission_type) {
                this.comment_permission_type = comment_permission_type;
            }

            public int getApproval_comment_type() {
                return approval_comment_type;
            }

            public void setApproval_comment_type(int approval_comment_type) {
                this.approval_comment_type = approval_comment_type;
            }
        }

        public static class AnnotationsBean {
            /**
             * mapi_request : true
             */

            private boolean mapi_request;

            public boolean isMapi_request() {
                return mapi_request;
            }

            public void setMapi_request(boolean mapi_request) {
                this.mapi_request = mapi_request;
            }
        }
    }
}
