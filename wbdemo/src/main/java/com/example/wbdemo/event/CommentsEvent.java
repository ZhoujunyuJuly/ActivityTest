package com.example.wbdemo.event;

import android.content.Context;

import com.example.wbdemo.info.commentdata.CommentsBean;

import java.util.List;

/**
 * Created by zhoujunyu on 2019/5/31.
 */
public class CommentsEvent {

    private static List<CommentsBean> CommentsList;


    public static CommentsEvent CommentsEvent(List<CommentsBean> comments) {
        CommentsList = comments;
        return new CommentsEvent();
    }

    public static List<CommentsBean> getCommentsList() {
        return CommentsList;
    }

    public static void setCommentsList(List<CommentsBean> mCommentsList) {
        CommentsEvent.CommentsList = mCommentsList;
    }
}
