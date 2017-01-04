package com.example.cristian.mamaandroidthermalpos;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cristian.mamaandroidthermalpos.tabCajas.CajaPageAdapter;

import static android.support.design.widget.TabLayout.*;

/**
 * Created by Cristian on 15/12/2016.
 */

public class FragmentCaja extends android.support.v4.app.Fragment implements View.OnClickListener{

    View view;
    ViewPager viewPager;
    TabLayout tabLayout;
    FloatingActionButton fabCaja;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_caja, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new CajaPageAdapter(getChildFragmentManager()));

        tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        fabCaja = (FloatingActionButton) view.findViewById(R.id.fabCaja);
        fabCaja.setOnClickListener(this);

        return view;


    }

    @Override
    public void onClick(final View view) {

        AlertDialog.Builder confirmarAbrirCerrarCaja = new AlertDialog.Builder(getContext());
        if(view.getId() == R.id.fabCaja){
            if(!MainActivity.sCaja){
                confirmarAbrirCerrarCaja.setTitle("Abrir caja");

                confirmarAbrirCerrarCaja.setMessage("¿Deseas abrir la caja?");

                confirmarAbrirCerrarCaja.setPositiveButton("Confirmar", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Snackbar.make(getView(), "Se ha abierto la caja", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();


                        fabCaja.setImageResource(R.drawable.lock);

                        MainActivity.sCaja = true;
                    }
                });

                confirmarAbrirCerrarCaja.setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Snackbar.make(getView(), "No se ha abierto la caja", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                });

                confirmarAbrirCerrarCaja.show();


            }
            else{
                //Caja cerrada

                confirmarAbrirCerrarCaja.setTitle("Cerrar caja");
                confirmarAbrirCerrarCaja.setMessage("¿Deseas cerrar la caja?");

                confirmarAbrirCerrarCaja.setPositiveButton("Confirmar", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Snackbar.make(getView(), "Se ha cerrado la caja", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();


                        fabCaja.setImageResource(R.drawable.lock_open);

                        MainActivity.sCaja = false;
                    }
                });

                confirmarAbrirCerrarCaja.setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Snackbar.make(getView(), "No se ha cerrado la caja", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                });

                confirmarAbrirCerrarCaja.show();




            }
        }
    }
}
