package org.example.network;

public class ServerSocketManager {
    private final int port;

    public ServerSocketManager() {
        this(12345);
    }

    public ServerSocketManager(int port) {
        this.port = port;
    }

    public void start() {
        // TODO: iniciar ServerSocket y aceptar clientes concurrentemente
    }
}

