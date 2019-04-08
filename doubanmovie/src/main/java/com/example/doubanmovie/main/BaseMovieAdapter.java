package com.example.doubanmovie.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.doubanmovie.R;
import com.example.doubanmovie.model.SubjectsBean;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by zhoujunyu on 2019/4/8.
 */
public class BaseMovieAdapter extends BaseQuickAdapter<SubjectsBean,BaseViewHolder> {

    private Context mContext;

    public BaseMovieAdapter(int layoutResId, @Nullable List<SubjectsBean> data,Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, SubjectsBean item) {


        //观影人数两位小数点
        DecimalFormat convert = new DecimalFormat("0.00");
        String count = convert.format(Double.valueOf(item.getCollect_count()) / 10000);

        //主演
        String casts = "";
        for (int a = 0; a < item.getCasts().size(); a++) {
            if (a != 0) {
                casts += '/';
            }
            casts += item.getCasts().get(a).getName();
        }
        helper.setText(R.id.cast_name,casts);

        //导演
        String directors = "";
        for (int a = 0; a < item.getDirectors().size(); a++) {
            if (a != 0) {
                directors += '/';
            }
            directors += item.getDirectors().get(a).getName();
        }
        helper.setText(R.id.tv_director_name,directors);


        //电影封面
        Glide.with(mContext)
                .load(item.getImages().getSmall())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into((ImageView)helper.getView(R.id.iv_movie));


        //电影名、评分、观影人数、评分星星
          helper.setText(R.id.tv_title,item.getTitle())
                .setText(R.id.tv_average,String.valueOf(item.getRating().getAverage()))
                .setText(R.id.tv_collect_count,count)
                .setRating(R.id.iv_star,item.getRating().getAverage().floatValue()/2)
                .addOnClickListener(R.id.iv_movie);

    }



}
