package com.example.wbdemo.FunctionModule.Main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
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
        helper.setText(R.id.tv_main_username,item.getUser().getName());
        helper.setText(R.id.tv_main_timeline,item.getCreated_at());
        helper.setText(R.id.tv_main_content,item.getText());

        //Glide.with(mContext).load(item.getUser().getAvatar_hd()).into((ImageView)helper.getView(R.id.iv_main_portrait));

    }
}
