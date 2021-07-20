package com.softvision.foodfactory.factory;

import com.softvision.foodfactory.model.product.*;

import java.security.InvalidParameterException;

/**
 * Let's suppose that these are the only products which are being processed
 */
public class ProductFactory {

    public static Product getProduct(ProductType productTYpe) {
        switch (productTYpe) {
            case WHITE:
                return new WhiteBread(0.05);
            case BLACK:
                return new BlackBread(0.04);
            case CORN:
                return new CornBread(0.06);
            default:
                throw new InvalidParameterException("There is no such product type");
        }
    }
}
