package com.practice.mayank.theapp;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class Home extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigationbar);
    bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch(item.getItemId()){
                case R.id.homeid :
                    selectedFragment = Homefragment.newinstance();
                    break;
                case R.id.create:
                    selectedFragment = Createfragment.newinstance();
                    break;
                case R.id.userinfo:
                    selectedFragment=Userinfofrag.newinstance();
                    break;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_layout,selectedFragment);
            transaction.commit();
            return true;
        }

    });

    setdefaultfragment();
    }

    private void setdefaultfragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_layout,Homefragment.newinstance());
        transaction.commit();
    }
}
