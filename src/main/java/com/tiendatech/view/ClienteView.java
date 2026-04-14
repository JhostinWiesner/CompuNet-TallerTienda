package com.tiendatech.view;

import com.tiendatech.model.Producto;
import com.tiendatech.model.Respuesta;

import java.util.List;
import java.util.Scanner;

/**
 * View: Handles all user-facing input/output on the client side.
 */
public class ClienteView {

    private final Scanner scanner = new Scanner(System.in);

    // -------------------------------------------------------------------------
    // Menu
    // -------------------------------------------------------------------------

    public void mostrarMenu() {
        System.out.println("\n========== TiendaTech ==========");
        System.out.println("1. Buscar productos");
        System.out.println("2. Realizar compra");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    public int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // -------------------------------------------------------------------------
    // Purchase input
    // -------------------------------------------------------------------------

    public int pedirProductoId() {
        System.out.print("Ingrese el ID del producto: ");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public int pedirCantidad() {
        System.out.print("Ingrese la cantidad deseada: ");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // -------------------------------------------------------------------------
    // Response display
    // -------------------------------------------------------------------------

    public void mostrarListaProductos(Respuesta respuesta) {
        if (!respuesta.isExito()) {
            System.out.println("Error: " + respuesta.getMensaje());
            return;
        }
        List<Producto> productos = respuesta.getProductos();
        System.out.println("\n--- Productos disponibles ---");
        if (productos == null || productos.isEmpty()) {
            System.out.println("No hay productos disponibles.");
        } else {
            for (Producto p : productos) {
                System.out.println(p);
            }
        }
    }

    public void mostrarResultadoCompra(Respuesta respuesta) {
        if (respuesta.isExito()) {
            System.out.println("✔ " + respuesta.getMensaje());
        } else {
            System.out.println("✘ " + respuesta.getMensaje());
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void cerrar() {
        scanner.close();
    }
}
