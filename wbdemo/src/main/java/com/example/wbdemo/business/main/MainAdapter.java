package com.example.wbdemo.business.main;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wbdemo.R;
import com.example.wbdemo.info.maindata.StatusesBean;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhoujunyu on 2019/5/23.
 */
public class MainAdapter extends BaseQuickAdapter<StatusesBean, BaseViewHolder> {

    private Context mContext;

    public MainAdapter(int layoutResId, @Nullable List<StatusesBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final StatusesBean item) {

        //微博头像
        Glide.with(mContext).load(item.getUser().getAvatar_hd()).into((ImageView) helper.getView(R.id.iv_main_portrait));

        //微博昵称
        helper.setText(R.id.tv_main_username, item.getUser().getName());

        //发布时间
        String time = item.getCreated_at();
        time = time.substring(0, time.indexOf("+"));
        helper.setText(R.id.tv_main_timeline, time);

        //微博内容
        if (item.getText().contains("http://") || item.getText().contains("#")) {
            //SPannableString富文本样式
            String ContentStr = item.getText();
            SpannableString content;
            String Article_URL = "";

            //超过140字，替换链接为"展开"字样
            if (ContentStr.contains("全文： http://m.weibo.cn")) {
                Article_URL = ContentStr.substring(ContentStr.indexOf("全文： http://m.weibo.cn"),
                        ContentStr.length() - 1);
                ContentStr = ContentStr.replace(Article_URL, "展开");
                Article_URL = Article_URL.substring(Article_URL.indexOf("http://m.weibo.cn"), Article_URL.length() - 1);
            }
            content = new SpannableString(ContentStr);

            //超链接
            //1.视频链接
            if (ContentStr.contains("http://t.cn")) {
                String ALLURL = ContentStr.substring(ContentStr.indexOf("http://t.cn"), ContentStr.length() - 1);
                int lastPosition = ALLURL.indexOf(" ");
                final String videoURL;
                if (lastPosition != -1) {
                    videoURL = ALLURL.substring(0, lastPosition);
                } else {
                    videoURL = ALLURL.substring(0, ALLURL.length() - 1);
                }
                content.setSpan(new LINKURLSpan(videoURL), ContentStr.indexOf(videoURL),
                        ContentStr.indexOf(videoURL) + videoURL.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            }
            //2.话题
            if (ContentStr.contains("#")) {
                Matcher m = Pattern.compile("#").matcher(ContentStr);
                int number = 0;
                List<Integer> position = new ArrayList<>();
                while (m.find()) {
                    position.add(number, m.end() - 1);
                    number = number + 1;
                }

                if (number >= 2) {
                    for (int i = 0; i < number - 1; i = i + 2) {
                        content.setSpan(new LINKURLSpan(""), position.get(i), position.get(i + 1) + 1,
                                Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    }
                }
            }
            //3.文章链接
            if (ContentStr.contains("...展开")) {
                content.setSpan(new LINKURLSpan(Article_URL), ContentStr.indexOf("...展开") + 3,
                        ContentStr.length() - 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            }

            TextView ActiveTextEvent = helper.getView(R.id.tv_main_content);
            ActiveTextEvent.setMovementMethod(LinkMovementMethod.getInstance());
            ActiveTextEvent.setText(content);


        } else {
            helper.setText(R.id.tv_main_content, item.getText());
        }

        ArrayList<ImageInfo> imageInfo = new ArrayList<>();
        List<StatusesBean.PicUrlsBean> imageDetails = item.getPic_urls();
        if (imageDetails != null) {
            for (StatusesBean.PicUrlsBean imageDetail : imageDetails) {
                ImageInfo info = new ImageInfo();
                if (imageDetails.size() > 1) {
                    info.setThumbnailUrl(imageDetail.getThumbnail_pic());
                } else {
                    info.setThumbnailUrl(item.getOriginal_pic());
                }
                info.setBigImageUrl(imageDetail.getThumbnail_pic().replace("thumbnail", "large"));
                imageInfo.add(info);
            }
            NineGridView nineGridView = helper.getView(R.id.nine_grid_view);
            nineGridView.setAdapter(new NineGridViewClickAdapter(mContext, imageInfo));
        }

        //点赞、转发、分享、评论数
        helper.setText(R.id.tv_item_comments, String.valueOf(item.getComments_count()));
        helper.setText(R.id.tv_item_attitudes, String.valueOf(item.getAttitudes_count()));
        helper.setText(R.id.tv_item_reposts, String.valueOf(item.getReposts_count()));
        helper.setText(R.id.tv_item_share, String.valueOf(item.getShares_count()));

        helper.addOnClickListener(R.id.nine_grid_view);
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

        @Override
        public void onClick(View widget) {
            super.onClick(widget);
            if (widget instanceof TextView && getURL() != null && !getURL().isEmpty()) {
                WatchVideoActivity.start(mContext, getURL());
            }
        }
    }
}
