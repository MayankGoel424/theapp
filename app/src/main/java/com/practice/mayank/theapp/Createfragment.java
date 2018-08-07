package com.practice.mayank.theapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.practice.mayank.theapp.Common.Common;
import com.practice.mayank.theapp.Model.INformation;
import com.practice.mayank.theapp.Model.user;
import com.rengwuxian.materialedittext.MaterialEditText;

import static android.view.View.*;

public class Createfragment extends Fragment {

float ratingbr;
    MaterialEditText name_create;
    MaterialEditText city_create;
    MaterialEditText country_create;
    MaterialEditText location_create;
    Spinner category_create;
    Button btn_create;
    String category = "";
RatingBar mratingbar;
    FirebaseDatabase firebase;
    DatabaseReference mdatabase,dtbse;
    String[] items = new String[]{"Food", "Education", "Entertainment","Hotel","Medical","Transportation","Others"};

    public static Createfragment newinstance(){
        Createfragment createfragment = new Createfragment();
        return createfragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebase = FirebaseDatabase.getInstance();
        mdatabase = firebase.getReference().child("Information");
dtbse=firebase.getReference().child("Users");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View createfrag = inflater.inflate(R.layout.createfrag,container,false);
        name_create = (MaterialEditText)createfrag.findViewById(R.id.name_create);
        city_create = (MaterialEditText)createfrag.findViewById(R.id.city_create);
        country_create = (MaterialEditText)createfrag.findViewById(R.id.country_create);
        location_create = (MaterialEditText)createfrag.findViewById(R.id.location_create);
        category_create =(Spinner)createfrag.findViewById(R.id.spinner1);
        btn_create =(Button)createfrag.findViewById(R.id.btn_create);
        mratingbar = (RatingBar)createfrag.findViewById(R.id.rating_bar);
        mratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingbr=rating;
            }
        });

ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category_create.setAdapter(adapter);
        category_create.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                switch (selectedItemText){

                    case "Food":
                        category="01";
                        break;
                    case "Education":
                        category="02";
                        break;
                    case "Entertainment":
                        category="03";
                        break;
                    case "Hotel":
                        category="04";
                        break;
                    case "Medical":
                        category="05";
                        break;
                    case "Transportation":
                        category="06";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        btn_create.setOnClickListener(new View.OnClickListener (){


    @Override
    public void onClick(View v) {
        final INformation iNformation = new INformation(country_create.getText().toString(),city_create.getText().toString(),
                name_create.getText().toString(),location_create.getText().toString(),category,ratingbr,1);
        mdatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mdatabase.child(iNformation.getName()+iNformation.getCity()+iNformation.getCountry()+iNformation.getLocation()).setValue(iNformation);
                Toast.makeText(getActivity(),"Added",Toast.LENGTH_SHORT).show();
                name_create.setText("");
                country_create.setText("");
                city_create.setText("");
                location_create.setText("");
                mratingbar.setRating(0);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }
});


        return createfrag;
    }


}
