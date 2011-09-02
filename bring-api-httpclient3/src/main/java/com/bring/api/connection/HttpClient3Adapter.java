package com.bring.api.connection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import com.bring.api.exceptions.RequestFailedException;

public class HttpClient3Adapter extends AbstractBringConnection{
    private String identifier;
    private HttpClient httpClient;

    public HttpClient3Adapter(String identifier) {
        this(identifier, new HttpClient());
    }
    
    public HttpClient3Adapter(String identifier, HttpClient httpClient) {
        this.identifier = identifier;
        this.httpClient = httpClient;
    }
    
    public InputStream openInputStream(String url) throws RequestFailedException {
        return openInputStream(url,null);
    }
    
    public InputStream openInputStream(String url, Map<String, String> additionalHeaders) throws RequestFailedException {
        InputStream inputStream = null;
        try {
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(connectionTimeout);
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(readTimeout);
            HttpMethod getMethod = createGetMethod(url);
            addHeaders(getMethod, additionalHeaders);
            httpClient.executeMethod(getMethod);
            throwFailedRequestExceptionOnUnwantedResponseCode(getMethod);
            inputStream = getMethod.getResponseBodyAsStream();
        }
        catch (IOException e) {
            throw new RequestFailedException(e);
        }
        catch (IllegalArgumentException e) {
            throw new RequestFailedException(e);
        }
        return inputStream;
    }

    protected HttpMethod createGetMethod(String url) {
        return new GetMethod(url);
    }

    private void addHeaders(HttpMethod httpGet, Map<String, String> additionalHeaders) {
        httpGet.addRequestHeader("User-Agent", generateUserAgentString(identifier));
        if (additionalHeaders != null) {
            for (String key : additionalHeaders.keySet()) {
                httpGet.addRequestHeader(key, additionalHeaders.get(key));
            }
        }
    }

    private void throwFailedRequestExceptionOnUnwantedResponseCode(HttpMethod getMethod) throws RequestFailedException, IOException {
        InputStream errorStream = null;
        try {
            int responseCode = getMethod.getStatusLine().getStatusCode();
            if (isUnwantedResponseCode(responseCode)) {
                errorStream = getMethod.getResponseBodyAsStream();
                throw new RequestFailedException(RequestFailedException.readErrorStream(errorStream), responseCode);
            }
        }
        finally {
            if (errorStream != null) {
                errorStream.close();
            }
        }
    }
}
