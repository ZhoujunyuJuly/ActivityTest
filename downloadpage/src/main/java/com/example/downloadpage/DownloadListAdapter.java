package com.example.downloadpage;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * Created by zhoujunyu on 2019/7/9.
 */
public class DownloadListAdapter extends BaseQuickAdapter<String,BaseViewHolder>  {


    public DownloadListAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        helper.setText(R.id.tv_app_name, item);
        helper.addOnClickListener(R.id.bt_download);
        helper.addOnLongClickListener(R.id.bt_download);

    }


}
