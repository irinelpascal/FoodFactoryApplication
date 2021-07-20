package com.softvision.foodfactory.model.product;

public enum ProductType {

    WHITE("white"), BLACK("black"), CORN("corn");

    private final String value;

    ProductType(String value) {
        this.value = value;
    }
}
