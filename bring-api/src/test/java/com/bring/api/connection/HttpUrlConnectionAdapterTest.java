package com.bring.api.connection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.bring.api.exceptions.RequestFailedException;

public class HttpUrlConnectionAdapterTest {

    private BringConnection bringConnection;
    private final HttpURLConnection connectionMock = mock(HttpURLConnection.class);
    
    @Before
    public void setUp() throws IOException {
        bringConnection = new HttpUrlConnectionAdapter("test") {
            @Override
            protected HttpURLConnection openConnection(URL url) throws IOException {
                return connectionMock;
            }
        };
        when(connectionMock.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);
    }
    
    @Test
    public void shouldBeAbleToSetConnectionTimeout() {
        bringConnection.setConnectionTimeout(20000);
        assertEquals(20000, bringConnection.getConnectionTimeout());
        
        bringConnection.setReadTimeout(10000);
        assertEquals(10000, bringConnection.getReadTimeout());
    }
    
    @Test
    public void shouldSetUserAgentHeader() throws RequestFailedException {
        bringConnection.openInputStream("http://fraktguide.bring.no/fraktguide/products/SERVICEPAKKE/price.xml?weightInGrams=1500&from=7600&to=1407");
        verify(connectionMock).addRequestProperty("User-Agent", bringConnection.generateUserAgentString("test"));
    }
    
    @Test
    public void shouldBeAbleToSetCustomHeaders() throws RequestFailedException {
        Map<String,String> headers = new HashMap<String,String>();
        headers.put("my-first-header-name","my-first-header-value");
        headers.put("my-second-header-name","my-second-header-value");
        bringConnection.openInputStream("http://fraktguiden.bring.no",headers);
        verify(connectionMock).addRequestProperty("my-first-header-name","my-first-header-value");
        verify(connectionMock).addRequestProperty("my-second-header-name","my-second-header-value");
    }

}
