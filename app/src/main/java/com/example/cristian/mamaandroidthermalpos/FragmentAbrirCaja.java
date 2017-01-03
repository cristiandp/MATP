package com.example.cristian.mamaandroidthermalpos;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by jessi on 30/12/2016.
 */

public class FragmentAbrirCaja extends Fragment {

    View view;
    TextView txtFechayHora;
    FloatingActionButton floatBtnCierre;


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_abrir_caja, container, false);

        txtFechayHora = (TextView) view.findViewById(R.id.txtFechayHora);

        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

        txtFechayHora.setText(currentDateTimeString);

        floatBtnCierre = (FloatingActionButton)view.findViewById(R.id.floatBtnCierre);

        floatBtnCierre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cerrarCaja();
            }
        });



        return view;

    }

    public void cerrarCaja(){

        AlertDialog.Builder confirmarCerrarCaja = new AlertDialog.Builder(getContext());

        confirmarCerrarCaja.setTitle("Cerrar caja");

        confirmarCerrarCaja.setMessage("¿Deseas cerrar la caja?");

        confirmarCerrarCaja.setPositiveButton("Confirmar", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Snackbar.make(getView(), "Se ha cerrado la caja", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        confirmarCerrarCaja.setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Snackbar.make(getView(), "No se ha cerrado la caja", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        confirmarCerrarCaja.show();

    }
}
