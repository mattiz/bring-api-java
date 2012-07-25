package com.bring.api.shippingguide.request;

import com.bring.api.exceptions.IllegalDimensionException;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Weight, volume or length, width and height must be set before querying Bring.
 */
public class Package {
    private String length;
    private String width;
    private String height;
    private String volume;
    private String weightInGrams;

    /**
     * @return Package length in cm
     */
    public String getLength() {
        return length;
    }

    /**
     * @param length Package length in cm
     */
    public Package withLength(String length) {
        this.length = length;
        return this;
    }

    /**
     * @return Package width in cm
     */
    public String getWidth() {
        return width;
    }

    /**
     * @param width Package width in cm
     */
    public Package withWidth(String width) {
        this.width = width;
        return this;
    }

    /**
     * @return Package height in cm
     */
    public String getHeight() {
        return height;
    }

    /**
     * @param height Package height in cm
     */
    public Package withHeight(String height) {
        this.height = height;
        return this;
    }
    
    /**
     * @return volume Package volume in dm^3
     */
    public String getVolume() {
        return volume;
    }

    /**
     * @param volume Package volume in dm^3
     */
    public Package withVolume(String volume) {
        this.volume = volume;
        return this;
    }

    public String getWeightInGrams() {
        return weightInGrams;
    }
    
    public Package withWeightInGrams(String weightInGrams) {
        this.weightInGrams = weightInGrams;
        return this;
    }

    public Package withWeightInKgs(String weightInKgs) {
        if (weightInKgs == null) {
            throw new IllegalArgumentException("Weight must be set");
        }
        BigDecimal weight = new BigDecimal(weightInKgs);
        this.weightInGrams = weight.multiply(new BigDecimal(1000)).setScale(0, RoundingMode.HALF_UP).toString();
        return this;
    }

    /**
     * @param suffix Used to append suffix to weightInGrams and volume (e.g. volume4=..)
     * @return String to be used in HTTP GET request
     */
    public String toQueryString(String suffix){
        String str = "";
        
        if (weightInGrams != null) {
            str += "&weightInGrams" + suffix + "=" + weightInGrams;
        }
       
        if (volume != null) {
            str += "&volume" + suffix + "=" + volume;
        }
        
        // Validate length, width and height
        if (length != null && width != null && height != null) {
            str += "&length" + suffix + "=" + length + "&width" + suffix + "=" + width + "&height" + suffix + "="+ height;
        }        
        else if (length != null || width != null || height != null) {
            throw new IllegalDimensionException("length, width and height must be specified");
        }
        
        if(str.equals("")){
            throw new IllegalDimensionException("Weight in grams, or volume must be specified.");
        }
        
        return str;
    }
}