package com.tiendatech.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorTienda {
    private static final int PUERTO = 12345;
    private static final int MAX_HILOS = 10; // Ajustable

    public static void main(String[] args) {
        // 1. Crear el Thread Pool
        ExecutorService pool = Executors.newFixedThreadPool(MAX_HILOS);

        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor iniciado en puerto " + PUERTO);

            while (true) {
                Socket socketCliente = serverSocket.accept();
                // 2. Pasar la conexión al pool
                pool.execute(new ClienteHandler(socketCliente));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}