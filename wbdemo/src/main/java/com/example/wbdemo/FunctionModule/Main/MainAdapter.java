package com.example.wbdemo.FunctionModule.Main;

import android.content.Context;
import android.support.annotation.LongDef;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wbdemo.Object.Main.StatusesBean;
import com.example.wbdemo.R;

import java.util.List;

/**
 * Created by zhoujunyu on 2019/5/23.
 */
public class MainAdapter extends BaseQuickAdapter<StatusesBean,BaseViewHolder> {

    private Context mContext;

    public MainAdapter(int layoutResId, @Nullable List<StatusesBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, StatusesBean item) {

        //微博昵称
        helper.setText(R.id.tv_main_username,item.getUser().getName());

        //发布时间
        String time = item.getCreated_at();
        time = time.substring(0,time.indexOf("+"));
        helper.setText(R.id.tv_main_timeline,time);

        helper.setText(R.id.tv_main_content,item.getText());


        Glide.with(mContext).load(item.getUser().getAvatar_hd()).into((ImageView)helper.getView(R.id.iv_main_portrait));


    }
}
