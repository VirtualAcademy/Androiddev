package com.ndicson.ndic.medialand;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ProgressDialog mprogress;
    VideoView videoView;
    ImageButton btnplaypause;

    private final String videourl = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";//"https://ak2.picdn.net/shutterstock/videos/21549772/preview/stock-footage-gingerbread-man-dancers-d-animation-of-funny-hot-and-sweet-cookie-boy-dancing-for-holiday-and.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = (VideoView)findViewById(R.id.vid_playerview);
        btnplaypause = (ImageButton)findViewById(R.id.btn_play_pause);
        btnplaypause.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        mprogress = new ProgressDialog(MainActivity.this);
        mprogress.setMessage("Loading...");
        mprogress.setCanceledOnTouchOutside(false);
        mprogress.show();

        try
        {
            if (!videoView.isPlaying()){
                Uri videouri = Uri.parse(videourl);
                videoView.setVideoURI(videouri);
                videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        btnplaypause.setImageResource(R.mipmap.ic_play);
                    }
                });
            }
            else {
                videoView.pause();
                btnplaypause.setImageResource(R.mipmap.ic_play);
            }
        }
        catch (Exception e){

        }

        videoView.requestFocus();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mprogress.dismiss();
                mp.setLooping(true);
                videoView.start();
                btnplaypause.setImageResource(R.mipmap.ic_pause);

            }
        });
    }
}
