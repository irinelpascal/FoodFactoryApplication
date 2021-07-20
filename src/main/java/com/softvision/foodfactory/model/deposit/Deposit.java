package com.softvision.foodfactory.model.deposit;

import com.softvision.foodfactory.model.product.Product;

import java.util.Queue;

public class Deposit {

    private Queue<Product> products;

    public Queue<Product> getProducts() {
        return products;
    }

    public void setProducts(Queue<Product> products) {
        this.products = products;
    }
}
