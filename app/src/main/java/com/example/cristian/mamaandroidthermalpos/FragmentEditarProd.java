package com.example.cristian.mamaandroidthermalpos;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by jessi on 27/12/2016.
 */

public class FragmentEditarProd extends Fragment {

    View vEditarProd;

    EditText edTxtBuscar;

    LinearLayout lyEditarProd;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        vEditarProd = inflater.inflate(R.layout.fragment_editarprod, container, false);

        edTxtBuscar = (EditText)vEditarProd.findViewById(R.id.edTxtBuscar);

        lyEditarProd = (LinearLayout)vEditarProd.findViewById(R.id.lyEditarProd);



        ProductosDBOpenHelper dboh = new ProductosDBOpenHelper(getContext());

        String txtBuscar = edTxtBuscar.getText().toString();


        Toast.makeText(getContext(), txtBuscar, Toast.LENGTH_SHORT).show();

        if(txtBuscar != "") {

            Cursor c = dboh.obtenerProducto(edTxtBuscar.getText().toString());

            while (c.moveToNext()) {

                Button boton = new Button(getContext());

                lyEditarProd.addView(boton);

                boton.setText(c.getString(0));

            }

        }


        return vEditarProd;
    }

}
