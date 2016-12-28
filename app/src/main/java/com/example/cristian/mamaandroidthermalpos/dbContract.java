package com.example.cristian.mamaandroidthermalpos;

import android.provider.BaseColumns;

/**
 * Created by jessi on 26/12/2016.
 */

public class dbContract {

    //Diseño o esquema de la BBDD. Facilita el mantenimiento por si en algún momento
    //cambian los nombres de las tablas o columnas


    //Se implementó la interfaz BaseColumns con el fin de agregar una columna extra
    // que se recomienda tenga toda tabla.

    public static abstract class NombreColumnas implements BaseColumns{

        //TABLAS
        static final String TABLA_TICKETS ="tickets";
        static final String TABLA_PRODUCTOS = "productos";

        //COLUMNAS
        static final String ID = "_id";
        static final String CATEGORIA = "categoria";
        static final String STOCK = "stock";
        static final String NOMBRE_PRODUCTO = "nombre_producto";
        static final String PRECIO = "precio";
        static final String REFERENCIA = "referencia";
        static final String HORA = "hora";



    }






}
