package com.jazibkhan.healthcare;


import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;
import com.jazibkhan.healthcare.model.results;

import java.util.ArrayList;
import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 */
public class Nearby extends Fragment {

    private final static String API_KEY = "AIzaSyC729CaxVo0-DJ3GJXv2AhY_GwJ4ol0qMI";
    //private final static String LOCATION="28.5621,77.2841";
    private final static String RADIUS="5000";
    private final static String NAME="Gyms";
    private   String LOCATION="";
    List<results> results=new ArrayList<>();
    RecyclerView recyclerView;
    nearbyAdapter adapter;
    Double lat,lon;

    public Nearby() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nearby, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recylcler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter=new nearbyAdapter(results,getActivity());
        recyclerView.setAdapter(adapter);
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        GPSTracker gps = new GPSTracker(getActivity());
        if(gps.canGetLocation()){
             lat=gps.getLatitude();
             lon=gps.getLongitude();
        } else {
            gps.showSettingsAlert();
            Toast.makeText(getActivity(), "Enable Gps", Toast.LENGTH_SHORT).show();
        }
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(true);
        progressDialog.show();
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        LOCATION=lat+","+lon;
        Log.d("tag",LOCATION);
        Call<MapsResponse> call = apiService.getNearbyGyms(LOCATION,RADIUS,NAME);
        call.enqueue(new Callback<MapsResponse>() {
            @Override
            public void onResponse(Call<MapsResponse>call, Response<MapsResponse> response) {
                List<results> map = response.body().getResults();
                results.addAll(response.body().getResults());
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
                Log.d("check", "Number of locations received: " + map.size());
            }

            @Override
            public void onFailure(Call<MapsResponse>call, Throwable t) {
                // Log error here since request failed
                progressDialog.dismiss();
                Log.e("check", t.toString());
            }
        });
        return view;
    }



}
