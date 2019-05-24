package com.example.wbdemo.FunctionModule.Main;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wbdemo.Object.MainFgData.StatusesBean;
import com.example.wbdemo.R;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
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

        //微博头像
        Glide.with(mContext).load(item.getUser().getAvatar_hd()).into((ImageView)helper.getView(R.id.iv_main_portrait));

        //微博昵称
        helper.setText(R.id.tv_main_username,item.getUser().getName());

        //发布时间
        String time = item.getCreated_at();
        time = time.substring(0,time.indexOf("+"));
        helper.setText(R.id.tv_main_timeline,time);

        //微博内容
        helper.setText(R.id.tv_main_content,item.getText());

        //九宫格
        ArrayList<ImageInfo> imageInfo = new ArrayList<>();
        List<StatusesBean.PicUrlsBean> imageDetails = item.getPic_urls();
        if (imageDetails != null) {
            for (StatusesBean.PicUrlsBean imageDetail : imageDetails) {
                ImageInfo info = new ImageInfo();
                info.setThumbnailUrl(imageDetail.getThumbnail_pic());
                if(imageDetails.size() == 1) {
                    info.setBigImageUrl(item.getOriginal_pic());
                }else {
                    info.setBigImageUrl(imageDetail.getThumbnail_pic());
                }
                imageInfo.add(info);
            }
        }


        NineGridView nineGridView = helper.getView(R.id.nine_grid_view);
        nineGridView.setAdapter(new NineGridViewClickAdapter(mContext,imageInfo));

    }


    private class PicassoImageLoader implements NineGridView.ImageLoader {

        @Override
        public void onDisplayImage(Context context, ImageView imageView, String url) {
            Picasso.with(context).load(url)//
                    .placeholder(R.drawable.ic_default_image)//
                    .error(R.drawable.ic_default_image)//
                    .into(imageView);
        }

        @Override
        public Bitmap getCacheImage(String url) {
            return null;
        }
    }
}
