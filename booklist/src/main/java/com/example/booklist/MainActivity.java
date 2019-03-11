package com.example.booklist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private BookAdapter mBookAdapter;
    private CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        initView();
        click();

    }

    private void findViews() {
        mRecyclerView = findViewById(R.id.recycler_view);
        cardView = findViewById(R.id.cardView);
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mBookAdapter = new BookAdapter(Data.makeFakeData());
        mRecyclerView.setAdapter(mBookAdapter);
        //mRecyclerView.addItemDecoration(new BookDecoration());
    }

    private void click() {
        mBookAdapter.setOnItemClickListener(new OnItemClickListen() {

            @Override

            //TODO: 2019/3/7 点击事件，弹出提醒框
//            public void onItemClick(View view,int position){
//                Toast.makeText(MainActivity.this,"click",Toast.LENGTH_SHORT).show();
//            }

            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, BookDetail.class);
                intent.putExtra("index",position);
                startActivity(intent);
            }
        });
    }
}

