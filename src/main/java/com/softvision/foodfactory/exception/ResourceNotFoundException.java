package com.softvision.foodfactory.exception;

public class ResourceNotFoundException extends RuntimeException {

    public final static String MESSAGE = "Resource not found";

    public ResourceNotFoundException() {
        super(MESSAGE);
    }
}
