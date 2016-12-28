package com.example.cristian.mamaandroidthermalpos;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by jessi on 27/12/2016.
 */

public class FragmentEditarProd extends Fragment {

    View vEditarProd;

    EditText edTxtBuscar;

    LinearLayout lyEditarProd;

    ProductosDBOpenHelper dboh ;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        vEditarProd = inflater.inflate(R.layout.fragment_editarprod, container, false);

        edTxtBuscar = (EditText)vEditarProd.findViewById(R.id.edTxtBuscar);

        lyEditarProd = (LinearLayout)vEditarProd.findViewById(R.id.lyEditarProd);

        dboh = new ProductosDBOpenHelper(getContext());





        edTxtBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String txtBuscar = edTxtBuscar.getText().toString();
                if(txtBuscar.length() > 1){
                    Cursor c = dboh.obtenerProducto(txtBuscar);
                    if(!c.moveToFirst()) {
                        return;
                    }else{
                        Button boton = new Button(getContext());
                        lyEditarProd.addView(boton);
                        boton.setText(c.getString(0));

                        while (c.moveToNext()) {
                            boton = new Button(getContext());
                            lyEditarProd.addView(boton);
                            boton.setText(c.getString(0));
                        }
                    }
                    }

                }


            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return vEditarProd;
    }

}
