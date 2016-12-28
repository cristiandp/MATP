package com.example.cristian.mamaandroidthermalpos;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
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

    LinearLayout lyBtnProd;

    ProductosDBOpenHelper dboh ;

    Fragment fragment;
    Bundle bundle;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        vEditarProd = inflater.inflate(R.layout.fragment_editarprod, container, false);

        edTxtBuscar = (EditText)vEditarProd.findViewById(R.id.edTxtBuscar);

        lyBtnProd = (LinearLayout)vEditarProd.findViewById(R.id.lyBtnProductos);

        dboh = new ProductosDBOpenHelper(getContext());





        edTxtBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                lyBtnProd.removeAllViews();
                String txtBuscar = edTxtBuscar.getText().toString();
                if(txtBuscar.length() > 1){
                    Cursor c = dboh.busquedaProductos(txtBuscar);
                    if(!c.moveToFirst()) {
                        return;
                    }else{
                        Button boton = new Button(getContext());
                        lyBtnProd.addView(boton);
                        boton.setText(c.getString(0));

                        boton.setOnClickListener(new View.OnClickListener(){

                            @Override
                            public void onClick(View view) {

                               Button b = (Button)view;

                                String nombre_prod = b.getText().toString();

                                Cursor c = dboh.obtenerDatosProd(nombre_prod);

                                if(c.moveToFirst()){

                                    fragment = new FragmentAnadirEditar();
                                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment)
                                            .commit();

                                    String categoria = c.getString(0);
                                    String stock = c.getString(1);
                                    String precio = c.getString(2);
                                    String referencia = c.getString(3);

                                    bundle = new Bundle();

                                    bundle.putString("categoria", categoria);
                                    bundle.putString("stock", stock);

                                    fragment.setArguments(bundle);
                                }
                            }
                        }
                        );

                        //TODO: Enviar datos de producto a editar al fragment FragmentAnadirEditar

                        while (c.moveToNext()) {
                            boton = new Button(getContext());
                            lyBtnProd.addView(boton);
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
