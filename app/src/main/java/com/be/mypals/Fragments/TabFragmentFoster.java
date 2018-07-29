package com.be.mypals.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.be.mypals.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragmentFoster extends Fragment {


    public TabFragmentFoster() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_fragment_foster, container, false);
    }

}
