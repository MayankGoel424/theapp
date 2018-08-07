package com.practice.mayank.theapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Userinfofrag extends Fragment {
    View myfragment;

    public static Userinfofrag newinstance(){
        Userinfofrag userinfofrag = new Userinfofrag();
        return userinfofrag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myfragment = inflater.inflate(R.layout.userfrag,container,false);
        return myfragment;
    }
}
