package com.softvision.foodfactory.base.service.assemblyLineStage.impl;

import com.softvision.foodfactory.base.service.assemblyLineStage.AssemblyLineStageService;
import com.softvision.foodfactory.model.product.Product;

import java.util.Queue;

public class AssemblyLineStageServiceImpl implements AssemblyLineStageService {

    private Queue<Product> productsToGetCooked;
    private Queue<Product> cookedProducts;

    public AssemblyLineStageServiceImpl(Queue<Product> productsToGetCooked) {
        this.productsToGetCooked = productsToGetCooked;
    }

    public AssemblyLineStageServiceImpl() {
    }

    @Override
    public void putAfter(Product product) {
        productsToGetCooked.add(product);
    }

    @Override
    public Product take() {
        return productsToGetCooked.poll();
    }

    @Override
    public void take(Product product) {
        productsToGetCooked.poll();
    }

    public Queue<Product> getProductsToGetCooked() {
        return productsToGetCooked;
    }

    public Queue<Product> getCookedProducts() {
        return cookedProducts;
    }

    public void setProductsToGetCooked(Queue<Product> productsToGetCooked) {
        this.productsToGetCooked = productsToGetCooked;
    }

    public void setCookedProducts(Queue<Product> cookedProducts) {
        this.cookedProducts = cookedProducts;
    }
}
