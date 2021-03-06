package com.jazibkhan.healthcare.model;

/**
 * Created by zeesh on 3/24/2018.
 */
import com.google.gson.annotations.SerializedName;

public class Location {
    @SerializedName("lat")
    private Double lat;
    @SerializedName("lng")
    private Double lng;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
