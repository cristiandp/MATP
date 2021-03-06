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
        public static final String TABLA_TICKETS ="tickets";
        public static final String TABLA_PRODUCTOS = "productos";
        public static final String TABLA_VENTAS = "ventas";
        public static final String TABLA_TICKETS_VENTAS = "tickets_ventas";
        public static final String TABLA_PRODUCTOS_VENTAS = "productos_ventas";

        //COLUMNAS
        public static final String ID = "_id";
        public static final String CATEGORIA = "categoria";
        public static final String STOCK = "stock";
        public static final String NOMBRE_PRODUCTO = "nombre_producto";
        public static final String PRECIO = "precio";
        public static final String REFERENCIA = "referencia";
        public static final String HORA = "hora";
        public static final String CANTIDAD = "cantidad";


        //ID's forágenas
        public static final String ID_PRODUCTO ="id_producto";
        public static final String ID_TICKET ="id_ticket";
        public static final String ID_VENTA ="id_venta";




    }






}
