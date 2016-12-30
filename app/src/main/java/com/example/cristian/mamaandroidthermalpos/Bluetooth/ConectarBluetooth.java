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

import java.util.Set;


/**
 * Created by jessi on 29/12/2016.
 */

public class ConectarBluetooth{

    public static BluetoothAdapter bAdapter;
    public static BluetoothSocket socket;
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



}
