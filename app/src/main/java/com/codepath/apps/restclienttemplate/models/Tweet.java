package com.codepath.apps.restclienttemplate.models;

import android.util.Log;

import com.codepath.apps.restclienttemplate.TimeFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Parcel
public class Tweet {

    public String body;
    public String createdAt;
    public long id;
    public User user;
    public Entities entities;

    public int retweetCt;
    public int favoriteCt;

    // Needed by parceler
    public Tweet() {}

    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.id = jsonObject.getLong("id");
        tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
        if (jsonObject.has("extended_entities")) {
            tweet.entities = Entities.fromJson(jsonObject.getJSONObject("extended_entities"));
        }

        tweet.retweetCt = jsonObject.getInt("retweet_count");
        tweet.favoriteCt = jsonObject.getInt("favorite_count");

        return tweet;
    }

    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            tweets.add(fromJson(jsonArray.getJSONObject(i)));
        }
        return tweets;
    }

    public String getBody() {
        return body;
    }

    public String getCreatedAt() {

        return createdAt;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getFormattedTimeStamp() {
        String formattedString = TimeFormatter.getTimeDifference(createdAt);
        return formattedString;
    }

    public String getCreationDate() throws ParseException {
        DateFormat outputDate = new SimpleDateFormat("h:mm aa • dd MMM yyyy");
        DateFormat inputDate = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy", Locale.ENGLISH);

        Date date = inputDate.parse(createdAt);
        String outputString = outputDate.format(date);

        return outputString;
    }

    public int getRetweetCt() {
        return retweetCt;
    }

    public int getFavoriteCt() {
        return favoriteCt;
    }
}
