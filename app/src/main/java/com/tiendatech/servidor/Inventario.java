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
        // TODO: Agregar productos quemados para las pruebas
    }

    // Método para que el ClienteHandler obtenga los datos a enviar
    public synchronized List<Producto> obtenerListaProductos() {
        // TODO: Retornar la lista que será enviada vía ObjectOutputStream
        return null; 
    }

    // Método crítico para la lógica de negocio
    public synchronized boolean procesarCompra(int id, int cantidad) {
        // TODO: 1. Buscar el producto por ID
        // TODO: 2. Validar si hay stock suficiente
        // TODO: 3. Si hay, reducirlo y retornar true. Si no, false.
        return false;
    }
}