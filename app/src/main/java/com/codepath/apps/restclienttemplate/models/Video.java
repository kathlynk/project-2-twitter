package com.codepath.apps.restclienttemplate.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class Video {
    public Video() {};

    public static final String TAG = "Video Model: ";
    public static String videoURL;
    public static Video video;

    public static Video fromJson(JSONObject jsonObject) throws JSONException {
        Video video = new Video();
        JSONArray videoList;
        videoList = jsonObject.getJSONArray("variants");
        video.videoURL = videoList.getJSONObject(0).getString("url");
        Log.i(TAG, "Video URL is "+ video.videoURL);
        return video;
    }

    public static String getVideoURL() {
        return videoURL;
    }

    public static Video getVideo() {
        return video;
    }
}
