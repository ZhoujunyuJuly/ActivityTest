package com.example.wbdemo.info;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zhoujunyu on 2019/6/14.
 */
public class MyItemDecoration extends RecyclerView.ItemDecoration {
    private Context mContext;
    private float mInterval;

    public MyItemDecoration(Context context,float interval) {
        mContext = context;
        mInterval = interval;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = ScaleUtils.dip2px(mContext,mInterval);//item间间隔
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = ScaleUtils.dip2px(mContext,mInterval);
        }
    }
}
