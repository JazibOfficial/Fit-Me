package com.jazibkhan.healthcare;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by zeesh on 3/24/2018.
 */

public interface ApiInterface {

    @GET("api/place/nearbysearch/json?sensor=true&key=AIzaSyC729CaxVo0-DJ3GJXv2AhY_GwJ4ol0qMI")
    Call<MapsResponse> getNearbyGyms(@Query("location") String location, @Query("radius") String radius, @Query("name") String name);
}