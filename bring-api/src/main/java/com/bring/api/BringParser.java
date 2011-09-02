package com.bring.api;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.bring.api.exceptions.UnmarshalException;

/**
 * Response element parser that uses JAXB.
 * @param <T> The type of the input / result object
 */
public class BringParser<T> {
    
    private JAXBContext context;
    
    public BringParser(Class<T> typeParameterClass){
        try {
			context = JAXBContext.newInstance(typeParameterClass);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public T unmarshal(InputStream inputStream) throws UnmarshalException {
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            T p = (T) unmarshaller.unmarshal(inputStream);
            return p;
        }
        catch (JAXBException e) {
            throw new UnmarshalException(e);
        }
    }
}