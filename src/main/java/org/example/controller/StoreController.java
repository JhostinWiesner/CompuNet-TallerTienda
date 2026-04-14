package org.example.controller;

import java.util.List;
import org.example.model.Product;
import org.example.service.StoreService;

public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    public void loadInitialData() {
        storeService.initializeProducts();
    }

    public List<Product> listProducts() {
        return storeService.getProducts();
    }

    public Product searchProduct(int id) {
        return storeService.findProductById(id);
    }

    public boolean buyProduct(int productId, int quantity) {
        return storeService.processPurchase(productId, quantity);
    }
}

