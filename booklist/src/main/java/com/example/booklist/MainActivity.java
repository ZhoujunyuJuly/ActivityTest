package com.example.booklist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

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
        test();

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
    }

    private void click() {
        mBookAdapter.setOnItemClickListener(new OnItemClickListen() {

            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, BookDetailActivity.class);
                intent.putExtra("index", position);
                startActivity(intent);
            }
        });
    }

    private void test(){
        Map<String,String> map = new HashMap();
        map.put("a","北京");
        map.put("b","1");
        map.put("c","2");
        map.put("status","3");
        map.put("page","4");

        int i = 0;

        StringBuffer url = new StringBuffer("www.baidu.com?");
        for (String key : map.keySet()){
            if( i ==0) {
                url.append(key + "=");
                i ++;
            }else {
                url.append("&" + key + "=");
            }
            String value;
            value = map.get(key);
            try {
                value = URLEncoder.encode(value, "UTF-8");
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
            url.append( value );
        }

        Log.d("zjy", "url value is " + url);

        String[] array = map.keySet().toArray(new String[0]);
        Log.d("zjy", "array is " + array);

    }
}

