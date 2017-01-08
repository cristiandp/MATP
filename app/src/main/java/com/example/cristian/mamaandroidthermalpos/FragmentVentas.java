package com.example.cristian.mamaandroidthermalpos;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cristian.mamaandroidthermalpos.Bluetooth.ConectarBluetooth;
import com.example.cristian.mamaandroidthermalpos.Bluetooth.ESCUtil;
import com.example.cristian.mamaandroidthermalpos.Productos.Producto;
import com.example.cristian.mamaandroidthermalpos.Productos.ProductoAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Cristian on 15/12/2016.
 */

public class FragmentVentas extends android.support.v4.app.Fragment implements View.OnClickListener, VentasInteface {

    View view;
    TextView txtPrecio;
    public static Button btnCobrar;
    public Button btnBuscarProducto;


    AlertDialog.Builder confirmarAbrirCerrarCaja;
    Bundle bundle = new Bundle();
    Fragment fragment;

    public FragmentVentas(){

    }


    public void actualizarPrecio(){
        float precio = 0 ;
        String calculo;
        for (int i = 0 ; i < MainActivity.items.size();i++){

            Producto p =(Producto) MainActivity.items.get(i);
            precio += p.getPrecio()*p.getCantidad();
        }
        calculo = String.format(Locale.getDefault(),"%.2f", precio)+"€" ;
        txtPrecio.setText(calculo);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_ventas, container, false);
//
//        boton1 = (Button)  view.findViewById(R.id.btnBarras);
        txtPrecio = (TextView) view.findViewById(R.id.txtPrecio);
//        boton1.setOnClickListener(this);
        btnCobrar = (Button) view.findViewById(R.id.btnCobrar);
        btnBuscarProducto = (Button) view.findViewById(R.id.btnBuscarProducto);

        btnBuscarProducto.setOnClickListener(this);
        btnCobrar.setOnClickListener(this);


// Obtener el Recycler
        RecyclerView recycler = (RecyclerView) view.findViewById(R.id.rcvProductos);
        recycler.setHasFixedSize(true);

// Usar un administrador para LinearLayout
        RecyclerView.LayoutManager lManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(lManager);

// Crear un nuevo adaptador
        RecyclerView.Adapter adapter = new ProductoAdapter(MainActivity.items, this);
        recycler.setAdapter(adapter);


        actualizarPrecio();

        IntentFilter filtro = new IntentFilter();
        filtro.addAction(BluetoothDevice.ACTION_FOUND);
        filtro.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        if(MainActivity.saldo_inicial == 0f){
            btnCobrar.setEnabled(false);
            confirmarAbrirCerrarCaja = new AlertDialog.Builder(getContext());
            confirmarAbrirCerrarCaja.setTitle("Caja cerrada");
            confirmarAbrirCerrarCaja.setMessage("¿Deseas abrir la caja?");
            confirmarAbrirCerrarCaja.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    fragment = new FragmentCaja();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null)
                            .commit();
                    MainActivity.cambiarActivo(2,true);
                    ((MainActivity) getActivity()).cambiarTitulo("Estadísticas del día");
                }
            });
            confirmarAbrirCerrarCaja.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            confirmarAbrirCerrarCaja.show();
        }else{
            btnCobrar.setEnabled(true);
        }
        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnBarras:
                //Escaneo por código de barras
                break;

            case R.id.btnBuscarProducto:
                //Buscar por producto -> Requiere salto de fragment
                fragment = new FragmentEditarBorrarProd();
                bundle.putString("accionBtn","buscar");
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null)
                        .commit();
                MainActivity.cambiarActivo(2,true);
                ((MainActivity) getActivity()).cambiarTitulo("Buscar Producto");
                break;

            case R.id.btnCobrar:
                //Cobrar y general el ticket
                byte[] prueba = "HOLA MUNDO*******************\n".getBytes();
                try {
//                    ConectarBluetooth.enviarDatos(prueba,ConectarBluetooth.socket);
//                    byte[] ticket = ESCUtil.generarTicket(items);
                    byte[] ticket =ESCUtil.generarTicket(MainActivity.items);
                    ConectarBluetooth.enviarDatos(ticket,ConectarBluetooth.socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

        }
    }

    @Override
    public void VentasInterface(int position, int cantidad) {
        if (cantidad > 0) {
            MainActivity.items.get(position).setCantidad(cantidad);
        }
        actualizarPrecio();
    }



}


