package com.jazibkhan.healthcare;

import com.google.gson.annotations.SerializedName;
import com.jazibkhan.healthcare.model.results;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeesh on 3/24/2018.
 */

public class MapsResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("results")
    private ArrayList<results>  results;
   // private List<results> results = new ArrayList<results>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<results> getResults() {
        return results;
    }

    }

