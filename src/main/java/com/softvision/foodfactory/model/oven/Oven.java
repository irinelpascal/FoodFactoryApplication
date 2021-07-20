package com.softvision.foodfactory.model.oven;

import com.softvision.foodfactory.model.product.Product;

import java.time.Duration;
import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;

public class Oven {

    private final UUID id = UUID.randomUUID();
    /**
     * in square meters
     */
    private final double size = 0.6; // square meter, we assume that every oven has the same size
    private boolean isOvenOn;
    private boolean isOvenFull;
    private final Duration cookTime = Duration.ofMillis(5000);
    private final Queue<Product> incomingProducts = new LinkedList<>(); // which need to get cooked
    private final Queue<Product> outgoingProducts = new LinkedList<>(); // already cooked and ready for delivery

    public Oven() {
    }

    public void setOvenOn(boolean ovenOn) {
        isOvenOn = ovenOn;
    }

    public void setOvenFull(boolean ovenFull) {
        isOvenFull = ovenFull;
    }

    public Queue<Product> getIncomingProducts() {
        return incomingProducts;
    }

    public UUID getId() {
        return id;
    }

    public void addProductInOven(Product product) {
        incomingProducts.add(product);
    }

    @Override
    public String toString() {
        return "Oven{" +
                "id=" + id +
                ", size=" + size +
                ", isOvenOn=" + isOvenOn +
                ", isOvenFull=" + isOvenFull +
                ", cookTime=" + cookTime +
                ", productsGettingIn=" + incomingProducts +
                ", productsGettingOut=" + outgoingProducts +
                '}';
    }
}
