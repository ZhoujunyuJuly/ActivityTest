package com.example.doubanmovie;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.doubanmovie.detail.DetailActivity;

import static com.example.doubanmovie.detail.DetailActivity.MOVIEID_KEY;

public class VideoActivity extends AppCompatActivity {

    public static final String VIDEO_KEY = "movieID";
    private VideoView videoView;
    private MediaController mediaController;

    public static void start(Context context, String videoId) {
        Intent intent = new Intent(context, VideoActivity.class);
        intent.putExtra(VIDEO_KEY, videoId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        findView();
        init();
        }

    private void findView(){
        videoView = findViewById(R.id.videoview);
    }

    private void init(){
        mediaController = new MediaController(this);
        String video_url = getIntent().getStringExtra(VIDEO_KEY);

        videoView.setVideoURI(Uri.parse(video_url));
        videoView.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoView);
        videoView.requestFocus();
        videoView.start();
    }

}
