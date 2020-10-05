package com.codepath.apps.restclienttemplate.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Media {

    public static final String TAG = "MediaModel";
    private static String mediaType;
    public static String mediaURL;

    public Media() {};

    public static Media fromJson(JSONObject jsonObject) throws JSONException {
        Media media = new Media();

        return media;
    };

    public static List<Media> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Media> media = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {


            media.add(fromJson(jsonArray.getJSONObject(i)));

           JSONObject currentObject = jsonArray.getJSONObject(i);

            if (currentObject.has("type")) {
                mediaType = currentObject.getString("type");
                Log.i(TAG, "Media: " + currentObject.getString("type"));
            }

            if (currentObject.has("media_url_https")) {
                mediaURL = currentObject.getString("media_url_https");
                Log.i(TAG, "Photo URL " + currentObject.getString("media_url_https"));
            }



            Log.i(TAG, "Media: " + media.get(i).toString());
        }
        return media;
    }

    public static String getMediaType() {
        return mediaType;
    }

    public static String getMediaURL() {
        return mediaURL;
    }
}
