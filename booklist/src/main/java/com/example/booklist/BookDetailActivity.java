package com.example.booklist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class BookDetailActivity extends AppCompatActivity {

    ImageView img;
    TextView bookname;
    TextView description;
    TextView chapter;
    TextView customer;
    TextView bookvalue;

    Book mbook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_bookview);

        findViews();
        getData();
        refresh(mbook);

    }


    private void getData() {
        Intent intent = getIntent();
        int i = intent.getIntExtra("index", 0);
        Log.d("zhoujunyu", String.valueOf(i));
        mbook = Data.get(i);
    }

    private void findViews() {
        img = findViewById(R.id.iv_icon);
        bookname = findViewById(R.id.tv_bookname);
        description = findViewById(R.id.tv_description);
        chapter = findViewById(R.id.tv_chapter);
        customer = findViewById(R.id.tv_customer);
        bookvalue = findViewById(R.id.tv_bookvalue);
    }

    private void refresh(Book book) {
        img.setImageResource(book.getImg());
        bookname.setText(book.getBookName());
        description.setText(book.getDescription());
        chapter.setText(book.getChapter());
        customer.setText(book.getCustomer());
        bookvalue.setText(book.getBookvalue());

    }


}




