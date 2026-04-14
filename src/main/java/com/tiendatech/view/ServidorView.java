package com.tiendatech.view;

/**
 * View: Handles all console output on the server side.
 */
public class ServidorView {

    public void mostrarInicio(int puerto) {
        System.out.println("========================================");
        System.out.println("  TiendaTech - Servidor iniciado");
        System.out.println("  Escuchando en el puerto: " + puerto);
        System.out.println("========================================");
    }

    public void mostrarClienteConectado(String direccion) {
        System.out.println("[+] Cliente conectado: " + direccion);
    }

    public void mostrarClienteDesconectado(String direccion) {
        System.out.println("[-] Cliente desconectado: " + direccion);
    }

    public void mostrarSolicitud(String direccion, String tipo) {
        System.out.println("[>] Solicitud de " + direccion + ": " + tipo);
    }

    public void mostrarError(String mensaje, Exception e) {
        System.err.println("[ERROR] " + mensaje + ": " + e.getMessage());
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println("[INFO] " + mensaje);
    }
}
