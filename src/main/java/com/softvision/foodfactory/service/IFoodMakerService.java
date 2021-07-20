package com.softvision.foodfactory.service;

import com.softvision.foodfactory.model.product.Product;
import com.softvision.foodfactory.model.product.ProductType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IFoodMakerService {
    Product createProduct(ProductType productType);
    List<Product> getAllProducts();
    Optional<Product> getProductById(UUID productId);
}
