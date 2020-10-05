package com.codepath.apps.restclienttemplate.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Entities {

    public static final String TAG = "EntitiesModel";

    public List<Media> media;
    public String mediaURL;
    public String mediaType;

    public Entities() {};

    public static Entities fromJson(JSONObject jsonObject) throws JSONException {
        Entities entities = new Entities();
        if(jsonObject.has("media")) {
            entities.media = Media.fromJsonArray(jsonObject.getJSONArray("media"));
            Log.i(TAG, entities.media.toString());
            entities.mediaURL = Media.getMediaURL();
            entities.mediaType = Media.getMediaType();
        }

        return entities;
    }

}
