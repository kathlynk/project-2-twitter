package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.codepath.apps.restclienttemplate.models.Entities;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.User;

import org.parceler.Parcels;

public class DetailActivity extends AppCompatActivity {

    TextView tvName;
    TextView tvScreenName;
    TextView tvTimeStamp;
    TextView tvBody;
    ImageView ivProfileImage;
    ImageView ivTweetPhoto;
    ImageButton btDetailBack;


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

        btDetailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Tweet tweet = Parcels.unwrap(getIntent().getParcelableExtra("tweet"));
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

        if (tweet.entities.media != null) {
            ivTweetPhoto.setVisibility(View.VISIBLE);
            Glide.with(ivTweetPhoto)
                    .load(tweet.entities.mediaURL)
                    .fitCenter()
                    .transform(new RoundedCorners(30))
                    .into(ivTweetPhoto);
        } else {
            Glide.with(ivTweetPhoto).clear(ivTweetPhoto);
            ivTweetPhoto.setVisibility(View.GONE);
        }
    }
}