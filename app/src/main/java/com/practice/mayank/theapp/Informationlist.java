package com.practice.mayank.theapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.practice.mayank.theapp.Common.Common;
import com.practice.mayank.theapp.Model.INformation;

public class Informationlist extends AppCompatActivity {
    private RecyclerView mbloglist;
    private DatabaseReference mdatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informationlist);

        mdatabase = FirebaseDatabase.getInstance().getReference("Information");
        mdatabase.keepSynced(true);

        mbloglist = (RecyclerView)findViewById(R.id.recyclerviewlist);
        mbloglist.setHasFixedSize(true);
        mbloglist.setLayoutManager(new LinearLayoutManager(null));
    }

    @Override
    protected void onStart() {
        final RatingBar rating_bardata=(RatingBar)findViewById(R.id.rating_bardata);
        super.onStart();
        FirebaseRecyclerAdapter<INformation,Blogviewholder>firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<INformation, Blogviewholder>
                (INformation.class,R.layout.cardviewlist,Blogviewholder.class,mdatabase) {
            @Override
            protected void populateViewHolder(Blogviewholder viewHolder, INformation model, int position) {
                if (model.getCategoryId().equals(Common.CatageryId) && model.getCountry().equals(Common.country) && model.getCity().equals(Common.city)) {
                    viewHolder.setName(model.getName());
                    viewHolder.setCity(model.getCity());
                    viewHolder.setCountry(model.getCountry());
                    viewHolder.setLocation(model.getLocation());
                    viewHolder.setRating(model.getRating());
                }
else{
                    viewHolder.Layout_hide();
                }

            }
        };
        mbloglist.setAdapter(firebaseRecyclerAdapter);
    }
    public static class Blogviewholder extends RecyclerView.ViewHolder{
        View mview;
        LinearLayout layout;
        LinearLayout.LayoutParams params;

        public Blogviewholder(View itemview){
            super(itemview);
            mview=itemview;

            layout = (LinearLayout)itemview.findViewById(R.id.show_layout);
            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        }
        private void Layout_hide(){
            params.height=0;
            itemView.setLayoutParams(params);
        }

        public void setName(String name){
            TextView name_info = (TextView)mview.findViewById(R.id.name_info);
            name_info.setText(name);
        }
        public void setCity(String city){
            TextView city_info=(TextView)mview.findViewById(R.id.city_info);
            city_info.setText(city);
        }

        public void setCountry(String city){
            TextView country_info=(TextView)mview.findViewById(R.id.country_info);
            country_info.setText(city);
        }

        public void setLocation(String city){
            TextView location_info=(TextView)mview.findViewById(R.id.location_info);
            location_info.setText(city);
        }
        public void setRating(float rating){
            RatingBar rating_bardata=(RatingBar)mview.findViewById(R.id.rating_bardata);
            rating_bardata.setRating(rating);
        }

    }
}
