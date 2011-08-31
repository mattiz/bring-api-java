package com.bring.exceptions;

import java.io.IOException;

import javax.xml.bind.JAXBException;

public class UnmarshalException extends IOException{
    
    public UnmarshalException(JAXBException jaxbException){
        super(jaxbException);
    }
}
