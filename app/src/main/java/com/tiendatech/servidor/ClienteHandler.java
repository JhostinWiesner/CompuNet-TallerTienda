package com.tiendatech.servidor;

import com.tiendatech.modelo.Producto;
import java.io.*;
import java.net.Socket;
import java.util.List;

public class ClienteHandler implements Runnable {
    private Socket socket;

    public ClienteHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // Inicialización de flujos siguiendo el orden recomendado para evitar bloqueos
        try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
            
            boolean conectado = true;
            while (conectado) {
                // TODO: 1. Leer el comando del cliente (String)
                // String comando = ...

                // TODO: 2. Estructurar el control de flujo (switch o if-else)
                // - Si es "LISTAR": Obtener lista del Inventario y enviarla.
                // - Si es "COMPRAR": Leer ID (int), Cantidad (int), procesar y enviar respuesta.
                // - Si es "SALIR": Romper el ciclo.
                
                // Tip: No olviden usar out.flush() después de enviar datos.
            }

        } catch (IOException e) {
            System.err.println("Error en la comunicación con el cliente: " + e.getMessage());
        } finally {
            cerrarConexion();
        }
    }

    private void cerrarConexion() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}