package com.tiendatech.servidor;

import com.tiendatech.modelo.Producto;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Inventario {
    private static Inventario instancia;
    private List<Producto> productos;

    // Constructor privado para el patrón Singleton
    private Inventario() {
        // La lista debe ser sincronizada para manejo de hilos
        productos = Collections.synchronizedList(new ArrayList<>());
        inicializarProductos();
    }

    // Acceso global al Singleton
    public static synchronized Inventario getInstancia() {
        if (instancia == null) {
            instancia = new Inventario();
        }
        return instancia;
    }

    private void inicializarProductos() {
        productos.add(new Producto(1, "compu", 1200.00, 10));
        productos.add(new Producto(2, "audifonos", 50.00, 50));
        productos.add(new Producto(3, "mouse", 25.00, 100));
        productos.add(new Producto(4, "teclado", 75.00, 30));
        productos.add(new Producto(5, "cargador", 200.00, 15));
    }

    // Método para que el ClienteHandler obtenga los datos a enviar
    public synchronized List<Producto> obtenerListaProductos() {
        // TODO: Retornar la lista que será enviada vía ObjectOutputStream
        return new ArrayList<>(productos);
    }

    // Método crítico para la lógica de negocio
    public synchronized boolean procesarCompra(int id, int cantidad) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                if (p.getCantidadDisponible() >= cantidad) {
                    p.reducirStock(cantidad);
                    return true;
                }
                return false;
            }
        }
        return false;
    }
}