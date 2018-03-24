package com.jazibkhan.healthcare;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jazibkhan.healthcare.model.results;

import java.util.List;

/**
 * Created by zeesh on 3/24/2018.
 */

public class nearbyAdapter extends   RecyclerView.Adapter<nearbyAdapter.NearbyViewHolder>{

    private List<com.jazibkhan.healthcare.model.results> results;
    private Context context;


    public static class NearbyViewHolder extends RecyclerView.ViewHolder {
        TextView name,rating,address;


        public NearbyViewHolder(View v) {
            super(v);
            //movie = (ImageView) v.findViewById(R.id.imagemovie);
            name= (TextView)v.findViewById(R.id.namemap);
            rating=(TextView)v.findViewById(R.id.rating);
            address=(TextView)v.findViewById(R.id.address);
        }
    }

    public nearbyAdapter(List<results> results, Context context) {
        this.results = results;
        this.context = context;
    }

    @Override
    public nearbyAdapter.NearbyViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.maps_items, parent, false);
        return new NearbyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(NearbyViewHolder holder, final int position) {
        holder.name.setText(results.get(position).getName());
        holder.rating.setText(String.valueOf(results.get(position).getRating()));
        holder.address.setText(results.get(position).getVicinity());

    }

    @Override
    public int getItemCount() {
        return results.size();
    }
}


