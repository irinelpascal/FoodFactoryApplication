package com.softvision.foodfactory;

import com.softvision.foodfactory.processor.FoodFactoryProcessor;

public class FoodFactoryApplication {

    public static void main(String[] args) {
        new FoodFactoryProcessor().startProcess();
    }
}
