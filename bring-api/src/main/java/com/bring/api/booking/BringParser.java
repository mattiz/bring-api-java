package com.bring.api.booking;


import com.bring.api.exceptions.UnmarshalException;
import javax.xml.bind.*;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;


/**
 * Response element parser that uses JAXB.
 * @param <T> The type of the input / result object
 */
public class BringParser<T> {
    
    private JAXBContext context;
    
    public BringParser( Class<T> typeParameterClass ){
        try {
			context = JAXBContext.newInstance(typeParameterClass);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


	public <T> T unmarshal( InputStream inputStream) throws UnmarshalException {
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            T p = (T) unmarshaller.unmarshal(inputStream);
            return p;
        }
        catch (JAXBException e) {
            throw new UnmarshalException(e);
        }
    }


	public byte[] marshal(Object object) throws MarshalException {
		ByteArrayOutputStream out;
		Marshaller marshaller;

        try {
			out = new ByteArrayOutputStream();
			marshaller = context.createMarshaller();
			
			//marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			//marshaller.marshal( object, System.out );

            marshaller.marshal( object, out );

			return out.toByteArray();

        } catch (JAXBException e) {
            throw new MarshalException(e);
        }
    }
}