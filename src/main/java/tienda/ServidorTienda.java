package tienda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Servidor TCP de la tienda.
 * Protocolo (texto plano, una línea por mensaje):
 *   LISTAR                  -> devuelve todos los productos (id,nombre,precio,cantidad por línea)
 *   COMPRAR <id> <cantidad>  -> intenta realizar una compra
 *   SALIR                   -> cierra la conexión del cliente
 */
public class ServidorTienda {

    private static final int PUERTO = 9090;

    public static void main(String[] args) throws IOException {
        Tienda tienda = new Tienda();
        // Datos de ejemplo
        tienda.agregarProducto("Laptop", 2500000, 10);
        tienda.agregarProducto("Mouse", 45000, 50);
        tienda.agregarProducto("Teclado", 80000, 30);
        tienda.agregarProducto("Monitor", 900000, 15);

        System.out.println("Servidor Tienda escuchando en puerto " + PUERTO + "...");
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            while (true) {
                Socket cliente = serverSocket.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress());
                Thread hilo = new Thread(() -> manejarCliente(cliente, tienda));
                hilo.start();
            }
        }
    }

    private static void manejarCliente(Socket socket, Tienda tienda) {
        try (
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String linea;
            while ((linea = entrada.readLine()) != null) {
                String respuesta = procesarComando(linea.trim(), tienda);
                salida.println(respuesta);
                if (linea.trim().equalsIgnoreCase("SALIR")) {
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error con cliente: " + e.getMessage());
        } finally {
            try { socket.close(); } catch (IOException ignored) {}
            System.out.println("Cliente desconectado: " + socket.getInetAddress());
        }
    }

    static String procesarComando(String comando, Tienda tienda) {
        if (comando.equalsIgnoreCase("LISTAR")) {
            StringBuilder sb = new StringBuilder();
            for (Producto p : tienda.listarProductos()) {
                sb.append(p.toString()).append("\n");
            }
            return sb.toString().trim();
        }
        if (comando.toUpperCase().startsWith("COMPRAR")) {
            String[] partes = comando.split("\\s+");
            if (partes.length != 3) {
                return "ERROR: Uso correcto: COMPRAR <id> <cantidad>";
            }
            try {
                int id = Integer.parseInt(partes[1]);
                int cantidad = Integer.parseInt(partes[2]);
                return tienda.comprar(id, cantidad);
            } catch (NumberFormatException e) {
                return "ERROR: id y cantidad deben ser números enteros";
            }
        }
        if (comando.equalsIgnoreCase("SALIR")) {
            return "Hasta luego!";
        }
        return "ERROR: Comando desconocido. Comandos válidos: LISTAR, COMPRAR <id> <cantidad>, SALIR";
    }
}
