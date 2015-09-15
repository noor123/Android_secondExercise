package com.example.jeansmits.jsonexercise.activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jeansmits.jsonexercise.R;

public class Fragment_1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_1, container, false);
    }

    public static Fragment_1 newInstance(int page, String title) {
        Fragment_1 fragment = new Fragment_1();
        return fragment;
    }


}
