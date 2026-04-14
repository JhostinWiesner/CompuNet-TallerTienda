package tienda;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Tienda {

    private final List<Producto> inventario = new ArrayList<>();
    private int nextId = 1;

    public void agregarProducto(String nombre, double precio, int cantidad) {
        inventario.add(new Producto(nextId++, nombre, precio, cantidad));
    }

    public List<Producto> listarProductos() {
        return new ArrayList<>(inventario);
    }

    public Optional<Producto> buscarPorId(int id) {
        return inventario.stream().filter(p -> p.getId() == id).findFirst();
    }

    public String comprar(int id, int cantidad) {
        Optional<Producto> opt = buscarPorId(id);
        if (opt.isEmpty()) {
            return "ERROR: Producto no encontrado";
        }
        Producto p = opt.get();
        if (p.getCantidad() < cantidad) {
            return "ERROR: Stock insuficiente (" + p.getCantidad() + " disponibles)";
        }
        p.setCantidad(p.getCantidad() - cantidad);
        double total = p.getPrecio() * cantidad;
        return String.format("OK: Compra exitosa. Total: $%.2f", total);
    }
}
