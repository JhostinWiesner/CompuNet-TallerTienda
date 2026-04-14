package org.example.controller

import org.example.network.ClientSocketManager

class ClientController(
    private val socketManager: ClientSocketManager
) {
    fun connect() {
        socketManager.connect()
    }

    fun disconnect() {
        socketManager.close()
    }

    fun requestProducts() {
        // TODO: solicitar lista de productos al servidor
    }

    fun requestPurchase(productId: Int, quantity: Int) {
        // TODO: enviar solicitud de compra al servidor
    }
}

