package com.example.cristian.mamaandroidthermalpos;

import com.example.cristian.mamaandroidthermalpos.recycleViewProductos.Producto;

/**
 * Created by jessi on 26/12/2016.
 */

public class Productos {

    private int id;
    private String categoria;
    private int stock;
    private String nombre_producto;
    private float precio;
    private String referencia;

    public Productos(String referencia, float precio, String nombre_producto, int stock, String categoria) {
        this.referencia = referencia;
        this.precio = precio;
        this.nombre_producto = nombre_producto;
        this.stock = stock;
        this.categoria = categoria;
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
}
