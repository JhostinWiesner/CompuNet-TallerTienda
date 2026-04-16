package com.tiendatech.servidor;

import com.tiendatech.modelo.Producto;
import java.io.*;
import java.net.Socket;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class ClienteHandler implements Runnable {
    private Socket socket;

    public ClienteHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // Inicialización de flujos siguiendo el orden recomendado para evitar bloqueos
        try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
            
            boolean conectado = true;
            while (conectado) {
                // TODO: 1. Leer el comando del cliente (String)
                String comando = (String) in.readObject();
                if (comando.equals("LISTAR")){
                    List<Producto> productos = Inventario.getInstancia().obtenerListaProductos();
                    out.writeObject(productos);
                    out.flush();
                } else if (comando.startsWith("COMPRAR")) {
                    String[] partComando = comando.split("//");
                    int id = Integer.parseInt(partComando[1]);
                    int cantidad = Integer.parseInt(partComando[2]);
                    boolean exito = Integer.getInstancia().procesarCompra(id,cantidad);
                    String respuesta;
                    if (exito) {
                        respuesta = "EXITO: compra realizada correctamente";
                    } else {
                        respuesta = "ERROR: no hay suficiente stock o el producto no fue encontrado";

                    }
                    out.writeObject(respuesta);
                    out.flush();
                } else if (comando.equals("SALIR")) {
                    conectado=false;
                }
            }

        } catch (IOException e) {
            System.err.println("Error en la comunicación con el cliente: " + e.getMessage());
        } finally {
            cerrarConexion();
        }
    }

    private void cerrarConexion() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}