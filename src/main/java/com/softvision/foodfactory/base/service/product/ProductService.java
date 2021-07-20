package com.softvision.foodfactory.base.service.product;

import java.time.Duration;
import java.util.UUID;

/**
 * Implementations of this class should take care of overriding the necessary methods of the Object class to allow
 * for the use of Collections in the different implementations of Oven and Store.
 */
public interface ProductService {
    /**
     * The size that this product physically occupies in cm2
     *
     * @return
     */
    double size(UUID id);

    /**
     * This is the duration that this product should be cooked for.
     */
    Duration cookTime();
}
