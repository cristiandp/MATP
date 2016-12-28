package com.example.cristian.mamaandroidthermalpos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

/**
 * Created by Cristian on 15/12/2016.
 */

public class FragmentOpciones extends android.support.v4.app.Fragment implements View.OnClickListener {

    View view;
    String[] monedas;
    ArrayAdapter<String> adapter;
    Spinner spnMonedas;

    Button btnProductos;


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

        btnProductos = (Button)view.findViewById(R.id.btnProductos);

        btnProductos.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnProductos:

                Fragment fragment = new FragmentProductos();

                //Obtenemos el FragmentManager de la activity y le decimos que haga una transacción
                // que es remplazar el content_frame por el fragment indicado

                getActivity().getSupportFragmentManager()
                        .beginTransaction().replace(R.id.content_frame,fragment).addToBackStack(null)
                        .commit();
                break;
        }
    }
}
