package com.bring.connection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Before;
import org.junit.Test;

import com.bring.exceptions.RequestFailedException;

public class HttpClient3AdapterTest {
    private BringConnection bringConn;
    private HttpMethod getMethod;
    
    @Before
    public void setUp() throws IOException {
        getMethod = mock(GetMethod.class);
        bringConn = new HttpClient3Adapter("test") {
            @Override
            protected HttpMethod createGetMethod(String url) {
                return getMethod;
            };
        };
    }
    
    @Test
    public void shouldBeAbleToSetCustomHeaders() throws Throwable {
        Map<String,String> headers = new HashMap<String,String>();
        headers.put("my-first-header-name","my-first-header-value");
        headers.put("my-second-header-name","my-second-header-value");
        try {
            bringConn.openInputStream("http://fraktguiden.bring.no",headers);
        } catch (RequestFailedException e) {
           throw e.getCause();
        }
        catch (NullPointerException e){
            
        }
        finally {
            verify(getMethod).addRequestHeader("User-Agent",bringConn.generateUserAgentString("test"));
            verify(getMethod).addRequestHeader("my-first-header-name","my-first-header-value");
            verify(getMethod).addRequestHeader("my-second-header-name","my-second-header-value");
        }
    }
}
