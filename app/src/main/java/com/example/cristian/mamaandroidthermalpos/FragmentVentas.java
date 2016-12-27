package com.example.cristian.mamaandroidthermalpos;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cristian.mamaandroidthermalpos.recycleViewProductos.Producto;
import com.example.cristian.mamaandroidthermalpos.recycleViewProductos.ProductoAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Cristian on 15/12/2016.
 */

public class FragmentVentas extends android.support.v4.app.Fragment implements View.OnClickListener, VentasInteface {

    View view;
    static TextView txtPrecio;

    List<Producto> items = new ArrayList<Producto>();

    public FragmentVentas(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_ventas,container,false);
//
//        boton1 = (Button)  view.findViewById(R.id.btnBarras);
        txtPrecio = (TextView) view.findViewById(R.id.txtPrecio);
//        boton1.setOnClickListener(this);


        //DUMMY DATA
        items.add(new Producto("producto 1",40.20f,1));
        items.add(new Producto("producto 2",12.55f,5));
        items.add(new Producto("producto 3",34.10f,4));
        items.add(new Producto("producto 4",59.95f,6));

        items.add(new Producto("producto 1",40.20f,1));
        items.add(new Producto("producto 2",12.55f,5));
        items.add(new Producto("producto 3",34.10f,4));
        items.add(new Producto("producto 4",59.95f,6));

        items.add(new Producto("producto 1",40.20f,1));
        items.add(new Producto("producto 2",12.55f,5));
        items.add(new Producto("producto 3",34.10f,4));
        items.add(new Producto("producto 4",59.95f,6));

        items.add(new Producto("producto 1",40.20f,1));
        items.add(new Producto("producto 2",12.55f,5));
        items.add(new Producto("producto 3",34.10f,4));
        items.add(new Producto("producto 4",59.95f,6));



// Obtener el Recycler
        RecyclerView recycler = (RecyclerView) view.findViewById(R.id.rcvProductos);
        recycler.setHasFixedSize(true);

// Usar un administrador para LinearLayout
        RecyclerView.LayoutManager lManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(lManager);

// Crear un nuevo adaptador
        RecyclerView.Adapter adapter = new ProductoAdapter(items,this);
        recycler.setAdapter(adapter);



        actualizarPrecio();
        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){

        }
    }


    public void actualizarPrecio(){
        float precio = 0 ;
        String calculo;
        for (int i = 0 ; i < items.size();i++){

            Producto p =(Producto) items.get(i);
            precio += p.getPrecio()*p.getCantidad();
        }
        calculo = String.format(Locale.getDefault(),"%.2f", precio)+"€" ;
        txtPrecio.setText(calculo);
    }


    @Override
    public void VentasInterface(int position, int cantidad) {
        if (cantidad > 0) {
            items.get(position).setCantidad(cantidad);
        }
        actualizarPrecio();
    }
}


