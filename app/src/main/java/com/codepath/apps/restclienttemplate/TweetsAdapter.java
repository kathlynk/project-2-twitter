package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> {

    Context context;
    List<Tweet> tweets;

    public static final String TAG = "TweetsAdapter";

    // Pass in the context and list of tweets
    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    // For each row, inflate the layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);
    }

    // Bind values based on the position of the element
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data at the position
        Tweet tweet = tweets.get(position);
        // Bind the tweet with view holder
        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(List<Tweet> tweetList) {
        tweets.addAll(tweetList);
        notifyDataSetChanged();
    }

    // Define a viewholder
    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout container; // To move activities
        ImageView ivProfileImage;
        ImageView ivTweetPhoto;
        TextView tvBody;
        TextView tvScreenName;
        TextView tvName;
        TextView tvTimeStamp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            ivTweetPhoto = itemView.findViewById(R.id.ivTweetPhoto);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            tvTimeStamp = itemView.findViewById(R.id.tvTimeStamp);
            container = itemView.findViewById(R.id.container); // To move activities
            tvName = itemView.findViewById(R.id.tvName);
        }

        public void bind(final Tweet tweet) {
            tvBody.setText(tweet.body);
            tvScreenName.setText(tweet.user.screenName);
            tvName.setText(tweet.user.name);
            tvTimeStamp.setText(tweet.getFormattedTimeStamp());
            Glide.with(context)
                    .load(tweet.user.profileImageUrl)
                    .circleCrop()
                    .into(ivProfileImage);
            // Load tweet images
            if (tweet.entities != null) {
                // Tweet has video
                if (tweet.entities.mediaType.equals("video")) {
                   Log.i(TAG, "Received video URL " + tweet.entities.videoURL);
                }
                // Tweet has photo
                if (tweet.entities.mediaURL != null) {
                    ivTweetPhoto.setVisibility(View.VISIBLE);
                    Glide.with(context)
                            .load(tweet.entities.mediaURL)
                            .fitCenter()
                            .transform(new RoundedCorners(30))
                            .into(ivTweetPhoto);
                }
            } else {
                Glide.with(context).clear(ivTweetPhoto);
                ivTweetPhoto.setVisibility(View.GONE);
            }

            // Parceler to New Activity
            container.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("name", tweet.user.name);
                    i.putExtra("tweet", Parcels.wrap(tweet));
                    i.putExtra("user", Parcels.wrap(tweet.user));
                    i.putExtra("entities", Parcels.wrap(tweet.entities));
                    context.startActivity(i);
                }
            });
        }
    }
}
