package com.example.jeansmits.jsonexercise.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jeansmits.jsonexercise.R;

public class Fragment_3 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_3, container, false);
    }

    public static Fragment_3 newInstance(int page, String title) {
        Fragment_3 fragment = new Fragment_3();
        return fragment;
    }

}
