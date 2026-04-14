package org.example.service;

import java.util.ArrayList;
import java.util.List;
import org.example.model.Product;

public class StoreService {
    private final List<Product> products = new ArrayList<>();

    public void initializeProducts() {
        // TODO: cargar productos iniciales
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public Product findProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public boolean processPurchase(int productId, int quantity) {
        // TODO: validar stock y actualizar inventario
        return false;
    }
}

