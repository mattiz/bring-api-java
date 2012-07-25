package com.bring.api.shippingguide.request;

import java.util.Map;

/**
 * The products it is possible to query for.
 * Please note that fraktguiden might support additional products, please check fraktguide.bring.no.
 */
public enum ProductType {
    SERVICEPAKKE("SERVICEPAKKE"), 
    PA_DOREN("PA_DOREN"), 
    BPAKKE_DOR_DOR("BPAKKE_DOR-DOR"),
    EKSPRESS07("EKSPRESS07"), 
    EKSPRESS09("EKSPRESS09"),
    A_POST("A-POST"), 
    B_POST("B-POST"), 
    QUICKPACK_SAMEDAY("QUICKPACK_SAMEDAY"),
    QUICKPACK_OVER_NIGHT_0900("QUICKPACK_OVER_NIGHT_0900"),
    QUICKPACK_OVER_NIGHT_1200("QUICKPACK_OVER_NIGHT_1200"),
    QUICKPACK_DAY_CERTAIN("QUICKPACK_DAY_CERTAIN"),
    QUICKPACK_EXPRESS_ECONOMY("QUICKPACK_EXPRESS_ECONOMY"),
    CARRYON_BUSINESS_NORWAY("CARRYON_BUSINESS_NORWAY"),
    CARRYON_BUSINESS_SWEDEN("CARRYON_BUSINESS_SWEDEN"), 
    CARRYON_BUSINESS_DENMARK("CARRYON_BUSINESS_DENMARK"), 
    CARRYON_BUSINESS_FINLAND("CARRYON_BUSINESS_FINLAND"), 
    CARRYON_HOMESHOPPING_NORWAY("CARRYON_HOMESHOPPING_NORWAY"), 
    CARRYON_HOMESHOPPING_SWEDEN("CARRYON_HOMESHOPPING_SWEDEN"), 
    CARRYON_HOMESHOPPING_DENMARK("CARRYON_HOMESHOPPING_DENMARK"), 
    CARRYON_HOMESHOPPING_FINLAND("CARRYON_HOMESHOPPING_FINLAND"), 
    HOMEDELIVERY_CURBSIDE_DAG("HOMEDELIVERY_CURBSIDE_DAG"), 
    COURIER_VIP("COURIER_VIP"),
    COURIER_1H("COURIER_1H"),
    COURIER_2H("COURIER_2H"), 
    COURIER_4H("COURIER_4H"), 
    COURIER_6H("COURIER_6H"),
    COURIER_BICYCLE_VIP("COURIER_BICYCLE_VIP"),
    COURIER_BICYCLE_1H("COURIER_BICYCLE_1H"),
    COURIER_BICYCLE_2H("COURIER_BICYCLE_2H"),
    COURIER_BICYCLE_4H("COURIER_BICYCLE_4H"),
    COURIER_BICYCLE_6H("COURIER_BICYCLE_6H"),
    ALL("");

    private final String queryName;

    private ProductType(String queryName) {
        this.queryName = queryName;
    }
    
    public String getQueryName() {
        return queryName;
    }
    
    public static ProductType createFromQueryName(String name) {
        for (ProductType productType : values()) {
            if (productType.getQueryName().equals(name)) {
                return productType;
            }
        }
        throw new IllegalArgumentException("Unknown product: " + name);
    }
    
}
