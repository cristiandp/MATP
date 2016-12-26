package com.example.cristian.mamaandroidthermalpos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by Cristian on 15/12/2016.
 */

public class FragmentOpciones extends android.support.v4.app.Fragment {

    View view;
    String[] monedas;
    ArrayAdapter<String> adapter;
    Spinner spnMonedas;


    public FragmentOpciones(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_opciones,container,false);

        spnMonedas = (Spinner) view.findViewById(R.id.spnMonedas);

        monedas = getResources().getStringArray(R.array.monedas_array);
        adapter =  new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,monedas);
        spnMonedas.setAdapter(adapter);
        return view;

    }
}
