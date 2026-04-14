package com.tiendatech.controller;

import com.tiendatech.model.Producto;
import com.tiendatech.model.Respuesta;
import com.tiendatech.model.Solicitud;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Controller: Handles the business logic for the TiendaTech store.
 * Manages the shared product catalogue and processes client requests.
 */
public class TiendaController {

    private final List<Producto> productos =
            Collections.synchronizedList(new ArrayList<>());

    public TiendaController() {
        inicializarProductos();
    }

    // -------------------------------------------------------------------------
    // Initialization
    // -------------------------------------------------------------------------

    private void inicializarProductos() {
        productos.add(new Producto(1, "Laptop",       1200.0,  10));
        productos.add(new Producto(2, "Auriculares",    50.0,  50));
        productos.add(new Producto(3, "Mouse",          25.0, 100));
        productos.add(new Producto(4, "Teclado",        45.0,  80));
        productos.add(new Producto(5, "Monitor",       350.0,  20));
    }

    // -------------------------------------------------------------------------
    // Request dispatch
    // -------------------------------------------------------------------------

    /**
     * Processes an incoming {@link Solicitud} and returns a {@link Respuesta}.
     */
    public Respuesta procesarSolicitud(Solicitud solicitud) {
        switch (solicitud.getTipo()) {
            case BUSCAR_PRODUCTOS:
                return buscarProductos();
            case REALIZAR_COMPRA:
                return realizarCompra(solicitud.getProductoId(), solicitud.getCantidad());
            default:
                return new Respuesta(false, "Tipo de solicitud desconocido.");
        }
    }

    // -------------------------------------------------------------------------
    // Business logic
    // -------------------------------------------------------------------------

    private Respuesta buscarProductos() {
        List<Producto> copia = new ArrayList<>(productos);
        return new Respuesta(true, "Lista de productos obtenida.", copia);
    }

    private synchronized Respuesta realizarCompra(int productoId, int cantidad) {
        for (Producto producto : productos) {
            if (producto.getId() == productoId) {
                if (producto.getCantidadDisponible() >= cantidad) {
                    producto.setCantidadDisponible(producto.getCantidadDisponible() - cantidad);
                    return new Respuesta(true,
                            "Compra exitosa: " + cantidad + " x " + producto.getNombre());
                } else {
                    return new Respuesta(false,
                            "Stock insuficiente. Disponible: " + producto.getCantidadDisponible());
                }
            }
        }
        return new Respuesta(false, "Producto con id " + productoId + " no encontrado.");
    }
}
