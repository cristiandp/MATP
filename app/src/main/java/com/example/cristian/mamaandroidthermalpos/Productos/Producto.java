package com.example.cristian.mamaandroidthermalpos.Productos;

import android.content.ContentValues;
import com.example.cristian.mamaandroidthermalpos.dbContract;



/**
 * Created by Cristian on 16/12/2016.
 */

public class Producto {

    private int id;
    private String categoria;
    private int stock;
    private String nombre_producto;
    private float precio;
    private String referencia;
    private int cantidad;

    public Producto(String nombre_producto,float precio,int cantidad){
        this.nombre_producto = nombre_producto;
        this.precio = precio;
        this.cantidad = cantidad;

    }

    public Producto(String categoria, int stock, String nombre_producto, float precio, String referencia) {

        this.categoria = categoria;
        this.stock = stock;
        this.nombre_producto = nombre_producto;
        this.precio = precio;
        this.referencia = referencia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }


    public ContentValues toContentValues(){

        //Contenedor de valores. Almacena las columnas del registro en pares clave-valor
        ContentValues values = new ContentValues();

        values.put(dbContract.NombreColumnas.CATEGORIA, categoria);
        values.put(dbContract.NombreColumnas.STOCK, stock);
        values.put(dbContract.NombreColumnas.NOMBRE_PRODUCTO, nombre_producto);
        values.put(dbContract.NombreColumnas.PRECIO, precio);
        values.put(dbContract.NombreColumnas.REFERENCIA, referencia);

        return values;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCantidadS(){
        return Integer.toString(cantidad);
    }
}
