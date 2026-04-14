package com.tiendatech.modelo;

import java.io.Serializable;

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

    // Getters y Setters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getCantidadDisponible() { return cantidadDisponible; }
    
    public synchronized void reducirStock(int cantidad) {
        this.cantidadDisponible -= cantidad;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | %s | Precio: $%.2f | Stock: %d", 
                id, nombre, precio, cantidadDisponible);
    }
}