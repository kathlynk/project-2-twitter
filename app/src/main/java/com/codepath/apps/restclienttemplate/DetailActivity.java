package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.codepath.apps.restclienttemplate.models.Entities;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.User;
import com.klinker.android.simple_videoview.SimpleVideoView;

import org.parceler.Parcels;

import java.io.IOException;

public class DetailActivity extends AppCompatActivity {


    public static final String TAG = "DetailACTIVITY";

    TextView tvName;
    TextView tvScreenName;
    TextView tvTimeStamp;
    TextView tvBody;
    ImageView ivProfileImage;
    ImageView ivTweetPhoto;
    ImageButton btDetailBack;
    SimpleVideoView vvTweetVideo;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar_detail = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar_detail);


        tvName = findViewById(R.id.tvName);
        tvScreenName = findViewById(R.id.tvScreenName);
        tvTimeStamp = findViewById(R.id.tvTimeStamp);
        tvBody = findViewById(R.id.tvBody);
        ivProfileImage = findViewById(R.id.ivProfileImage);
        btDetailBack = findViewById(R.id.btDetailBack);
        ivTweetPhoto = findViewById(R.id.ivTweetPhoto);
        vvTweetVideo = (SimpleVideoView) findViewById(R.id.vvTweetVideo);


        btDetailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        final Tweet tweet = Parcels.unwrap(getIntent().getParcelableExtra("tweet"));
        User user = Parcels.unwrap(getIntent().getParcelableExtra("user"));
        Entities entities = Parcels.unwrap(getIntent().getParcelableExtra("entities"));

        tvName.setText(user.getName());
        tvScreenName.setText(user.getScreenName());
        tvTimeStamp.setText(tweet.getFormattedTimeStamp());
        tvBody.setText(tweet.getBody());
        Glide.with(ivProfileImage)
                .load(tweet.user.getProfileImageUrl())
                .circleCrop()
                .into(ivProfileImage);

        if (tweet.entities != null) {

            // If tweet has a video
            if (tweet.entities.mediaType.equals("video")) {
                vvTweetVideo.setVisibility(View.VISIBLE);
                ivTweetPhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i(TAG, tweet.entities.videoURL);

                        Glide.with(ivTweetPhoto).clear(ivTweetPhoto);
                        ivTweetPhoto.setVisibility(View.GONE);

                        vvTweetVideo.start(tweet.entities.videoURL);

                        vvTweetVideo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (vvTweetVideo.isPlaying())
                                    vvTweetVideo.pause();
                                else
                                    vvTweetVideo.play();
                            }
                        });


                    }
                });
            }
            ivTweetPhoto.setVisibility(View.VISIBLE);
            Glide.with(ivTweetPhoto)
                    .load(tweet.entities.mediaURL)
                    .fitCenter()
                    .transform(new RoundedCorners(30))
                    .into(ivTweetPhoto);
        } else {
            Glide.with(ivTweetPhoto).clear(ivTweetPhoto);
            ivTweetPhoto.setVisibility(View.GONE);
            vvTweetVideo.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        vvTweetVideo.release();
    }


}

