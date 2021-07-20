package com.softvision.foodfactory.model.product;

public class CornBread extends Product {

    private boolean isCooked;
    /**
     * in square meters
     */
    private final double size;

    public CornBread(double size) {
        this.size = size;
    }

    public double getSize() {
        return size;
    }

    public void setCooked(boolean cooked) {
        isCooked = cooked;
    }

    @Override
    public String toString() {
        return "CornBread{" +
                "isCooked=" + isCooked +
                ", size=" + size +
                '}';
    }
}
