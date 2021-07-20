package com.softvision.foodfactory.model.product;

import java.util.Arrays;
import java.util.Random;

public class ProductTypeDecider {

    public static ProductType getRandomProductType() {
        Random random = new Random();
        return Arrays.asList(ProductType.values()).get(random.nextInt(3));
    }
}
