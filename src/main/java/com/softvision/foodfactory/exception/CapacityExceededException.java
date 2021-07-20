package com.softvision.foodfactory.exception;

public class CapacityExceededException extends Throwable {

    public final static String MESSAGE = "Oven capacity exceeded";

    public CapacityExceededException() {
        super(MESSAGE);
    }
}
