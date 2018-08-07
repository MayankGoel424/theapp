package com.practice.mayank.theapp;

import android.content.Intent;
import android.icu.util.ULocale;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.practice.mayank.theapp.Common.Common;
import com.practice.mayank.theapp.Interfaces.itemclicklistner;
import com.practice.mayank.theapp.Model.category;
import com.practice.mayank.theapp.viewholder.catageryholder;
import com.squareup.picasso.Picasso;

public class Homefragment extends Fragment {
    View myfragment;

    RecyclerView listhome;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<category,catageryholder> adapter;

    FirebaseDatabase database;
    DatabaseReference catagories;
    public static Homefragment newinstance(){
        Homefragment homefragment = new Homefragment();
        return homefragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        catagories = database.getReference("Home");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myfragment = inflater.inflate(R.layout.homefrag,container,false);
        listhome = (RecyclerView)myfragment.findViewById(R.id.homelist);
        listhome.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(container.getContext());
        listhome.setLayoutManager(layoutManager);
        loadcatagories();

        return myfragment;
    }
    private void loadcatagories(){
        adapter = new FirebaseRecyclerAdapter<category, catageryholder>(
                category.class,
                R.layout.category_layout,
                catageryholder.class,
                catagories
        ) {
            @Override
            protected void populateViewHolder(catageryholder viewHolder, final category model, int position) {
viewHolder.catagery_name.setText(model.getName());
                Picasso.with(getActivity())
                        .load(model.getImage())
                        .into(viewHolder.category_image);
                viewHolder.setItemClickListner(new itemclicklistner() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                    Intent i = new Intent(getActivity(),Find.class);
                    Common.CatageryId=adapter.getRef(position).getKey();
                    startActivity(i);
                    }
                });
            }
        };
        adapter.notifyDataSetChanged();
        listhome.setAdapter(adapter);
    }

}
