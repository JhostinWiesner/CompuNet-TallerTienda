package org.example.service

import org.example.model.Product

class StoreService {
    private val products = mutableListOf<Product>()

    fun initializeProducts() {
        // TODO: cargar productos iniciales
    }

    fun getProducts(): List<Product> = products.toList()

    fun findProductById(id: Int): Product? = products.find { it.id == id }

    fun processPurchase(productId: Int, quantity: Int): Boolean {
        // TODO: validar stock y actualizar inventario
        return false
    }
}

