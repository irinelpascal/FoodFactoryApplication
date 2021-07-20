package com.softvision.foodfactory.base.service.oven.impl;

import com.softvision.foodfactory.base.service.oven.OvenService;
import com.softvision.foodfactory.exception.CapacityExceededException;
import com.softvision.foodfactory.model.product.Product;

import java.time.Duration;
import java.util.Queue;
import java.util.UUID;

public class OvenServiceImpl implements OvenService {

    private final Queue<Product> products;

    public OvenServiceImpl(Queue<Product> products) {
        this.products = products;
    }

    @Override
    public double size() {
        return 0.6;
    }

    @Override
    public void put(Product product) throws CapacityExceededException {
        /*
         * suppose that the size of the product added to the others is bigger than 0.6 square meters we'll
         * throw an exception of type CapacityExceededException
         */
        products.add(product);
    }

    @Override
    public void take(Product product) {
        products.remove(product);
    }

    @Override
    public void turnOn(UUID ovenId) {

    }

    @Override
    public void turnOn(Duration duration) {

    }

    @Override
    public void turnOff(UUID ovenId) {

    }
}
