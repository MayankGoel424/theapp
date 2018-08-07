package com.practice.mayank.theapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.practice.mayank.theapp.Common.Common;
import com.rengwuxian.materialedittext.MaterialEditText;

public class Find extends AppCompatActivity {

TextView getname;
    MaterialEditText countrylist;
    MaterialEditText citylist;
    Button search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        getname=(TextView)findViewById(R.id.getname);
        search = (Button)findViewById(R.id.btn_create);
        switch (Common.CatageryId){
            case "01":
                getname.setText("Food");
                break;
            case "02":
                getname.setText("Education");
                break;
            case "03":
                getname.setText("Entertainment");
                break;
            case "04":
                getname.setText("Hotel");
                break;
            case "05":
                getname.setText("Medical");
                break;
            case "06":
                getname.setText("Transportation");
                break;

        }
    }

    public void searchclick(View view){
        countrylist = (MaterialEditText)findViewById(R.id.countrylist);
        citylist = (MaterialEditText)findViewById(R.id.citylist);
        Common.country = countrylist.getText().toString();
        Common.city = citylist.getText().toString();



        Intent i = new Intent(Find.this,Informationlist.class);
        startActivity(i);


    }
}
