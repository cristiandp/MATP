package com.example.cristian.mamaandroidthermalpos.Bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Created by jessi on 29/12/2016.
 */

public class BluetoothUtil {

    public static BluetoothSocket getSocket(BluetoothDevice dispositivo, UUID laUUID) throws IOException{

        BluetoothSocket socket = dispositivo.createRfcommSocketToServiceRecord(laUUID);
        socket.connect();
        return socket;
    }

    public static void enviarDatos(byte[] bytes, BluetoothSocket socket) throws IOException{

        OutputStream out = socket.getOutputStream();
        out.write(bytes, 0, bytes.length);
        out.close();

        Log.d("Enviar datos", "Confirmado");
    }
}
