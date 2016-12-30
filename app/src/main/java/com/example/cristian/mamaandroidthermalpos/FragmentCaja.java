package com.example.cristian.mamaandroidthermalpos;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.support.design.widget.TabLayout.*;

/**
 * Created by Cristian on 15/12/2016.
 */

public class FragmentCaja extends android.support.v4.app.Fragment{

    View view;
    TextView producto;
    TabLayout tabs;
    ViewPager pager;

    RelativeLayout rlCaja;

    Fragment fragmento;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_caja, container, false);

     /*   producto = (TextView) view.findViewById(R.id.producto);

        DBOpenHelper dpoh = new DBOpenHelper(getContext());

        Cursor c = dpoh.obtenerProducto1("1");
        if(c.moveToFirst() == false){
            Toast.makeText(view.getContext(), "No hay resultados", Toast.LENGTH_SHORT).show();
        }else{
            String nombreProducto = c.getString(c.getColumnIndex(dbContract.NombreColumnas.NOMBRE_PRODUCTO));
            producto.setText(nombreProducto);
        }*/



        tabs = (TabLayout)view.findViewById(R.id.tabs);
       // pager = (ViewPager)view.findViewById(R.id.view_pager);

        rlCaja = (RelativeLayout)view.findViewById(R.id.rlCaja);

        pager = (ViewPager)view.findViewById(R.id.pager);



        tabs.addOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {
               Toast.makeText(view.getContext(), tab.getText(), Toast.LENGTH_SHORT).show();

             if(tab.getText().equals("Abrir caja")){

                    Toast.makeText(view.getContext(), "Has seleccionado el tab Abrir caja", Toast.LENGTH_SHORT).show();
                 //   view = inflater.inflate(R.layout.fragment_caja, container, false);




                }else if(tab.getText().equals("Cerrar caja")){
                    Toast.makeText(view.getContext(), "Has seleccionado el tab Cerrar caja", Toast.LENGTH_SHORT).show();
                  //  view = inflater.inflate(R.layout.fragment_cerrar_caja, container, false);
             }



            }

            @Override
            public void onTabUnselected(Tab tab) {

            }

            @Override
            public void onTabReselected(Tab tab) {

            }
        });

        return view;
    }



}
