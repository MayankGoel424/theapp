package com.practice.mayank.theapp.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.practice.mayank.theapp.Interfaces.itemclicklistner;
import com.practice.mayank.theapp.R;

public class catageryholder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView catagery_name;
    public ImageView category_image;
    private itemclicklistner ItemClickListner;

    public catageryholder(View itemView) {
        super(itemView);
        category_image = (ImageView)itemView.findViewById(R.id.category_image);
        catagery_name =(TextView)itemView.findViewById(R.id.catagery_name);
        itemView.setOnClickListener(this);

    }

    public void setItemClickListner(itemclicklistner itemClickListner) {
        ItemClickListner = itemClickListner;
    }

    @Override
    public void onClick(View v) {
        ItemClickListner.onClick(v,getAdapterPosition(),false);
    }
}
