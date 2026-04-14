package com.tiendatech.model;

import java.io.Serializable;
import java.util.List;

/**
 * Model: Represents a response sent from the server back to the client.
 */
public class Respuesta implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean exito;
    private String mensaje;
    private List<Producto> productos;

    /** Constructor for purchase responses. */
    public Respuesta(boolean exito, String mensaje) {
        this.exito = exito;
        this.mensaje = mensaje;
    }

    /** Constructor for product-list responses. */
    public Respuesta(boolean exito, String mensaje, List<Producto> productos) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.productos = productos;
    }

    public boolean isExito() {
        return exito;
    }

    public String getMensaje() {
        return mensaje;
    }

    public List<Producto> getProductos() {
        return productos;
    }
}
