package com.tiendatech.model;

import java.io.Serializable;

/**
 * Model: Represents a request sent from the client to the server.
 * Types:
 *   - BUSCAR_PRODUCTOS: requests the full product list.
 *   - REALIZAR_COMPRA:  requests a purchase for a given product and quantity.
 */
public class Solicitud implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum Tipo {
        BUSCAR_PRODUCTOS,
        REALIZAR_COMPRA
    }

    private Tipo tipo;
    private int productoId;
    private int cantidad;

    /** Constructor for BUSCAR_PRODUCTOS requests. */
    public Solicitud(Tipo tipo) {
        this.tipo = tipo;
    }

    /** Constructor for REALIZAR_COMPRA requests. */
    public Solicitud(Tipo tipo, int productoId, int cantidad) {
        this.tipo = tipo;
        this.productoId = productoId;
        this.cantidad = cantidad;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public int getProductoId() {
        return productoId;
    }

    public int getCantidad() {
        return cantidad;
    }
}
