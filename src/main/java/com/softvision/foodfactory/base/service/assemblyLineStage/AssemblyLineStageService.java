package com.softvision.foodfactory.base.service.assemblyLineStage;


import com.softvision.foodfactory.model.product.Product;

/**
 * This represents an assembly line stage of the factory. Implementations of this class should be thread-safe
 */
public interface AssemblyLineStageService {
    /**
     * Put the specified product to the assembly line to continue in the next stage.
     *
     * @param product
     */
    void putAfter(Product product);

    /**
     * Takes the next product available from the assembly line.
     *
     * @return
     */
    Product take();

    void take(Product product);

}
