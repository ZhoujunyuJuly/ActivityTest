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

    private String[] data_1 = new String[]{"apple","pine","banana","watermelon","orange","mango","pear","cherry","aaa","bbb","ccc","dfs","asdfasf","errt","eeee",
            "asdfasdf","werewwg","asdfdf","dfdgdgdg","fedfedf","efegeg","fgrg","eegreb","efev","efeevf","efwfweg","efw","tht","thr","egr","wfew"};


    private ListView mListView;
    private MyAdapter myAdapter;
    private List<ListV1> mList = new ArrayList<ListV1>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView)findViewById(R.id.list_view);
        addData();
        myAdapter = new MyAdapter(MainActivity.this,mList);
        mListView.setAdapter(myAdapter);
    }


    private void addData(){
        //List<ListV1> userList = new ArrayList<>();
       // ListV1 listv1 = null;
       // for(int i = 0;i< data_1.length;i++) {
           // listv1 = new ListV1("left" + data_1[i]);
         //   mList.add(listv1);
        //}

        ListV1 v1 = new ListV1("aaa","bbb");
        ListV1 v2 = new ListV1("bbb","aaa");
        ListV1 v3 = new ListV1("ccc","ddd");
        ListV1 v4 = new ListV1("ddd","fff");
        ListV1 v5 = new ListV1("eee","ddf");
        ListV1 v6 = new ListV1("eee","ddf");
        ListV1 v7 = new ListV1("eee","ddf");
        ListV1 v8 = new ListV1("eee","ddf");
        ListV1 v9 = new ListV1("eee","ddf");
        ListV1 v10 = new ListV1("eee","ddf");
        ListV1 v11 = new ListV1("eee","ddf");
        ListV1 v12 = new ListV1("eee","ddf");
        ListV1 v13 = new ListV1("eee","ddf");
        ListV1 v14 = new ListV1("eee","ddf");
        ListV1 v15 = new ListV1("eee","ddf");
        ListV1 v16 = new ListV1("eee","ddf");
        ListV1 v17 = new ListV1("eee","ddf");


        mList.add(v1);
        mList.add(v2);
        mList.add(v3);
        mList.add(v4);
        mList.add(v5);
        mList.add(v6);
        mList.add(v7);
        mList.add(v8);
        mList.add(v9);
        mList.add(v10);
        mList.add(v11);
        mList.add(v12);
        mList.add(v13);
        mList.add(v14);
        mList.add(v15);
        mList.add(v16);
        mList.add(v17);

        v1 = null;
        v2 = null;
        v3 = null;
        v4 = null;
        v5 = null;
        v6 = null;
        v7 = null;
        v8 = null;
        v9 = null;
        v10 = null;
        v11 = null;
        v12 = null;
        v13 = null;
        v14 = null;
        v15 = null;
        v16 = null;
        v17 = null;



    }
}

