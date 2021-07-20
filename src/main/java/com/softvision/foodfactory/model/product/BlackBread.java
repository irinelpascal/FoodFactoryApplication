package com.softvision.foodfactory.model.product;

public class BlackBread extends Product {
    /**
     * in square meters
     */
    private final double size;
    private boolean isCooked;

    public BlackBread(double size) {
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
        return "BlackBread{" +
                "size=" + size +
                ", isCooked=" + isCooked +
                '}';
    }
}
