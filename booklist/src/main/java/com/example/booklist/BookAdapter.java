package com.example.booklist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<MyRecList> mRecList;


    static class BookViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView bookname;
        TextView description;
        TextView chapter;
        TextView customer;
        TextView bookvalue;

        public BookViewHolder(View view){
            super(view);//?????????????????????????????
            img = view.findViewById(R.id.iv_icon);
            bookname = view.findViewById(R.id.tv_bookname);
            description = view.findViewById(R.id.tv_description);
            chapter = view.findViewById(R.id.tv_chapter);
            customer = view.findViewById(R.id.tv_customer);
            bookvalue = view.findViewById(R.id.tv_bookvalue);
        }
    }

    public BookAdapter(List<MyRecList> myRecList){
        mRecList = myRecList;
    }


    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_bookview,viewGroup,false);
        BookViewHolder bookViewHolder = new BookViewHolder(view);
        return bookViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder viewHolder, int i) {
        MyRecList booklist = mRecList.get(i);
        viewHolder.img.setImageResource(booklist.getImg());
        viewHolder.bookname.setText(booklist.getBookName());
        viewHolder.chapter.setText(booklist.getChapter());
        viewHolder.customer.setText(booklist.getCustomer());
        viewHolder.description.setText(booklist.getDescription());
        viewHolder.bookvalue.setText(booklist.getBookvalue());
    }

    @Override
    public int getItemCount() {
        return mRecList.size();
    }
}
