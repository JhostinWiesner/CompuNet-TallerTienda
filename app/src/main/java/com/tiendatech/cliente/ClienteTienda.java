package com.tiendatech.cliente;

import com.tiendatech.modelo.Producto;
import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class ClienteTienda {
    private static final String HOST = "localhost"; 
    private static final int PUERTO = 12345;

    public static void main(String[] args) {
        // Establecer conexión y preparar flujos de datos
        try (Socket socket = new Socket(HOST, PUERTO);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             Scanner sc = new Scanner(System.in)) {

            String opcion = "";
            while (!opcion.equals("3")) {
                mostrarMenu();
                opcion = sc.nextLine();

                if (opcion.equals("1")) {
                    // TODO: 1. Enviar comando "LISTAR" al servidor
                    // TODO: 2. Recibir la List<Producto> (Casting necesario)
                    // TODO: 3. Mostrar los productos en consola
                } 
                else if (opcion.equals("2")) {
                    // TODO: 1. Enviar comando "COMPRAR"
                    // TODO: 2. Pedir ID y Cantidad al usuario y enviarlos (writeInt)
                    // TODO: 3. Recibir confirmación (readBoolean) y mostrar mensaje
                }
            }
            // Notificar al servidor que cerramos la sesión
            out.writeObject("SALIR");
            out.flush();

        } catch (IOException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n--- TiendaTech ---");
        System.out.println("1. Ver productos");
        System.out.println("2. Realizar compra");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");
    }
}