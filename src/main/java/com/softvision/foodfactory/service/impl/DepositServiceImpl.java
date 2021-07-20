package com.softvision.foodfactory.service.impl;

import com.softvision.foodfactory.model.deposit.Deposit;
import com.softvision.foodfactory.model.product.Product;
import com.softvision.foodfactory.model.product.ProductTypeDecider;
import com.softvision.foodfactory.service.IDepositService;
import com.softvision.foodfactory.service.IFoodMakerService;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.stream.IntStream;

public class DepositServiceImpl implements IDepositService {

    private final IFoodMakerService foodMakerService;

    public DepositServiceImpl(IFoodMakerService foodMakerService) {
        this.foodMakerService = foodMakerService;
    }

    @Override
    public Deposit getDeposit() {
        Deposit deposit = new Deposit();
        Queue<Product> products = new LinkedList<>();
        int productsToGetCreatedPerStore = new Random().nextInt(51 - 25 + 1) + 25; // [25, 50]
        IntStream.range(0, productsToGetCreatedPerStore)
                .forEach(productCounter -> {
                    Product product = foodMakerService.createProduct(ProductTypeDecider.getRandomProductType());
                    products.add(product);
                });
        deposit.setProducts(products);
        return deposit;
    }
}
