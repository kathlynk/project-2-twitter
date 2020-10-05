package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.User;

import org.parceler.Parcels;

public class DetailActivity extends AppCompatActivity {

    TextView tvName;
    TextView tvScreenName;
    TextView tvTimeStamp;
    TextView tvBody;
    ImageView ivProfileImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvName = findViewById(R.id.tvName);
        tvScreenName = findViewById(R.id.tvScreenName);
        tvTimeStamp = findViewById(R.id.tvBody);
        tvBody = findViewById(R.id.tvBody);
        ivProfileImage = findViewById(R.id.ivProfileImage);

        Tweet tweet = Parcels.unwrap(getIntent().getParcelableExtra("tweet"));
        User user = Parcels.unwrap(getIntent().getParcelableExtra("user"));

        tvName.setText(user.getName());
        tvScreenName.setText(user.getScreenName());
        tvTimeStamp.setText(tweet.getFormattedTimeStamp());
        tvBody.setText(tweet.getBody());
        Glide.with(ivProfileImage)
                .load(tweet.user.getProfileImageUrl())
                .circleCrop()
                .into(ivProfileImage);

    }
}