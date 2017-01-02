package com.example.cristian.mamaandroidthermalpos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jessi on 30/12/2016.
 */

public class FragmentCierreCaja extends Fragment {

    View view;
    EditText txtFechaActual;
    TextView txtFechayHora;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cerrar_caja, container, false);


        txtFechayHora = (TextView) view.findViewById(R.id.txtFechayHora);

        String fechaConHora = DateFormat.getDateTimeInstance().format(new Date());

        txtFechayHora.setText(fechaConHora);


        txtFechaActual = (EditText)view.findViewById(R.id.txtFechaActual);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");



        txtFechaActual.setText(sdf.format(new Date()));

        return view;

    }
}
