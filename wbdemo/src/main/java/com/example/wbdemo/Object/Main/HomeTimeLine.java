package com.example.wbdemo.Object.Main;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zhoujunyu on 2019/5/21.
 */
public class HomeTimeLine {

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
}
