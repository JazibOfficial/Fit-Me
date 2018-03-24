package com.jazibkhan.healthcare.model;

import android.widget.TextView;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zeesh on 3/24/2018.
 */

public class results {
    @SerializedName("icon")
    private String icon;
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
//    @SerializedName("photos")
//    private List<Photo> photos = new ArrayList<Photo>();
    @SerializedName("place_id")
    private String placeId;
    @SerializedName("rating")
    private Double rating;
//    @SerializedName("reference")
//    private String reference;
//    @SerializedName("scope")
//    private String scope;
//    @SerializedName("types")
//    private List<String> types = new ArrayList<String>();
    @SerializedName("vicinity")
    private String vicinity;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }
}
