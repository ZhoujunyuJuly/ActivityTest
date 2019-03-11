package com.example.booklist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class BookDetail extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private BookAdapter mBookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_bookview);

        //findViews();
        //initView();

    }


    private void findViews() {
        mRecyclerView = findViewById(R.id.recycler_view);
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mBookAdapter = new BookAdapter(Data.makeFakeData());
        mRecyclerView.setAdapter(mBookAdapter);
    }
}
