package com.bring.api.utilities;


import javax.xml.ws.WebServiceException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * @author Mathias Bjerke
 */
public class FileUtilities {
	public static void saveInputStreamAsFile( InputStream in, String filename ) {
		OutputStream out = null;
		byte[] buffer;
		int length;

		try {
			out = new FileOutputStream( filename );
			buffer = new byte[1024];

			while((length = in.read(buffer))>0) {
				out.write( buffer, 0 ,length );
			}

		} catch( IOException e ) {
			throw new WebServiceException( "Could not save file", e );

		} finally {
			try {
				out.close();
				in.close();

			} catch( Exception e2 ) {
				/* Do nothing */
			}
		}
	}
}
