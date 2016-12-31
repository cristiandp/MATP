package com.example.cristian.mamaandroidthermalpos.Bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;


/**
 * Created by jessi on 29/12/2016.
 */

public class ConectarBluetooth{

    public static BluetoothAdapter bAdapter;
    public static BluetoothSocket socket;
    private static final UUID PRINTER_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    Set<BluetoothDevice> bonded;

    public ConectarBluetooth(Context context){
        bAdapter = BluetoothAdapter.getDefaultAdapter();
        if(bAdapter == null){
            //Carece de BT
            bonded = bAdapter.getBondedDevices();

            new AlertDialog.Builder(context).setTitle("No compatible")
                    .setMessage("Tu teléfono no soporta Bluetooth")
                    .setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                               System.exit(0);
                           }
                       })
                    .setIcon(android.R.drawable.ic_dialog_alert).show();
           }
    }


    public static BluetoothSocket getSocket(BluetoothDevice dispositivo) throws IOException {

        BluetoothSocket socket = dispositivo.createRfcommSocketToServiceRecord(PRINTER_UUID);
        socket.connect();
        return socket;
    }

    public static void enviarDatos(byte[] bytes, BluetoothSocket socket) throws IOException{

        OutputStream out = socket.getOutputStream();
        out.write(bytes, 0, bytes.length);
//        out.close();

        Log.d("Enviar datos", "Confirmado");
    }


    public byte[] getHeader(){
        byte[] header = new byte[5];




        return header;
    }



}
