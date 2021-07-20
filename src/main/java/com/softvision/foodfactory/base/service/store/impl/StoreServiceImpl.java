package com.softvision.foodfactory.base.service.store.impl;

import com.softvision.foodfactory.base.service.store.StoreService;
import com.softvision.foodfactory.model.product.Product;

import java.util.Queue;

public class StoreServiceImpl implements StoreService {

    private Queue<Product> products;

    public StoreServiceImpl(Queue<Product> products) {
        this.products = products;
    }

    @Override
    public void put(Product product) {
        products.add(product);
    }

    @Override
    public Product take() {
        return products.poll();
    }

    @Override
    public void take(Product product) {

    }
}
