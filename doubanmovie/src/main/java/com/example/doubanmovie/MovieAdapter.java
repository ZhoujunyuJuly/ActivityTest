package com.example.doubanmovie;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.doubanmovie.model.SubjectsBean;

import java.util.List;

/**
 * Created by zhoujunyu on 2019/3/19.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<SubjectsBean> mMovieInfo;
    private Context mContent;

    public MovieAdapter(List movie, Context context) {
        mMovieInfo = movie;
        mContent = context;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder viewHolder, int i) {
        SubjectsBean movieList = mMovieInfo.get(i);

        viewHolder.mMovieName.setText(movieList.getTitle());
        viewHolder.mMovieScore.setText(String.valueOf(movieList.getRating().getAverage()));
        viewHolder.mParticipator.setText(String.valueOf(movieList.getCollect_count() / 10000));

        //电影封面
        Glide.with(mContent)
                .load(movieList.getImages().getSmall())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(viewHolder.mMovieCover);


        //主演
        String casts = "";
        for (int a = 0; a < movieList.getCasts().size(); a++) {
            if (a != 0) {
                casts += '/';
            }
            casts += movieList.getCasts().get(a).getName();
        }
        viewHolder.mCast.setText(casts);

        //导演
        String directors = "";
        for (int a = 0; a < movieList.getDirectors().size(); a++) {
            if (a != 0) {
                directors += '/';
            }
            directors += movieList.getDirectors().get(a).getName();
        }
        viewHolder.mDirector.setText(directors);

        //评分星星
            viewHolder.mStar.setRating(movieList.getRating().getAverage().floatValue() / 2);

    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
        MovieViewHolder movieViewHolder = new MovieViewHolder(view);
        return movieViewHolder;
    }

    @Override
    public int getItemCount() {
        return mMovieInfo.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView mMovieCover;
        TextView mMovieName;
        TextView mMovieScore;
        TextView mDirector;
        TextView mCast;
        TextView mParticipator;
        RatingBar mStar;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            mMovieCover = itemView.findViewById(R.id.iv_movie);
            mMovieName = itemView.findViewById(R.id.tv_title);
            mMovieScore = itemView.findViewById(R.id.tv_average);
            mDirector = itemView.findViewById(R.id.tv_director_name);
            mCast = itemView.findViewById(R.id.cast_name);
            mParticipator = itemView.findViewById(R.id.tv_collect_count);
            mStar = itemView.findViewById(R.id.iv_star);
        }
    }
}
