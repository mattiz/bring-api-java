package com.bring.connection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.HttpGet;
import org.junit.Before;
import org.junit.Test;

import com.bring.exceptions.RequestFailedException;

public class HttpClient4AdapterTest {
    private BringConnection bringConn;
    private HttpGet httpGetMock;
    
    @Before
    public void setUp() throws IOException {
        httpGetMock = mock(HttpGet.class);
        bringConn = new HttpClient4Adapter("test") {
            @Override
            protected HttpGet createHttpGet(URI uri) throws java.net.URISyntaxException {
                return httpGetMock;
            };
        };
    }
    
    @Test
    public void shouldBeAbleToSetCustomHeaders() {
        Map<String,String> headers = new HashMap<String,String>();
        headers.put("my-first-header-name","my-first-header-value");
        headers.put("my-second-header-name","my-second-header-value");
        try {
            bringConn.openInputStream("http://fraktguiden.bring.no",headers);
        } catch (RequestFailedException e) {}
          catch (NullPointerException e) {}
          catch (IllegalArgumentException e) {}
        finally {
            verify(httpGetMock).addHeader("User-Agent",bringConn.generateUserAgentString("test"));
            verify(httpGetMock).addHeader("my-first-header-name","my-first-header-value");
            verify(httpGetMock).addHeader("my-second-header-name","my-second-header-value");
        }
    }
}