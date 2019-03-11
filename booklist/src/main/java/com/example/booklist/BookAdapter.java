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

    private List<Book> mBookList;


    public BookAdapter(List<Book> bookList) {
        mBookList = bookList;
    }


    private OnItemClickListen mClickListen;

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_bookview, viewGroup, false);
        BookViewHolder bookViewHolder = new BookViewHolder(view, mClickListen);
        return bookViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final BookViewHolder viewHolder, int i) {
        Book booklist = mBookList.get(i);

        viewHolder.img.setImageResource(booklist.getImg());
        viewHolder.bookname.setText(booklist.getBookName());
        viewHolder.chapter.setText(booklist.getChapter());
        viewHolder.customer.setText(booklist.getCustomer());
        viewHolder.description.setText(booklist.getDescription());
        viewHolder.bookvalue.setText(booklist.getBookvalue());

    }

    @Override
    public int getItemCount() {
        return mBookList.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView bookname;
        TextView description;
        TextView chapter;
        TextView customer;
        TextView bookvalue;

        public BookViewHolder(View itemview, OnItemClickListen listener) {
            super(itemview);//?????????????????????????????
            mClickListen = listener;
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListen.onItemClick(v, getAdapterPosition());
                }
            });

            img = itemview.findViewById(R.id.iv_icon);
            bookname = itemview.findViewById(R.id.tv_bookname);
            description = itemview.findViewById(R.id.tv_description);
            chapter = itemview.findViewById(R.id.tv_chapter);
            customer = itemview.findViewById(R.id.tv_customer);
            bookvalue = itemview.findViewById(R.id.tv_bookvalue);
        }
    }


    public void setOnItemClickListener(OnItemClickListen listener) {
        this.mClickListen = listener;
    }
}
