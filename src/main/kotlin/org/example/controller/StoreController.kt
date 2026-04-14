package org.example.controller

import org.example.model.Product
import org.example.service.StoreService

class StoreController(
    private val storeService: StoreService
) {
    fun loadInitialData() {
        storeService.initializeProducts()
    }

    fun listProducts(): List<Product> = storeService.getProducts()

    fun searchProduct(id: Int): Product? = storeService.findProductById(id)

    fun buyProduct(productId: Int, quantity: Int): Boolean =
        storeService.processPurchase(productId, quantity)
}

