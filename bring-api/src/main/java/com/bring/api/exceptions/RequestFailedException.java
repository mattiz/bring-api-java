package com.bring.api.exceptions;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class RequestFailedException extends Exception {
    private String message;
    private int responseCode;
    
    public RequestFailedException(String message, int responseCode) {
        super();
        this.message = message;
        this.responseCode = responseCode;
    }
    
    public RequestFailedException() {
        super();
    }

    public RequestFailedException(Throwable exception) {
        super(exception);
    }

    @Override
    public String getMessage() {
        return message;
    }
    
    public int getResponseCode() {
        return responseCode;
    }
    
    public static String readErrorStream(InputStream errorStream) throws IOException {
        InputStreamReader reader;
        reader = new InputStreamReader(new BufferedInputStream(errorStream));
        String message = "";
        int c;
        while ( ((c = reader.read()) != -1) ){
            message += (char) c;
        }
        return message;
    }
}