package com.tiendatech.server;

import com.tiendatech.controller.TiendaController;
import com.tiendatech.model.Respuesta;
import com.tiendatech.model.Solicitud;
import com.tiendatech.view.ServidorView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Server: Thread that handles a single client connection.
 * Reads {@link Solicitud} objects, delegates to {@link TiendaController},
 * and writes {@link Respuesta} objects back to the client.
 */
public class ClienteHandler extends Thread {

    private final Socket clienteSocket;
    private final TiendaController controller;
    private final ServidorView view;

    public ClienteHandler(Socket clienteSocket, TiendaController controller, ServidorView view) {
        this.clienteSocket = clienteSocket;
        this.controller = controller;
        this.view = view;
    }

    @Override
    public void run() {
        String direccion = clienteSocket.getInetAddress().getHostAddress();
        view.mostrarClienteConectado(direccion);

        try (ObjectOutputStream out = new ObjectOutputStream(clienteSocket.getOutputStream());
             ObjectInputStream  in  = new ObjectInputStream(clienteSocket.getInputStream())) {

            Solicitud solicitud;
            while ((solicitud = (Solicitud) in.readObject()) != null) {
                view.mostrarSolicitud(direccion, solicitud.getTipo().name());
                Respuesta respuesta = controller.procesarSolicitud(solicitud);
                out.writeObject(respuesta);
                out.flush();
                out.reset();
            }

        } catch (IOException | ClassNotFoundException e) {
            // Client disconnected or stream ended – not necessarily an error
        } finally {
            view.mostrarClienteDesconectado(direccion);
            try {
                clienteSocket.close();
            } catch (IOException e) {
                view.mostrarError("Error al cerrar socket del cliente", e);
            }
        }
    }
}
