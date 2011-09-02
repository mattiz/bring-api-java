package com.bring.api.shippingguide.request;

/**
 *  Used to adjust the price returned from Shipping Guide.
 */
public class PriceAdjustment {
    private int amount = -1;
    private int adjustment = 0;
    private PriceAdjusmentUnit unit = PriceAdjusmentUnit.AMOUNT;
    public static final int AMOUNT_NOT_SET = -1;

    /**
     * Absolute amount that will be used as amountWithoutVAT in the search result
     * @param amount
     * @return PriceAdjustment
     */
    public PriceAdjustment withAbsoluteAmount(int amount) {
        this.amount = amount;
        return this;
    }

    /**
     * Increases amountWithoutVAT in the search result with the given value.
     * @param amount
     * @return PriceAdjustment
     */
    public PriceAdjustment withIncreasedAmount(int amount) {
        this.adjustment = amount;
        this.unit = PriceAdjusmentUnit.AMOUNT;
        return this;
    }

    /**
     * Decreases amountWithoutVAT in the search result with the given value.
     * @param amount
     * @return PriceAdjustment
     */
    public PriceAdjustment withDecreasedAmount(int amount) {
        adjustment = -amount;
        this.unit = PriceAdjusmentUnit.AMOUNT;
        return this;
    }
    
    /**
     * Increases amountWithoutVAT in the search result with the given percentage.
     * @param percentage
     * @return PriceAdjustment
     */
    public PriceAdjustment withIncreasedPercentage(int percentage) {
        adjustment = percentage;
        this.unit = PriceAdjusmentUnit.PERCENTAGE;
        return this;
    }
    
    /**
     * Decreases amountWithoutVAT in the search result with the given percentage.
     * @param percentage
     * @return PriceAdjustment
     */
    public PriceAdjustment withDecreasedPercentage(int percentage) {
        adjustment = -percentage;
        this.unit = PriceAdjusmentUnit.PERCENTAGE;
        return this;
    }
    
    public String toQueryString(ProductType key) {
        // Set amount
        String str = "";
        if( amount != AMOUNT_NOT_SET) {
            str += "&priceAdjustment=";
            if (key == ProductType.ALL) {
                str += amount;
            }
            else {
                str += key.getQueryName()+"_"+amount;
            } 
        }
        
        // Increase / decrease amount
        if (adjustment != 0) {
            str += "&priceAdjustment=";
            if (key != ProductType.ALL) {
                str += key.getQueryName()+"_";
            }
            str += ((adjustment > 0) ? "p" : "m") + Math.abs(adjustment);
            str += unit.getName();
        }
        return str;
    }
    
    public int getAmount() {
    	return amount;
    }
    
    public int getAdjustment() {
    	return adjustment;
    }
    
    public PriceAdjusmentUnit getAdjustmentUnit() {
    	return unit;
    }
}
