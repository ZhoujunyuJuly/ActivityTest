package com.example.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<MyReclList> mRecList;

    static class  MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name;
        TextView content;

        public MyViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.iv_icon);
            name = view.findViewById(R.id.tv_name);
            content = view.findViewById(R.id.tv_content);
        }
    }

    public MyAdapter(List<MyReclList> myReclList){
        mRecList = myReclList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_listview,viewGroup,false);
        MyViewHolder Holder = new MyViewHolder(view);
        return Holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        MyReclList myList = mRecList.get(i);
        myViewHolder.imageView.setImageResource(myList.getImg());
        myViewHolder.name.setText(myList.getName());
        myViewHolder.content.setText(myList.getContent());
    }

    @Override
    public int getItemCount() {
        return mRecList.size();
    }

}
