package com.bring.api.shippingguide.request;

/**
 *  Used to limit query results (ALL returns everything, PRICE only price information, etc.).
 */
public enum QueryType {
    PRICE("price"), EXPECTED_DELIVERY("expectedDelivery"), ALL("all");
    
    private final String name;

    private QueryType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}