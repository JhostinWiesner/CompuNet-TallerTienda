package org.example.network;

import java.net.Socket;

@SuppressWarnings("unused")
public class ClientHandler extends Thread {
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        socket.hashCode();
        // TODO: procesar solicitudes del cliente en un hilo independiente
    }
}

