package org.example.network;

public class ClientSocketManager {
    private final String host;
    private final int port;

    public ClientSocketManager() {
        this("localhost", 12345);
    }

    public ClientSocketManager(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() {
        // TODO: abrir conexion con el servidor
    }

    public void close() {
        // TODO: cerrar conexion
    }
}

