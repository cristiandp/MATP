package com.example.cristian.mamaandroidthermalpos;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Cristian on 15/12/2016.
 */

public class FragmentCierre extends android.support.v4.app.Fragment {

    public FragmentCierre(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cierre, container, false);
    }
}
