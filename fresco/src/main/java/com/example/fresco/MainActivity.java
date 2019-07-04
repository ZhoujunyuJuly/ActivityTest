package com.example.fresco;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.drawee.view.SimpleDraweeView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //网址：http://pic13.nipic.com/20110409/7119492_114440620000_2.jpg/
        //     https://avatar.csdn.net/4/E/8/1_y1scp.jpg（可）
        Uri uri = Uri.parse("https://avatar.csdn.net/4/E/8/1_y1scp.jpg");
        SimpleDraweeView draweeView = findViewById(R.id.draweeView);
        draweeView.setImageURI(uri);

    }
}
