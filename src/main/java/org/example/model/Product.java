package org.example.model;

import java.util.Objects;

public class Product {
    private final int id;
    private final String name;
    private final double price;
    private int quantityAvailable;

    public Product(int id, String name, double price, int quantityAvailable) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product product)) {
            return false;
        }
        return id == product.id
            && Double.compare(product.price, price) == 0
            && quantityAvailable == product.quantityAvailable
            && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, quantityAvailable);
    }

    @Override
    public String toString() {
        return "Product{"
            + "id=" + id
            + ", name='" + name + '\''
            + ", price=" + price
            + ", quantityAvailable=" + quantityAvailable
            + '}';
    }
}

