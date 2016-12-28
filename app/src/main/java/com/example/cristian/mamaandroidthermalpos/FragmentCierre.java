package com.example.cristian.mamaandroidthermalpos;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Cristian on 15/12/2016.
 */

public class FragmentCierre extends android.support.v4.app.Fragment {

    View view;
    TextView producto;

    public FragmentCierre(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_estadisticas, container, false);

        producto = (TextView) view.findViewById(R.id.producto);

        ProductosDBOpenHelper dpoh = new ProductosDBOpenHelper(getContext());

        Cursor c = dpoh.obtenerProducto1("1");
        if(c.moveToFirst() == false){
            Toast.makeText(view.getContext(), "No hay resultados", Toast.LENGTH_SHORT).show();
        }else{
            String nombreProducto = c.getString(c.getColumnIndex(ProductosContract.NombreColumnas.NOMBRE_PRODUCTO));
            producto.setText(nombreProducto);
        }






        return view;
    }
}
