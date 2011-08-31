package com.bring.exceptions;

public class MissingParameterException extends RuntimeException {

    public MissingParameterException(String string) {
        super(string);
    }
    
}
