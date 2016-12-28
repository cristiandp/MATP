package com.example.cristian.mamaandroidthermalpos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by jessi on 27/12/2016.
 */

public class FragmentAnadirEditar extends Fragment{

    View vAnadirEditar;

    TextView txtInfo;

    EditText edTxtCategoria, edTxtStock, edTxtNombreProd, edTxtPrecio, edTxtRef;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        vAnadirEditar = inflater.inflate(R.layout.fragment_anadir_editar, container, false);

        txtInfo = (TextView)vAnadirEditar.findViewById(R.id.txtInformativo);
        edTxtCategoria = (EditText) vAnadirEditar.findViewById(R.id.edTxtCategoria);
        edTxtStock = (EditText) vAnadirEditar.findViewById(R.id.edTxtStock);
        edTxtNombreProd = (EditText) vAnadirEditar.findViewById(R.id.edTxtNombreProd);
        edTxtPrecio = (EditText) vAnadirEditar.findViewById(R.id.edTxtPrecio);
        edTxtRef = (EditText) vAnadirEditar.findViewById(R.id.edTxtRef);

        Bundle bundle = this.getArguments();

        if(bundle != null){

            String info = bundle.getString("info");

            String categoria = bundle.getString("categoria");
            String stock = bundle.getString("stock");
            String nombre_prod = bundle.getString("nombre_prod");
            String precio = bundle.getString("precio");
            String ref = bundle.getString("ref");

            txtInfo.setText(info);

            edTxtCategoria.setText(categoria);
            edTxtStock.setText(stock);
            edTxtNombreProd.setText(nombre_prod);
            edTxtPrecio.setText(precio);
            edTxtRef.setText(ref);
        }

        return vAnadirEditar;


    }
}
