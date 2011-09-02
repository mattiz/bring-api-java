package com.bring.api.connection;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;

import com.bring.api.exceptions.RequestFailedException;

public class HttpClient4Adapter extends AbstractBringConnection {
    private String identifier;
    private HttpClient httpClient;

    public HttpClient4Adapter(String identifier) {
        this(identifier, new DefaultHttpClient());
    }

    public HttpClient4Adapter(String identifier, HttpClient httpClient) {
        this.identifier = identifier;
        this.httpClient = httpClient;
    }
    
    public InputStream openInputStream(String url) throws RequestFailedException {
        return openInputStream(url,null);
    }
    
    public InputStream openInputStream(String url, Map<String, String> additionalHeaders) throws RequestFailedException {
        InputStream inputStream = null;
        try {
            HttpGet httpGet = createHttpGet(new URI(url));
            addHeaders(httpGet, additionalHeaders);
            HttpConnectionParams.setConnectionTimeout(httpGet.getParams(), connectionTimeout);
            HttpConnectionParams.setSoTimeout(httpGet.getParams(), readTimeout);
            HttpResponse response = httpClient.execute(httpGet);
            throwFailedRequestExceptionOnUnwantedResponseCode(response);
            inputStream = response.getEntity().getContent();
        }
        catch (URISyntaxException e) {
            throw new RequestFailedException(e);
        }
        catch (ClientProtocolException e) {
            throw new RequestFailedException(e);
        }
        catch (IOException e) {
            throw new RequestFailedException(e);
        }
        return inputStream;
    }

    protected HttpGet createHttpGet(URI uri) throws URISyntaxException {
        return new HttpGet(uri);
    }

    private void addHeaders(HttpGet httpGet, Map<String, String> additionalHeaders) {
        httpGet.addHeader("User-Agent", generateUserAgentString(identifier));
        if (additionalHeaders != null) {
            for (String key : additionalHeaders.keySet()) {
                httpGet.addHeader(key, additionalHeaders.get(key));
            }
        }
    }
    
    private void throwFailedRequestExceptionOnUnwantedResponseCode(HttpResponse response) throws RequestFailedException, IOException {
        InputStream errorStream = null;
        try {
            int responseCode = response.getStatusLine().getStatusCode();
            if (isUnwantedResponseCode(responseCode)) {
                errorStream = response.getEntity().getContent();
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