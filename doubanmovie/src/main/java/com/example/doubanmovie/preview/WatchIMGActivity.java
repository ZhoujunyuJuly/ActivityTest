package com.example.doubanmovie.preview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.doubanmovie.R;

public class WatchIMGActivity extends Activity {
    public static final String IMGID_KEY = "IMG_URL";


    public static void start(Context context,String IMGID){
        Intent intent = new Intent(context,WatchIMGActivity.class);
        intent.putExtra(IMGID_KEY,IMGID);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_watch_img);

        ImageView mImg;
        mImg = findViewById(R.id.iv_largeImg);

        Intent intent = getIntent();
        String IMG_URL = intent.getStringExtra("IMG_URL");


        Glide.with(this)
                .load(IMG_URL)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(mImg);

    }
}
