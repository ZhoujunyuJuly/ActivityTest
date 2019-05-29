package com.example.wbdemo.info.maindata;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zhoujunyu on 2019/5/23.
 */
public class StatusesBean {
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
    private List<AnnotationsBean> annotations;
    private List<Integer> biz_ids;
    private List<?> darwin_tags;
    private List<?> hot_weibo_tags;
    private List<?> text_tag_tips;
    private String original_pic;

    @SerializedName("pic_urls")
    private List<PicUrlsBean> pic_urls;

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


    //九宫格
    public List<PicUrlsBean> getPic_urls() {
        return pic_urls;
    }

    public void setPic_urls(List<PicUrlsBean> pic_urls) {
        this.pic_urls = pic_urls;
    }


    public static class PicUrlsBean {
        /**
         * thumbnail_pic : http://wx1.sinaimg.cn/thumbnail/80762695ly1g3b2qrfj9mj20j62n1gt6.jpg
         */

        private String thumbnail_pic;

        public String getThumbnail_pic() {
            return thumbnail_pic;
        }

        public void setThumbnail_pic(String thumbnail_pic) {
            this.thumbnail_pic = thumbnail_pic;
        }
    }


    public String getOriginal_pic() {
        return original_pic;
    }

    public void setOriginal_pic(String original_pic) {
        this.original_pic = original_pic;
    }
}
