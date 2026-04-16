package com.tiendatech.cliente;

import com.tiendatech.modelo.Producto;
import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class ClienteTienda {
    private static final String HOST = "localhost"; 
    private static final int PUERTO = 5000;

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

                    @SuppressWarnings("unchecked")
                    List<Producto> productos = (List<Producto>) in.readObject();

                    if (productos == null || productos.isEmpty()) {
                        System.out.println("No hay productos disponibles en este momento.");
                    } else {
                        System.out.println("\n--- Productos disponibles ---");
                        for (Producto p : productos) {
                            System.out.println(p);
                        }
                    }
                }
                else if (opcion.equals("2")) {
                   // out.writeObject("comprar");

                    int id = leerEntero(sc, "Coloque el ID del producto: ");
                    int cantidad = leerEntero(sc, "Coloque la cantidad: ");

                 //   out.writeInt(id);
                    //out.writeInt(cantidad);
                    //out.flush();
                        String comando = "COMPRAR|"+id+"|"+ cantidad;

                    out.writeObject(comando);
                    out.flush();
                      //
                    String respuesta = (String) in.readObject();
                    if(respuesta.startsWith("EXITO")){
                        System.out.println(respuesta);
                    } else {
                        System.out.println(respuesta);
                    }

                } else if (!opcion.equals("3")) {
                    System.out.println("Opción incorrecta, intente nuevamente.");
                }
            }
            // Notificar al servidor que cerramos la sesión
            out.writeObject("SALIR");
            out.flush();

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n--- tiendaTech ---");
        System.out.println("1. Ver productos");
        System.out.println("2. Realizar compra");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opcion: ");
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