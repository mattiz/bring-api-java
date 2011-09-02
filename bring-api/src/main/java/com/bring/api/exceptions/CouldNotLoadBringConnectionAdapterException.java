package com.bring.api.exceptions;

public class CouldNotLoadBringConnectionAdapterException extends RuntimeException {

    public CouldNotLoadBringConnectionAdapterException(Exception e) {
        super(e);
    }
}
