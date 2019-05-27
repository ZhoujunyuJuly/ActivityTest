package com.example.wbdemo.FunctionModule.Main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wbdemo.Object.MainFgData.StatusesBean;
import com.example.wbdemo.R;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

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
    protected void convert(BaseViewHolder helper, final StatusesBean item) {

        //微博头像
        Glide.with(mContext).load(item.getUser().getAvatar_hd()).into((ImageView)helper.getView(R.id.iv_main_portrait));

        //微博昵称
        helper.setText(R.id.tv_main_username,item.getUser().getName());

        //发布时间
        String time = item.getCreated_at();
        time = time.substring(0,time.indexOf("+"));
        helper.setText(R.id.tv_main_timeline,time);

        //微博内容
        if(item.getText().contains("http://")){
            //final String videoURL = item.getText().substring(item.getText().indexOf("http://"),item.getText().length()-1);
            SpannableString content = new SpannableString(item.getText());
            content.setSpan(new LINKURLSpan(""),item.getText().indexOf("http://"),
                    item.getText().length()-1,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            helper.setText(R.id.tv_main_content,content);
            TextView ActiveText;
            ActiveText = helper.getView(R.id.tv_main_content);
            ActiveText.setMovementMethod(new LinkMovementMethod(){
                @Override
                public boolean onTouchEvent(TextView widget, Spannable buffer, MotionEvent event) {
                    //WatchVideoActivity.start(mContext,videoURL);
                    //return super.onTouchEvent(widget, buffer, event);
                    return true;
                }
            });

        }else {
            helper.setText(R.id.tv_main_content, item.getText());
        }

        //九宫格
        ArrayList<ImageInfo> imageInfo = new ArrayList<>();
        List<StatusesBean.PicUrlsBean> imageDetails = item.getPic_urls();
        if (imageDetails != null) {
            for (StatusesBean.PicUrlsBean imageDetail : imageDetails) {
                ImageInfo info = new ImageInfo();
                info.setThumbnailUrl(imageDetail.getThumbnail_pic());
                info.setBigImageUrl(imageDetail.getThumbnail_pic().replace("thumbnail","large"));
                imageInfo.add(info);
            }
        }


        NineGridView nineGridView = helper.getView(R.id.nine_grid_view);
        nineGridView.setAdapter(new NineGridViewClickAdapter(mContext,imageInfo));

    }



    public class LINKURLSpan extends URLSpan {
        public LINKURLSpan(String url) {
            super(url);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false);//无下划线
            ds.setColor(Color.parseColor("#1E90FF"));//蓝色
        }
    }

}
