package com.softvision.foodfactory.model.assemblyLineStage;

import com.softvision.foodfactory.model.product.Product;

import java.util.LinkedList;
import java.util.Queue;

public class AssemblyLineStage {

    private boolean isPowerOn;
    private boolean isAssemblyLineFull;
    private final Queue<Product> incomingProducts = new LinkedList<>();
    private final Queue<Product> outgoingProducts = new LinkedList<>(); // cooked already

    public AssemblyLineStage() {
    }

    /**
     * 10 slots per conveyor line available for all the assembly stage lines
     * @return the number of slots per conveyor line
     */
    public final int getMaximumProductsOnStageLine() {
        return 10;
    }

    public void setPowerOn(boolean powerOn) {
        isPowerOn = powerOn;
    }
}
