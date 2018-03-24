package com.jazibkhan.healthcare;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class updates extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private FirebaseRecyclerAdapter<update, updateViewholder> adapter;
    private LinearLayoutManager mLayoutManager;
    private FirebaseDatabase mFirebaseDatabase;
    public DatabaseReference mDatabaseReference;

    public updates() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_updates, container, false);
        recyclerView=view.findViewById(R.id.rv);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("update");
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(true);
        progressDialog.show();
        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<update>().setQuery(mDatabaseReference, update.class).build();
        adapter = new FirebaseRecyclerAdapter<update, updateViewholder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull updateViewholder holder, int position, @NonNull update model) {
                holder.set(model);
                progressDialog.dismiss();
            }

            @Override
            public updateViewholder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.update_items, parent, false);
                return new updateViewholder(view);
            }
        };
        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}
