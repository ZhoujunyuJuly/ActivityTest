package com.example.doubanmovie.main;

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
import com.example.doubanmovie.R;
import com.example.doubanmovie.model.SubjectsBean;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by zhoujunyu on 2019/3/19.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    public IRecyclerviewClick onItemClickListen;
    private List<SubjectsBean> mSubjectList;
    private Context mContent;

    public MovieAdapter(List movie, Context context) {
        mSubjectList = movie;
        mContent = context;
    }

    public void setOnItemClickListen(IRecyclerviewClick onItemClickListen) {
        this.onItemClickListen = onItemClickListen;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder viewHolder, int i) {
        SubjectsBean movieList = mSubjectList.get(i);

        viewHolder.name.setText(movieList.getTitle());
        viewHolder.score.setText(String.valueOf(movieList.getRating().getAverage()));

        //观影人数
        DecimalFormat convert = new DecimalFormat("0.00");
        String count = convert.format(Double.valueOf(movieList.getCollect_count()) / 10000);
        viewHolder.participator.setText(count);

        //电影封面
        Glide.with(mContent)
                .load(movieList.getImages().getSmall())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(viewHolder.cover);


        //主演
        String casts = "";
        for (int a = 0; a < movieList.getCasts().size(); a++) {
            if (a != 0) {
                casts += '/';
            }
            casts += movieList.getCasts().get(a).getName();
        }
        viewHolder.cast.setText(casts);

        //导演
        String directors = "";
        for (int a = 0; a < movieList.getDirectors().size(); a++) {
            if (a != 0) {
                directors += '/';
            }
            directors += movieList.getDirectors().get(a).getName();
        }
        viewHolder.director.setText(directors);

        //评分星星
        viewHolder.star.setRating(movieList.getRating().getAverage().floatValue() / 2);


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
        return mSubjectList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView cover;
        TextView name;
        TextView score;
        TextView director;
        TextView cast;
        TextView participator;
        RatingBar star;


        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.iv_movie);
            name = itemView.findViewById(R.id.tv_title);
            score = itemView.findViewById(R.id.tv_average);
            director = itemView.findViewById(R.id.tv_director_name);
            cast = itemView.findViewById(R.id.cast_name);
            participator = itemView.findViewById(R.id.tv_collect_count);
            star = itemView.findViewById(R.id.iv_star);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListen.onMovieClick(v, mSubjectList.get(getAdapterPosition()));
                }
            });

            cover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListen.onCoverClick(v, mSubjectList.get(getAdapterPosition()));
                }
            });


        }
    }
}
