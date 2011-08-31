package com.bring.connection;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.bring.exceptions.RequestFailedException;

public class HttpClient4AdapterIntegrationTest {
    @Before
    public void setUp() {
        
    }
    
    @Test
    public void shouldBeAbleToOpenInputStreamToFraktguiden() throws RequestFailedException {
        String url = "http://fraktguide.bring.no/fraktguide/products/SERVICEPAKKE/price.xml?weightInGrams=1500&from=7600&to=1407";
        HttpClient4Adapter httpClient = new HttpClient4Adapter("test");
        assertEquals(InputStream.class, httpClient.openInputStream(url).getClass().getSuperclass());
    }
    
    @Test
    public void shouldBeAbleToOpenInputStreamWithCustomHeaders() throws RequestFailedException {
        String url = "http://fraktguide.bring.no/fraktguide/products/SERVICEPAKKE/price.xml?weightInGrams=1500&from=7600&to=1407";
        HttpClient4Adapter httpClient = new HttpClient4Adapter("test");
        Map<String,String> headers = new HashMap<String,String>();
        headers.put("my-first-header-name","my-first-header-value");
        headers.put("my-second-header-name","my-second-header-value");
        assertEquals(InputStream.class, httpClient.openInputStream(url, headers).getClass().getSuperclass());
    }
    
    @Test(expected = RequestFailedException.class)
    public void shouldThrowFailedRequestExceptionOnIllegalUrl() throws RequestFailedException {
        String url = ":-/htraktguide.bring.no";
        HttpClient4Adapter httpClient = new HttpClient4Adapter("test");
        httpClient.openInputStream(url);
    }
    
    @Test(expected = RequestFailedException.class)
    public void shouldThrowFailedRequestExceptionOnFailedFraktguidenRequest() throws RequestFailedException {
        String url = "http://fraktguide.bring.no/fraktguide/products/price.xml?from=1407&to=7050&weightInGrams0=324&edi=true&pid=abc123";
        HttpClient4Adapter httpClient = new HttpClient4Adapter("test");
        httpClient.openInputStream(url);
    }
    
    @Test
    public void shouldBeAbleToReadErrorMessageOnBadRequest() {
        try {
            String url = "http://fraktguide.bring.no/fraktguide/products/price.xml?from=1407&to=7050&weightInGrams0=324&edi=true&pid=abc123";
            HttpClient4Adapter httpClient = new HttpClient4Adapter("test");
            httpClient.openInputStream(url);
        } catch (RequestFailedException e) {
            assertTrue(e.getMessage().startsWith("FG_INPUT_025"));
        }
    }
}