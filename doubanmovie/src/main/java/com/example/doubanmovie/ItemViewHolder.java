package com.example.doubanmovie;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by zhoujunyu on 2019/3/21.
 */
public class ItemViewHolder {

    public class HeadViewHolder extends RecyclerView.ViewHolder{
        ImageView mMovieCover;
        TextView mMovieName;
        TextView mTags;
        TextView mOriName;
        TextView mPublishTime;
        TextView mDuration;
        TextView mScores;
        TextView mCount;
        RatingBar mStar;

        public HeadViewHolder(View view){
            super(view);
            mMovieCover = view.findViewById(R.id.iv_cover);
            mMovieName = view.findViewById(R.id.tv_moviename);
            mTags = view.findViewById(R.id.tv_tags);
            mOriName = view.findViewById(R.id.tv_ori_name);
            mPublishTime = view.findViewById(R.id.tv_publishTime);
            mDuration = view.findViewById(R.id.tv_duration);
            mScores = view.findViewById(R.id.tv_scores);
            mCount = view.findViewById(R.id.tv_count);
            mStar = view.findViewById(R.id.rb_star);

        }
    }

    public class MiddleInfoViewHolder extends RecyclerView.ViewHolder{
        TextView mIntroduction;

        TextView mDirector_name;
        TextView mCast_1_name;
        TextView mCast_2_name;
        TextView mCast_3_name;

        TextView mCast_1_role;
        TextView mCast_2_role;
        TextView mCast_3_role;

        ImageView mDirector;
        ImageView mCast_1;
        ImageView mCast_2;
        ImageView mCast_3;
        ImageView mPic;

        public MiddleInfoViewHolder(View view){
            super(view);
            mIntroduction = view.findViewById(R.id.tv_introduction);
            mDirector_name = view.findViewById(R.id.tv_director_name_detail);
            mCast_1_name = view.findViewById(R.id.tv_cast_1_name);
            mCast_2_name = view.findViewById(R.id.tv_cast_2_name);
            mCast_3_name = view.findViewById(R.id.tv_cast_3_name);
            mCast_1_role = view.findViewById(R.id.tv_cast_1_role);
            mCast_2_role = view.findViewById(R.id.tv_cast_2_role);
            mCast_3_role = view.findViewById(R.id.tv_cast_3_role);

            mDirector = view.findViewById(R.id.iv_director);
            mCast_1 = view.findViewById(R.id.iv_cast_1);
            mCast_2 = view.findViewById(R.id.iv_cast_2);
            mCast_3 = view.findViewById(R.id.iv_cast_3);
            mPic = view.findViewById(R.id.iv_pic);
        }
    }

    public class MiddleCommentsViewHolder extends RecyclerView.ViewHolder{
        ImageView mPortrait;
        TextView mUserName;
        TextView mUserComments;
        TextView mUserTime;
        RatingBar mStar;


        public MiddleCommentsViewHolder(View view){
            super(view);
            mPortrait = view.findViewById(R.id.portrait);
            mUserName = view.findViewById(R.id.tv_user_name);
            mUserComments = view.findViewById(R.id.tv_user_comments);
            mUserTime = view.findViewById(R.id.tv_user_time);
            mStar = view.findViewById(R.id.rb_comment_star);

        }
    }
}
