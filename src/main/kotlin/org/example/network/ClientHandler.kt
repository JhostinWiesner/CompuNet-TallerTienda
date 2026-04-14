package org.example.network

import java.net.Socket

@Suppress("unused")
class ClientHandler(
    private val socket: Socket
) : Thread() {
    override fun run() {
        socket.hashCode()
        // TODO: procesar solicitudes del cliente en un hilo independiente
    }
}



