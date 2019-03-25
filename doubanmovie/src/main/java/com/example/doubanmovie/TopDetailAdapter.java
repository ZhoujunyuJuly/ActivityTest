package com.example.doubanmovie;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.doubanmovie.model.DetailMode.Detail;

import java.util.List;

/**
 * Created by zhoujunyu on 2019/3/21.
 */
public class TopDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_HEAD = 0;
    public static final int TYPE_MIDDLE_INFO = 1;
    public static final int TYPE_MIDDLE_COMMENTS = 2;
    private final List<Integer> mTypeList;
    private Detail mListData;
    private Context mContext;

    public TopDetailAdapter(List<Integer> list, Detail listData, Context Context) {
        mTypeList = list;
        mListData = listData;
        mContext = Context;
    }

    @Override
    public int getItemViewType(int position) {
        return mTypeList.get(position);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = null;
        RecyclerView.ViewHolder viewHolder = null;
        ItemViewHolder itemViewHolder = new ItemViewHolder();
        switch (viewType) {
            case TYPE_HEAD:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.head_cover, viewGroup, false);
                ItemViewHolder.HeadViewHolder headViewHolder = itemViewHolder.new HeadViewHolder(view);
                return headViewHolder;
            case TYPE_MIDDLE_INFO:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.middle_information, viewGroup, false);
                ItemViewHolder.MiddleInfoViewHolder infoViewHolder = itemViewHolder.new MiddleInfoViewHolder(view);
                return infoViewHolder;
            case TYPE_MIDDLE_COMMENTS:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.middle_comments_recyclerview, viewGroup, false);
                RecyclerViewHolder commentsViewHolder = new RecyclerViewHolder(view);
                return commentsViewHolder;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder.HeadViewHolder) {
            setHeadViewHolder((ItemViewHolder.HeadViewHolder) holder);
        } else if (holder instanceof ItemViewHolder.MiddleInfoViewHolder) {
            setMiddleInfoViewHolder((ItemViewHolder.MiddleInfoViewHolder) holder);
        } else if (holder instanceof RecyclerViewHolder) {
            setCommentsViewHolder((RecyclerViewHolder) holder);
        }
    }


    public void setHeadViewHolder(ItemViewHolder.HeadViewHolder holder) {

        holder.mMovieName.setText(mListData.getTitle());
        holder.mTags.setText(formatString(mListData));
        holder.mOriName.setText("原名：" + mListData.getTitle());
        holder.mPublishTime.setText(mContext.getString(R.string.pushlish_time) + mListData.getPubdates().get(1));//第一个为当地上映时间，第二为大陆上映时间
        holder.mDuration.setText("片长：" + mListData.getDurations().get(0));//片长类数组中只有一个数据
        holder.mScores.setText(String.valueOf(mListData.getRating().getAverage()));
        holder.mStar.setRating(mListData.getRating().getAverage().floatValue() / 2);
        holder.mCount.setText(String.valueOf(mListData.getRatings_count()) + "人");

        Glide.with(mContext)
                .load(mListData.getImages().getLarge())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.mMovieCover);

    }

    public String formatString(Detail c) {
        String tags = "";
        tags = c.getYear() + "/";
        tags += c.getCountries();
        for (int i = 0; i < c.getGenres().size(); i++) {
            tags += "/";
            tags += c.getGenres().get(i);
        }
        return tags;
    }


    public void setMiddleInfoViewHolder(ItemViewHolder.MiddleInfoViewHolder holder) {
        holder.mIntroduction.setText(mListData.getSummary());

        //导演
        Glide.with(mContext)
                .load(mListData.getDirectors().get(0).getAvatars().getLarge())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.mDirector);

        //演员1
        Glide.with(mContext)
                .load(mListData.getCasts().get(0).getAvatars().getLarge())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.mCast_1);

        //演员2
        Glide.with(mContext)
                .load(mListData.getCasts().get(1).getAvatars().getLarge())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.mCast_2);

        //演员3
        Glide.with(mContext)
                .load(mListData.getCasts().get(2).getAvatars().getLarge())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.mCast_3);

        //剧照
        Glide.with(mContext)
                .load(mListData.getPhotos().get(0).getThumb())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.mPic);
    }

    public void setCommentsViewHolder(RecyclerViewHolder holder) {
        setCommentRecyclerView(holder.mRecyclerview);
    }

    public void setCommentRecyclerView(RecyclerView commentRecyclerView) {
        CommentsAdapter commentsAdapter = new CommentsAdapter(mListData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        commentRecyclerView.setLayoutManager(layoutManager);
        commentRecyclerView.setAdapter(commentsAdapter);
    }

    @Override
    public int getItemCount() {
        return mTypeList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public RecyclerView mRecyclerview;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mRecyclerview = itemView.findViewById(R.id.comments_recyclerview);
        }
    }
}
