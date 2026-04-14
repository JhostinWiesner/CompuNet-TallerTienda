package tienda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Cliente TCP de la tienda.
 * Uso: java -cp build/libs/CompuNet-TallerTienda.jar tienda.ClienteTienda [host] [puerto]
 */
public class ClienteTienda {

    private static final String HOST_DEFAULT = "localhost";
    private static final int PUERTO_DEFAULT = 9090;

    public static void main(String[] args) throws IOException {
        String host = args.length > 0 ? args[0] : HOST_DEFAULT;
        int puerto = args.length > 1 ? Integer.parseInt(args[1]) : PUERTO_DEFAULT;

        try (
            Socket socket = new Socket(host, puerto);
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Conectado al servidor " + host + ":" + puerto);
            System.out.println("Comandos: LISTAR | COMPRAR <id> <cantidad> | SALIR");
            System.out.println("--------------------------------------------------");

            String linea;
            while ((linea = teclado.readLine()) != null) {
                salida.println(linea);
                // Leer respuesta (puede ser multilínea; el servidor separa bloques con líneas vacías)
                String respuesta;
                while ((respuesta = entrada.readLine()) != null) {
                    System.out.println(respuesta);
                    if (!entrada.ready()) break;
                }
                if (linea.trim().equalsIgnoreCase("SALIR")) {
                    break;
                }
            }
        }
        System.out.println("Conexión cerrada.");
    }
}
