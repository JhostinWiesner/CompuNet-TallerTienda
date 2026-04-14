package com.tiendatech.client;

import com.tiendatech.model.Respuesta;
import com.tiendatech.model.Solicitud;
import com.tiendatech.view.ClienteView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Client: Entry point for a TiendaTech client.
 * Connects to the server via a socket and presents an interactive menu
 * allowing the user to browse products and make purchases.
 */
public class ClienteTienda {

    private static final String HOST  = "localhost";
    private static final int    PUERTO = 12345;

    public static void main(String[] args) {
        ClienteView view = new ClienteView();

        try (Socket socket = new Socket(HOST, PUERTO);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream  in  = new ObjectInputStream(socket.getInputStream())) {

            view.mostrarMensaje("Conectado al servidor TiendaTech.");
            boolean activo = true;

            while (activo) {
                view.mostrarMenu();
                int opcion = view.leerOpcion();

                switch (opcion) {
                    case 1:
                        // Buscar productos
                        out.writeObject(new Solicitud(Solicitud.Tipo.BUSCAR_PRODUCTOS));
                        out.flush();
                        Respuesta respuestaBusqueda = (Respuesta) in.readObject();
                        view.mostrarListaProductos(respuestaBusqueda);
                        break;

                    case 2:
                        // Realizar compra
                        int productoId = view.pedirProductoId();
                        int cantidad   = view.pedirCantidad();
                        out.writeObject(new Solicitud(Solicitud.Tipo.REALIZAR_COMPRA, productoId, cantidad));
                        out.flush();
                        Respuesta respuestaCompra = (Respuesta) in.readObject();
                        view.mostrarResultadoCompra(respuestaCompra);
                        break;

                    case 3:
                        activo = false;
                        view.mostrarMensaje("Cerrando conexion. ¡Hasta luego!");
                        break;

                    default:
                        view.mostrarMensaje("Opcion invalida. Intente de nuevo.");
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            view.mostrarMensaje("Error de conexion: " + e.getMessage());
        } finally {
            view.cerrar();
        }
    }
}
