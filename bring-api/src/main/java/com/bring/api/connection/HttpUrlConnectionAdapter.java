package com.bring.api.connection;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import com.bring.api.exceptions.RequestFailedException;

/**
 * BringConnection that uses Java's standard UrlConnection implementation.
 */
public class HttpUrlConnectionAdapter extends AbstractBringConnection {

    private String clientIdentifier = null;

    /**
     * @param clientIdentifier Unique string that identifies the caller. Domain of the web site is preferred.
     */
    public HttpUrlConnectionAdapter(String clientIdentifier) {
        this.clientIdentifier = clientIdentifier;
    }
    
    public InputStream openInputStream(String url) throws RequestFailedException {
        return openInputStream(url, null);
    }

    public InputStream openInputStream(String url, Map<String, String> additionalHeaders) throws RequestFailedException {
        HttpURLConnection conn;
        
        InputStream inputStream = null;
        try {
            conn = openConnection(new URL(url));
            addHeaders(conn, additionalHeaders);
            conn.setConnectTimeout(getConnectionTimeout());
            conn.setReadTimeout(getReadTimeout());
            throwFailedRequestExceptionOnUnwantedResponseCode(conn);
            inputStream = conn.getInputStream();
        }
        catch (IOException e) {
            throw new RequestFailedException(e);
        }
        return inputStream;
    }

    protected void addHeaders(HttpURLConnection conn, Map<String, String> additionalHeaders) {
        conn.addRequestProperty("User-Agent", generateUserAgentString(clientIdentifier));
        if (additionalHeaders == null) {
            return;
        }
        for (String key : additionalHeaders.keySet()) {
            conn.addRequestProperty(key, additionalHeaders.get(key));
        }
    }
    
    protected HttpURLConnection openConnection(URL url) throws IOException {
        if(url.toString().contains("https")){
            return (HttpsURLConnection) url.openConnection();
        }
        return (HttpURLConnection) url.openConnection();
    }

    private void throwFailedRequestExceptionOnUnwantedResponseCode(HttpURLConnection conn) throws RequestFailedException, IOException {
        InputStream errorStream = null;
        try {
            int responseCode = conn.getResponseCode();
            if (isUnwantedResponseCode(responseCode)) {
                errorStream  = conn.getErrorStream();
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
