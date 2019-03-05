package com.example.listviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zhoujunyu on March 3
 *
 */

public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mList;

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
        TextView left = view.findViewById(R.id.tv_left);
        TextView right = view.findViewById(R.id.tv_right);

        left.setText(mList.get(position));
        right.setText(mList.get(position));

        return view;
    }

}
