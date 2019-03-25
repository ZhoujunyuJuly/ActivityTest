package com.example.doubanmovie;

import android.view.View;

/**
 * Created by zhoujunyu on 2019/3/22.
 */
public interface IRecyclerviewClick {
    void onMovieClick(View view, int position);

    void onCoverClick(View view, int position);
}
