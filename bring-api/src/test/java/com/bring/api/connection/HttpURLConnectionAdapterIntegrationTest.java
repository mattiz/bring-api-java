package com.bring.api.connection;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import com.bring.api.exceptions.RequestFailedException;

public class HttpURLConnectionAdapterIntegrationTest {
    
    private BringConnection bringConnection;
    private HttpURLConnection httpUrlConnection;
    
    @Before
    public void setUp() {
        bringConnection = new HttpUrlConnectionAdapter("test");
    }

    @Test
    public void shouldConnectToValidSearchUrl() throws RequestFailedException {
        InputStream is = bringConnection
                .openInputStream(
                        "http://fraktguide.bring.no/fraktguide/products/SERVICEPAKKE/price.xml?weightInGrams=1500&from=7600&to=1407");
        assertNotNull(is);
    }
    
    @Test
    public void shouldBeAbleToConnectUsingSSL() throws IOException {
        try {
            BringConnection connection = new HttpUrlConnectionAdapter("test") {
                @Override
                protected HttpURLConnection openConnection(URL url) throws IOException {
                    httpUrlConnection = super.openConnection(url);
                    return httpUrlConnection;
                }
            };
            connection.openInputStream("https://encrypted.google.com/");
            assertEquals(HttpURLConnection.HTTP_OK, httpUrlConnection.getResponseCode());
        }
        catch (RequestFailedException e) {
            System.out.println(e.getMessage());
        }
    }
}
