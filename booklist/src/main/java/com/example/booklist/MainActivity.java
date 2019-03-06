package com.example.booklist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<MyRecList> myRecList = new ArrayList<MyRecList>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataSource();

        //RecyclerView recyclerView = findViewById(R.layout.item_bookview);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this);//????????????????????
        //recyclerView.setLayoutManager(layoutManager);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);//important!!!!!!
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        BookAdapter bookAdapter = new BookAdapter(myRecList);
        recyclerView.setAdapter(bookAdapter);
    }


    public void dataSource(){
        MyRecList first = new MyRecList(R.mipmap.ic_launcher,"MySQL是怎样运行的：从根儿上理解MYSQL",
                "小孩子4919","25小节","1257人购买","¥29.9");
        MyRecList second = new MyRecList(R.mipmap.ic_launcher,"基于Three JS框架的魔方微信小游戏实践",
                "NewbieYoung","10小节","467人购买","¥9.9");
        MyRecList third = new MyRecList(R.mipmap.ic_launcher,"前端面试之道",
                "yck","36小节","6245人购买","¥49.9");
        MyRecList forth = new MyRecList(R.mipmap.ic_launcher,"Kubernetes从上手到实践",
                "TaoBeier","24小节","1073人购买","¥19.9");
        MyRecList fifth = new MyRecList(R.mipmap.ic_launcher,"Vue.js组件精讲",
                "Aresn","20小节","2871人购买","¥29.9");
        MyRecList sixth = new MyRecList(R.mipmap.ic_launcher,"React实战：设计模式和最佳实践",
                "程墨","21小节","1523人购买","¥29.9");
        MyRecList seventh = new MyRecList(R.mipmap.ic_launcher,"Vue项目构建与开发入门",
                "劳卜","17小节","2953人购买","¥9.9");
        MyRecList eighth = new MyRecList(R.mipmap.ic_launcher,"前端性能优化原理与实践",
                "修言","15小节","5228人购买","¥19.9");
        MyRecList ninth = new MyRecList(R.mipmap.ic_launcher,"Redis深度历险：核心原理与应用实践",
                "老钱","45小节","18429人购买","¥19.9");
        MyRecList tenth = new MyRecList(R.mipmap.ic_launcher,"程序员小白书——如何规划和经营你的职业",
                "Easy","14小节","1554人购买","¥29.9");

        myRecList.add(first);
        myRecList.add(second);
        myRecList.add(third);
        myRecList.add(forth);
        myRecList.add(fifth);
        myRecList.add(sixth);
        myRecList.add(seventh);
        myRecList.add(eighth);
        myRecList.add(ninth);
        myRecList.add(tenth);



    }
}
