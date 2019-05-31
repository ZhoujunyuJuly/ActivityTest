package com.example.wbdemo.business.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wbdemo.R;
import com.example.wbdemo.info.commentdata.CommentsBean;

import java.util.List;

/**
 * Created by zhoujunyu on 2019/5/29.
 */
public class CommentsAdapter extends BaseQuickAdapter<CommentsBean,BaseViewHolder> {

    private Context mContext;

    public CommentsAdapter(int layoutResId, @Nullable List<CommentsBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;

    }

    @Override
    protected void convert(BaseViewHolder helper, CommentsBean item) {
        Glide.with(mContext).load(item.getUser().getAvatar_large()).into((ImageView)helper.getView(R.id.iv_comments_portrait));
        helper.setText(R.id.tv_comments_name,item.getUser().getName());
        helper.setText(R.id.tv_comments_time,item.getCreated_at());
        helper.setText(R.id.tv_comments_content,item.getText());
        helper.setText(R.id.tv_comments_attitudeCounts,String.valueOf(item.getUser().getFavourites_count()));

    }
}
