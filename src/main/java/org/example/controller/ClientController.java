package org.example.controller;

import org.example.network.ClientSocketManager;

public class ClientController {
    private final ClientSocketManager socketManager;

    public ClientController(ClientSocketManager socketManager) {
        this.socketManager = socketManager;
    }

    public void connect() {
        socketManager.connect();
    }

    public void disconnect() {
        socketManager.close();
    }

    public void requestProducts() {
        // TODO: solicitar lista de productos al servidor
    }

    public void requestPurchase(int productId, int quantity) {
        // TODO: enviar solicitud de compra al servidor
    }
}

