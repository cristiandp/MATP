package com.example.cristian.mamaandroidthermalpos;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.cristian.mamaandroidthermalpos.R;
//import com.example.cristian.mamaandroidthermalpos.recycleviewproductos.Producto;

/**
 * Created by jessi on 26/12/2016.
 */

public class FragmentProductos extends Fragment implements View.OnClickListener{

    View vProductos;

    Button btnAnadirProd;
    Button btnEditarProd;
    Button btnBorrarProd;

    Fragment fragment;
    Bundle bundle;
    String txtInfo;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        vProductos = inflater.inflate(R.layout.fragment_productos, container, false);

        btnAnadirProd = (Button)vProductos.findViewById(R.id.btnAnadirProd);
        btnEditarProd = (Button)vProductos.findViewById(R.id.btnEditarProd);
        btnBorrarProd = (Button)vProductos.findViewById(R.id.btnBorrarProd);

        btnAnadirProd.setOnClickListener(this);
        btnEditarProd.setOnClickListener(this);
        btnBorrarProd.setOnClickListener(this);



        return vProductos;
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnAnadirProd:

                fragment = new FragmentAnadirEditar();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment)
                        .commit();

                bundle = new Bundle();



                txtInfo = getText(R.string.txtInfo1).toString();



                bundle.putString("info", txtInfo);
                fragment.setArguments(bundle);



                break;

            case R.id.btnEditarProd:

              /*  fragment = new FragmentAnadirEditar();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment)
                        .commit();

                bundle = new Bundle();

                txtInfo = getText(R.string.txtInfo2).toString();

                bundle.putString("info", txtInfo);
                fragment.setArguments(bundle);*/

                fragment = new FragmentEditarProd();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment)
                        .commit();


                break;


            case R.id.btnBorrarProd:


                break;
        }


    }
}