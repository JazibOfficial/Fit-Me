package com.jazibkhan.healthcare;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.jazibkhan.healthcare.update;

/**
 * Created by zeesh on 3/24/2018.
 */

public class updateViewholder extends RecyclerView.ViewHolder {
    private TextView mTitle,mDescription,mDate,mTime;

    public updateViewholder(View v){
        super(v);
        mDate=(TextView)v.findViewById(R.id.date);
        mDescription=(TextView)v.findViewById(R.id.description);
        mTime=(TextView)v.findViewById(R.id.time);
        mTitle=(TextView)v.findViewById(R.id.title);
    }

    public void set(update mUpdate){
        mDate.setText(mUpdate.getDate());
        mTitle.setText(mUpdate.getTitle());
        mTime.setText(mUpdate.getTime());
        mDescription.setText(mUpdate.getDescription());
    }
}
