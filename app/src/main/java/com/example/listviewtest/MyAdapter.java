package com.example.listviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;



public class MyAdapter extends BaseAdapter {


    //private String[] data={"apple","pine","banana","watermelon","orange","mango","pear","cherry","aaa","bbb","ccc","dfs","asdfasf","errt","eeee",
           // "asdfasdf","werewwg","asdfdf","dfdgdgdg","fedfedf","efegeg","fgrg","eegreb","efev","efeevf","efwfweg","efw","tht","thr","egr","wfew"};
    private Context mContext;
    private List<ListV1> mList;

    public MyAdapter(Context mcontext,List<ListV1> mList) {
        super();
        this.mContext = mcontext;
        this.mList = mList;

    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if(convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.activity_main, null);
        }else {
            view = convertView;
        }
        TextView left = (TextView)view.findViewById(R.id.item_left);
        TextView right = (TextView)view.findViewById(R.id.item_right);

        left.setText(mList.get(position).getLeft());
        right.setText(mList.get(position).getRight());

        return view;
        }

}
