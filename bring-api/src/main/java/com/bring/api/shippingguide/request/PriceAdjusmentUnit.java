package com.bring.api.shippingguide.request;

public enum PriceAdjusmentUnit {
    PERCENTAGE("p"), AMOUNT("");
    
    private final String unit;
    
    private PriceAdjusmentUnit(String unit) {
        this.unit = unit;
    }
    
    public String getName(){
        return unit;
    }
}