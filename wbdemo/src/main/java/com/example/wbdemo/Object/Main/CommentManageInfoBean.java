package com.example.wbdemo.Object.Main;

/**
 * Created by zhoujunyu on 2019/5/23.
 */
public class CommentManageInfoBean {
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
