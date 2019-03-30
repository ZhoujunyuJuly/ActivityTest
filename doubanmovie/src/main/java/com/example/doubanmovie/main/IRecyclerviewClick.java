package com.example.doubanmovie.main;

import android.view.View;

import com.example.doubanmovie.model.SubjectsBean;

/**
 * Created by zhoujunyu on 2019/3/22.
 */
public interface IRecyclerviewClick {
    void onMovieClick(View view, SubjectsBean subjectsBean);

    void onCoverClick(View view, SubjectsBean subjectsBean);
}
