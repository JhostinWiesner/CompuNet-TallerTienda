package tienda;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServidorTiendaTest {

    private Tienda tienda;

    @BeforeEach
    void setUp() {
        tienda = new Tienda();
        tienda.agregarProducto("Laptop", 2500000, 10);
        tienda.agregarProducto("Mouse", 45000, 5);
    }

    @Test
    void comandoListarDevuelveProductos() {
        String resp = ServidorTienda.procesarComando("LISTAR", tienda);
        assertTrue(resp.contains("Laptop"));
        assertTrue(resp.contains("Mouse"));
    }

    @Test
    void comandoComprarValido() {
        String resp = ServidorTienda.procesarComando("COMPRAR 1 2", tienda);
        assertTrue(resp.startsWith("OK"));
    }

    @Test
    void comandoComprarFormatoIncorrecto() {
        String resp = ServidorTienda.procesarComando("COMPRAR 1", tienda);
        assertTrue(resp.startsWith("ERROR"));
    }

    @Test
    void comandoDesconocidoRetornaError() {
        String resp = ServidorTienda.procesarComando("INVENTAR", tienda);
        assertTrue(resp.startsWith("ERROR: Comando desconocido"));
    }

    @Test
    void comandoSalirRetornaDespedida() {
        String resp = ServidorTienda.procesarComando("SALIR", tienda);
        assertEquals("Hasta luego!", resp);
    }
}
