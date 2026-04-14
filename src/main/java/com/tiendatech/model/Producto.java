package com.tiendatech.model;

import java.io.Serializable;

/**
 * Model: Represents a product in the TiendaTech store.
 */
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nombre;
    private double precio;
    private int cantidadDisponible;

    public Producto(int id, String nombre, double precio, int cantidadDisponible) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadDisponible = cantidadDisponible;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s - $%.2f (Stock: %d)", id, nombre, precio, cantidadDisponible);
    }
}
