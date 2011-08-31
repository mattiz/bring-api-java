package com.bring.connection;

import java.io.InputStream;
import java.util.Map;

import com.bring.exceptions.RequestFailedException;

/**
 * Interface for abstracting the http client framework to use.
 */
public interface BringConnection {
    
    /**
     * Caller is responsible for closing the input stream.
     * 
     * @param url
     * @return
     * @throws com.bring.exceptions.RequestFailedException
     */
    public InputStream openInputStream(String url) throws RequestFailedException;
    
    /**
     * Caller is responsible for closing the input stream.
     * 
     * @param url
     * @param headers
     * @return
     * @throws com.bring.exceptions.RequestFailedException
     */
    public InputStream openInputStream(String url, Map<String, String> headers) throws RequestFailedException;

    public int getConnectionTimeout();
    public void setConnectionTimeout(int connectionTimeout);
    public int getReadTimeout();
    public void setReadTimeout(int readTimeout);
    
    /**
     * @param identifier URL for site (or other descriptive identifier)
     * @return User agent string
     */
    public String generateUserAgentString(String identifier);
}