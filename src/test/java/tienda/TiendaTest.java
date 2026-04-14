package tienda;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TiendaTest {

    private Tienda tienda;

    @BeforeEach
    void setUp() {
        tienda = new Tienda();
        tienda.agregarProducto("Laptop", 2500000, 10);
        tienda.agregarProducto("Mouse", 45000, 5);
    }

    @Test
    void listarProductosDevuelveTodos() {
        List<Producto> productos = tienda.listarProductos();
        assertEquals(2, productos.size());
    }

    @Test
    void compraExitosaReduceStock() {
        String resultado = tienda.comprar(1, 2);
        assertTrue(resultado.startsWith("OK"));
        assertEquals(8, tienda.buscarPorId(1).get().getCantidad());
    }

    @Test
    void compraConStockInsuficienteFalla() {
        String resultado = tienda.comprar(2, 100);
        assertTrue(resultado.startsWith("ERROR: Stock insuficiente"));
    }

    @Test
    void compraProductoInexistenteFalla() {
        String resultado = tienda.comprar(99, 1);
        assertTrue(resultado.startsWith("ERROR: Producto no encontrado"));
    }
}
