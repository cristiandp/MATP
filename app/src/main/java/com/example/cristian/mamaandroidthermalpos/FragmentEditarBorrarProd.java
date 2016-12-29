package com.example.cristian.mamaandroidthermalpos;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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

public class FragmentEditarBorrarProd extends Fragment {

    View vEditarProd;

    EditText edTxtBuscar;

    LinearLayout lyBtnProd;

    DBOpenHelper dboh ;

    Fragment fragment;
    Bundle bundle;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        vEditarProd = inflater.inflate(R.layout.fragment_editarprod, container, false);

        edTxtBuscar = (EditText)vEditarProd.findViewById(R.id.edTxtBuscar);

        lyBtnProd = (LinearLayout)vEditarProd.findViewById(R.id.lyBtnProductos);

        dboh = new DBOpenHelper(getContext());

        bundle = this.getArguments();





        edTxtBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                lyBtnProd.removeAllViews();
                String txtBuscar = edTxtBuscar.getText().toString();

                if(txtBuscar.length() >1){
                    Cursor c = dboh.busquedaProductos(txtBuscar);
                    if(c.moveToFirst()){
                        do{
                            Button boton = new Button(getContext());
                            lyBtnProd.addView(boton);
                            boton.setText(c.getString(0));
                            boton.setOnClickListener(new View.OnClickListener(){
                                @Override
                                public void onClick(View view) {
                                    Button b = (Button)view;
                                    String nombre_prod = b.getText().toString();
                                    String accion = bundle.getString("accionBtn");
                                    if(accion == "editar") {
                                        enviarDatos(nombre_prod);
                                    }else if (accion == "borrar"){
                                        borrarProd();
                                    }
                                }
                            });
                        }while(c.moveToNext());
                    }
                }
            }


            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return vEditarProd;
    }


    public void enviarDatos(String nombre_prod){

        Cursor c = dboh.obtenerDatosProd(nombre_prod);

        if(c.moveToFirst()){

            fragment = new FragmentAnadirEditar();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null)
                    .commit();

            String txtInfo = getText(R.string.txtInfo2).toString();

            String categoria = c.getString(0);
            String stock = c.getString(1);
            String precio = c.getString(2);
            String referencia = c.getString(3);



            bundle = new Bundle();

            bundle.putString("info",txtInfo);

            bundle.putString("categoria", categoria);
            bundle.putString("stock", stock);
            bundle.putString("nombre_prod", nombre_prod);
            bundle.putString("precio", precio);
            bundle.putString("ref", referencia);

            fragment.setArguments(bundle);
        }

    }

    public void borrarProd(){

        AlertDialog.Builder confirmarBorrar = new AlertDialog.Builder(getContext());

        confirmarBorrar.setTitle("Borrar elemento");

        confirmarBorrar.setMessage("¿Deseas borrar este elemento de la tabla?");

        confirmarBorrar.setPositiveButton("Confirmar", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //TODO: Borrar elemento de la BD

                Toast.makeText(getContext(), "Has simulado borrar un elemento", Toast.LENGTH_SHORT).show();
            }
        });

        confirmarBorrar.setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Toast.makeText(getContext(), "Has cancelado la acción", Toast.LENGTH_SHORT).show();
            }
        });

        confirmarBorrar.show();

    }

}
