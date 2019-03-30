package com.example.doubanmovie.preview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.doubanmovie.R;

public class WatchIMGActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
