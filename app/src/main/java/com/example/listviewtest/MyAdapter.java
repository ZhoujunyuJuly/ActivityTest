package com.example.listviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zhoujunyu on March 3
 *
 */

public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private List<MyList> mList;

    public MyAdapter(Context context,List list) {
        super();
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if(convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_listview, null);
        }else {
            view = convertView;
        }
        TextView name = view.findViewById(R.id.tv_name);
        TextView content = view.findViewById(R.id.tv_content);
        ImageView img = view.findViewById(R.id.tv_img);

        name.setText(mList.get(position).getmName());
        content.setText(mList.get(position).getmContent());
        img.setImageResource(mList.get(position).getmImg());

        return view;
    }

}
