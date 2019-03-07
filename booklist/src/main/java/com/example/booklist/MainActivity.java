package com.example.booklist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private BookAdapter mBookAdapter;

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
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mBookAdapter = new BookAdapter(Data.makeFakeData());
        mRecyclerView.setAdapter(mBookAdapter);
    }

    private void click(){
        mBookAdapter.setOnItemClickListener(new BookAdapter.OnItemClickListen() {
            @Override

            //TODO: 2019/3/7 点击事件，弹出提醒框
//            public void onItemClick(View view,int position){
//                Toast.makeText(MainActivity.this,"click",Toast.LENGTH_SHORT).show();
//            }

            public void onItemClick(View view,int position){
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}

