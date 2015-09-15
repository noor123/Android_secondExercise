package com.example.jeansmits.jsonexercise.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jeansmits.jsonexercise.R;

public class Fragment_2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_2, container, false);
    }

    public static Fragment_2 newInstance(int page, String title) {
        Fragment_2 fragment = new Fragment_2();
        return fragment;
    }

}
