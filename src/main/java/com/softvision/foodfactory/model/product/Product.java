package com.softvision.foodfactory.model.product;

import java.util.UUID;

public abstract class Product {

    private final UUID id = UUID.randomUUID();

    public UUID getId() {
        return id;
    }
}
