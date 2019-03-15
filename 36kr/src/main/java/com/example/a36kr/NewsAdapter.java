package com.example.a36kr;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a36kr.model.News;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private Context mContext;
    private OnItemClickListen mOnItemClickListen;

    private List<News.DataBean.ItemsBean> mItemsList;

    public void setmOnItemClickListen(OnItemClickListen onItemClickListen) {
        this.mOnItemClickListen = onItemClickListen;
    }

    public NewsAdapter(List itemsList, Context context) {
        mItemsList = itemsList;
        mContext = context;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView cover;
        TextView title;
        TextView name;
        TextView time;

        public NewsViewHolder(@NonNull View itemView,OnItemClickListen listen) {
            super(itemView);

            mOnItemClickListen= listen;
            cover = itemView.findViewById(R.id.iv_cover);
            title = itemView.findViewById(R.id.tv_title);
            name = itemView.findViewById(R.id.tv_name);
            time = itemView.findViewById(R.id.tv_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListen.OnNewsClick(v,getAdapterPosition());
                }
            });

        }
    }

    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_news,viewGroup,false);
        NewsViewHolder holder = new NewsViewHolder(view,mOnItemClickListen);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int i) {

        if (mItemsList.size() > 0) {
            newsViewHolder.title.setText(mItemsList.get(i).getTitle());
            newsViewHolder.name.setText(mItemsList.get(i).getUser().getName());
            newsViewHolder.time.setText(mItemsList.get(i).getPublished_at());
            Glide.with(mContext)
                    .load(mItemsList.get(i).getUser().getAvatar_url())
                    .placeholder(R.mipmap.ic_launcher)
                    .centerCrop()
                    .into(newsViewHolder.cover);
        }

    }

    @Override
    public int getItemCount() {
        return  mItemsList.size();
    }
}
