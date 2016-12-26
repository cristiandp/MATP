package com.example.cristian.mamaandroidthermalpos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Cristian on 15/12/2016.
 */

public class FragmentTickets extends android.support.v4.app.Fragment {

    TextView txtRef;

    String ref;
    Bundle bundle;
    View view;

    public FragmentTickets(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tickets, container, false);

        txtRef = (TextView) view.findViewById(R.id.txtRef);

        // Inflate the layout for this fragment
        bundle = getArguments();
        ref = bundle.getString("REF");

        txtRef.setText(ref);



        return view;
    }
}
