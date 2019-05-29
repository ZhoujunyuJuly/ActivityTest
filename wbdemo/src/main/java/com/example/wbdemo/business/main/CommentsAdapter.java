package com.example.wbdemo.business.main;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wbdemo.info.commentdata.Comments;

import java.util.List;

/**
 * Created by zhoujunyu on 2019/5/29.
 */
public class CommentsAdapter extends BaseQuickAdapter<Comments,BaseViewHolder> {


    public CommentsAdapter(int layoutResId, @Nullable List<Comments> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Comments item) {

    }
}
