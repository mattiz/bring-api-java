package com.bring.api.exceptions;

public class IllegalDimensionException extends RuntimeException {
    public IllegalDimensionException() {
        super();
    }
    
    public IllegalDimensionException(String message) {
        super(message);
    }
}