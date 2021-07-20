package com.softvision.foodfactory.base.service.product.impl;

import com.softvision.foodfactory.base.service.product.ProductService;
import com.softvision.foodfactory.exception.ResourceNotFoundException;
import com.softvision.foodfactory.model.product.BlackBread;
import com.softvision.foodfactory.model.product.CornBread;
import com.softvision.foodfactory.model.product.Product;
import com.softvision.foodfactory.model.product.WhiteBread;
import com.softvision.foodfactory.service.IFoodMakerService;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = Logger.getLogger(ProductServiceImpl.class.getName());
    private final IFoodMakerService iFoodMakerService;

    public ProductServiceImpl(IFoodMakerService iFoodMakerService) {
        this.iFoodMakerService = iFoodMakerService;
    }

    @Override
    public double size(UUID productId) {
        Optional<Product> productById = Optional.ofNullable(iFoodMakerService.getProductById(productId)
                .orElseThrow(ResourceNotFoundException::new));
        if (productById.isPresent()) {
            Product product = productById.get();
            if (product instanceof WhiteBread) {
                return ((WhiteBread) product).getSize();
            } else if (product instanceof BlackBread) {
                return ((BlackBread) product).getSize();
            } else {
                return ((CornBread) product).getSize();
            }
        }
        LOGGER.info("Product not found");
        return 0.00;
    }

    @Override
    public Duration cookTime() {
        return Duration.ofMillis(900000L); // 15 minutes for all bread products;
    }
}
