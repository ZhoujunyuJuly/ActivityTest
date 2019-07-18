package com.example.downloadpage;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * Created by zhoujunyu on 2019/7/9.
 */
public class DownloadListAdapter extends BaseQuickAdapter<DownloadData,BaseViewHolder>  {


    public DownloadListAdapter(int layoutResId, @Nullable List<DownloadData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DownloadData item) {

        helper.setText(R.id.tv_app_name, item.getBookName());
        helper.addOnClickListener(R.id.bt_download);
        helper.addOnLongClickListener(R.id.bt_download);
        helper.addOnClickListener(R.id.layout_recyclerview);

    }


}
