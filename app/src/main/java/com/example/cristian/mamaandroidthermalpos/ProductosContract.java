package com.example.cristian.mamaandroidthermalpos;

import android.provider.BaseColumns;

/**
 * Created by jessi on 26/12/2016.
 */

public class ProductosContract {

    //Diseño o esquema de la BBDD. Facilita el mantenimiento por si en algún momento
    //cambian los nombres de las tablas o columnas


    //Se implementó la interfaz BaseColumns con el fin de agregar una columna extra
    // que se recomienda tenga toda tabla.

    public static abstract class NombreColumnas implements BaseColumns{

        public static final String NOMBRE_TABLA = "productos";

        public static final String ID = "id";
        public static final String CATEGORIA = "categoria";
        public static final String STOCK = "stock";
        public static final String NOMBRE_PRODUCTO = "nombre_producto";
        public static final String PRECIO = "precio";
        public static final String REFERENCIA = "referencia";




    }






}
