package com.bring.tracking.request;

import com.bring.exceptions.MissingParameterException;

public class TrackingQuery {

    private String queryNumber;
    
    public TrackingQuery() {
    }
    
    public TrackingQuery(String queryNumber) {
        super();
        this.queryNumber = queryNumber;
    }


    /**
     * Required for making requests to the tracking system.
     * @param queryNumber Reference-, transmit-, or package number
     */
    public TrackingQuery withQueryNumber(String queryNumber) {
        this.queryNumber = queryNumber;
        return this;
    }

    public String getQueryNumber() {
        return queryNumber;
    }

    public String toQueryString() {
        if(queryNumber == null){
            throw new MissingParameterException("Missing query number.");
        }
        return "?q="+queryNumber;
    }
}