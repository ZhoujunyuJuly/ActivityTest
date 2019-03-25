package com.example.doubanmovie;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doubanmovie.model.DetailMode.Detail;

/**
 * Created by zhoujunyu on 2019/3/21.
 */
public class CommentsAdapter extends RecyclerView.Adapter<ItemViewHolder.MiddleCommentsViewHolder> {
    public Detail mData;
    private Context mContext;

    public CommentsAdapter(Detail data) {
        mData = data;

    }


    @NonNull
    @Override
    public ItemViewHolder.MiddleCommentsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.middle_comments, viewGroup, false);
        ItemViewHolder holder = new ItemViewHolder();
        ItemViewHolder.MiddleCommentsViewHolder commentsViewHolder = holder.new MiddleCommentsViewHolder(view);

        return commentsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder.MiddleCommentsViewHolder viewHolder, int i) {
        viewHolder.mUserName.setText(mData.getPopular_comments().get(i).getAuthor().getName());
        viewHolder.mUserComments.setText(mData.getPopular_comments().get(i).getContent());
        viewHolder.mStar.setRating(mData.getPopular_comments().get(i).getRating().getValue().floatValue());
        viewHolder.mUserTime.setText(mData.getPopular_comments().get(i).getCreated_at());

//        Glide.with(mContext).load(mData.getPopular_comments().get(i).getAuthor().getAvatar())
//                .centerCrop()
//                .placeholder(R.mipmap.ic_launcher)
//                .into(viewHolder.mPortrait);

    }

    @Override
    public int getItemCount() {
        return mData.getPopular_comments().size();
    }
}
