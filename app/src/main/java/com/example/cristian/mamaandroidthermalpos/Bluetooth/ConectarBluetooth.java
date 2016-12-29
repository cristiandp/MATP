package com.example.cristian.mamaandroidthermalpos.Bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


/**
 * Created by jessi on 29/12/2016.
 */

public class ConectarBluetooth{

    private int estadoBluetooth;
    private BluetoothAdapter bAdaptador = BluetoothAdapter.getDefaultAdapter();;

    private static final int BLUETOOTH_APAGADO = BluetoothAdapter.STATE_OFF;

    private static final int BLUETOOTH_ENCENDIDO = BluetoothAdapter.STATE_ON;

   // private Context contextRecibido;


   //registrarEventosBluetooth();


    //Registramos el bloadcastReciver que instanciamos previamente
    //para detectar los distintos eventos que queremso recibir

    public void registrarEventosBluetooth(Context context){

        Log.d("aviso", "Estas en el método");

        IntentFilter filtro = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);

        filtro.addAction(BluetoothDevice.ACTION_FOUND);
        filtro.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        context.registerReceiver(bRecibidor, filtro);

      //  Log.d("Context recibido", contextRecibido.toString());


    }


    private final BroadcastReceiver bRecibidor = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            //Cada vez que se descubra un nuevo dispositivo Bluetooth,
            // se ejecuta este fragmento de código

            String accionBluetooth = intent.getAction();

            Log.d("A la escucha", "escuchando");

            if(BluetoothAdapter.ACTION_STATE_CHANGED.equals(accionBluetooth)){

                estadoBluetooth = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,
                        BluetoothAdapter.ERROR);

                switch (estadoBluetooth){

                    case BluetoothAdapter.STATE_OFF:

                        Log.d("Bluetooth", "apagado");

                        break;

                    case BluetoothAdapter.STATE_ON:

                        Log.d("Bluetooth", "encendido");

                        break;




                }

            }


        }
    };

    public void empezarBusqueda(Context contextRecibido){

        //contextRecibido = this.contextRecibido;

        bAdaptador.isEnabled();

        bAdaptador.startDiscovery();

        registrarEventosBluetooth(contextRecibido);

    }

    public void finalizarBúsqueda(Context context){


        bAdaptador.cancelDiscovery();
        registrarEventosBluetooth(context);
    }



}
