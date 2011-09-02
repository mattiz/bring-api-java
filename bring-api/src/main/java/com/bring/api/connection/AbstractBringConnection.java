package com.bring.api.connection;

import java.net.HttpURLConnection;

public abstract class AbstractBringConnection implements BringConnection {

    protected int connectionTimeout = 30000;
    protected int readTimeout = 15000;
    
    public boolean isUnwantedResponseCode(int responseCode) {
        return responseCode != HttpURLConnection.HTTP_OK;
    }
    
    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }
    
    public String generateUserAgentString(String identifier) {
        return "open-java-api/1.0 (" + identifier + ")";
    }
}