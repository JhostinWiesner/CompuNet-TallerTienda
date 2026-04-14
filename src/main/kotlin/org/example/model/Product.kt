package org.example.model

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    var quantityAvailable: Int
)

