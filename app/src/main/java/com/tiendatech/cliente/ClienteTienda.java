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
                    out.writeObject("LISTAR");
                    out.flush();

                    List<Producto> productos = (List<Producto>) in.readObject();

                    if (productos == null || productos.isEmpty()) {
                        System.out.println("no hay productos disponibles en este momento.");
                    } else {
                        System.out.println("\n--- productos disponibles ---");
                        for (Producto p : productos) {
                            System.out.println(p);
                        }
                    }
                }
                else if (opcion.equals("2")) {
                    out.writeObject("comprar");

                    int id = leerEntero(sc, "coloque el ID del producto: ");
                    int cantidad = leerEntero(sc, "coloque la cantidad: ");

                    out.writeInt(id);
                    out.writeInt(cantidad);
                    out.flush();

                    boolean compraExitosa = in.readBoolean();
                    if (compraExitosa) {
                        System.out.println("compra relizada con exito");
                    } else {
                        System.out.println("no se pudo completar la compra ");
                    }
                } else if (!opcion.equals("3")) {
                    System.out.println("opcion incorrecta, intente nuevamente ");
                }
            }
            // Notificar al servidor que cerramos la sesión
            out.writeObject("SALIR");
            out.flush();

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("error de conexión: " + e.getMessage());
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n--- tiendaTech ---");
        System.out.println("1. Ver productos");
        System.out.println("2. Realizar compra");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = sc.nextLine();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Debe ingresar un numero entero.");
            }
        }
    }
}