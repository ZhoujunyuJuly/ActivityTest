package com.example.listviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    //List userList = new ArrayList<>();

    private List<MyList> mList = new ArrayList<MyList>();

    private ListView mListView;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView)findViewById(R.id.list_view);

        fakeData();

        myAdapter = new MyAdapter(MainActivity.this,mList);
        mListView.setAdapter(myAdapter);
    }


    private void fakeData(){

        for (int i = 0; i < 100 ; i++) {
             MyList myList = new MyList(String.valueOf(i),"hello",R.drawable.ic_launcher_background);
             mList.add(myList);
        }

    }
}

