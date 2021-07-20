package com.softvision.foodfactory.service.impl;

import com.softvision.foodfactory.factory.ProductFactory;
import com.softvision.foodfactory.model.product.Product;
import com.softvision.foodfactory.model.product.ProductType;
import com.softvision.foodfactory.service.IFoodMakerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductMakerServiceImpl implements IFoodMakerService {

    private final List<Product> products = new ArrayList<>();

    @Override
    public Product createProduct(ProductType productType) {
        Product product = ProductFactory.getProduct(productType);
        addProduct(product);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public Optional<Product> getProductById(UUID id) {
        return getAllProducts()
                .stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public void addProduct(Product product) {
        products.add(product);
    }
}
