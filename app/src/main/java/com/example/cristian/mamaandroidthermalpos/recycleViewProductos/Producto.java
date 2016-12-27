package com.example.cristian.mamaandroidthermalpos.recycleViewProductos;

/**
 * Created by Cristian on 16/12/2016.
 */

public class Producto {
    String nombre;
    int cantidad = 1;
    float precio;

    public  Producto(String nombre, float precio,int cantidad){
        this.nombre = nombre;
        this.precio = precio;
//        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getCantidadS(){ return Integer.toString(cantidad); }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
