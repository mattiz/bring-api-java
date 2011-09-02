package com.bring.api.shippingguide.request;

public enum AdditionalService {
    EVARSLING("EVARSLING"),
    POSTOPPKRAV("POSTOPPKRAV"),
    LORDAGSUTKJORING("LORDAGSUTKJORING");
    
    private String name;
    
    private AdditionalService(String name) {
        this.name = name;
    }

    /**
     * Get name used in HTTP GET query
     */
    public String getName() {
        return name;
    }
}