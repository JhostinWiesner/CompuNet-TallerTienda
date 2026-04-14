package com.tiendatech.server;

import com.tiendatech.controller.TiendaController;
import com.tiendatech.view.ServidorView;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server: Entry point for the TiendaTech server.
 * Accepts incoming client connections and spawns a {@link ClienteHandler}
 * thread for each one.
 */
public class ServidorTienda {

    private static final int PUERTO = 12345;

    public static void main(String[] args) {
        TiendaController controller = new TiendaController();
        ServidorView view = new ServidorView();

        view.mostrarInicio(PUERTO);

        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            while (true) {
                Socket clienteSocket = serverSocket.accept();
                new ClienteHandler(clienteSocket, controller, view).start();
            }
        } catch (IOException e) {
            view.mostrarError("Error en el servidor", e);
        }
    }
}
