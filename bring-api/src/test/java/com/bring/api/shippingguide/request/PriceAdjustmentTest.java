package com.bring.api.shippingguide.request;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PriceAdjustmentTest {
    private PriceAdjustment priceAdjustment;

    @Before
    public void setUp() {
        priceAdjustment = new PriceAdjustment();
    }
    
    @Test
    public void shouldBeAbleToSetAmount() {
        priceAdjustment.withAbsoluteAmount(20);
        assertEquals(20, priceAdjustment.getAmount());
    }
    
    @Test
    public void shouldBeAbleToIncreaseAmountOnce() {
        priceAdjustment.withIncreasedAmount(10);
        assertEquals(10, priceAdjustment.getAdjustment());
    }
    
    @Test
    public void shouldBeAbleToDecreaseAmountOnce() {
        priceAdjustment.withDecreasedAmount(12);
        assertEquals(-12, priceAdjustment.getAdjustment());
    }
    
    @Test
    public void shouldBeAbleToUsePercentageAdjustments() {
        priceAdjustment.withIncreasedPercentage(10);
        assertEquals(PriceAdjusmentUnit.PERCENTAGE, priceAdjustment.getAdjustmentUnit());
    }
    
    @Test
    public void shouldBeAbleToUseAmountAdjustments() {
        priceAdjustment.withIncreasedAmount(10);
        assertEquals(PriceAdjusmentUnit.AMOUNT, priceAdjustment.getAdjustmentUnit());
    }
    
    @Test
    public void shouldBeAbleToSetPriceForAllProducts() {
        PriceAdjustment pa = new PriceAdjustment();
        String expected = "&priceAdjustment=10";
        pa.withAbsoluteAmount(10);
        assertEquals(expected, pa.toQueryString(ProductType.ALL));
    }
    
    @Test
    public void shouldBeAbleToSetPriceForOneSpecifiedProduct() {
        String expected = "&priceAdjustment=B-POST_20";
        PriceAdjustment pa = new PriceAdjustment();
        pa.withAbsoluteAmount(20);
        assertEquals(expected, pa.toQueryString(ProductType.B_POST));
    }
    
    @Test
    public void shouldBeAbleToIncreasePriceForOneSpecifiedProduct() {
        PriceAdjustment bPostPa = new PriceAdjustment();
        bPostPa.withIncreasedAmount(20);
        assertEquals("&priceAdjustment=B-POST_p20", bPostPa.toQueryString(ProductType.B_POST));
    }
    
    @Test
    public void shouldNotDoAnythingWithEmptyPriceAdjustmentAmountAndB_Post() {
        PriceAdjustment emptyPa = new PriceAdjustment();
        assertEquals("",emptyPa.toQueryString(ProductType.ALL));
    }
    
    @Test
    public void shouldNotDoAnythingWithEmptyPriceAdjustmentForALLProducts() {
        PriceAdjustment emptyPa = new PriceAdjustment();
        assertEquals("",emptyPa.toQueryString(ProductType.ALL));
    }
    
    @Test
    public void shouldBeAbleToIncreaseAmount() {
        PriceAdjustment pa = new PriceAdjustment();
        pa.withIncreasedAmount(10);
        assertEquals("&priceAdjustment=p10", pa.toQueryString(ProductType.ALL));
    }
    
    @Test
    public void shouldBeAbleToDecreaseAmount() {
        PriceAdjustment pa = new PriceAdjustment();
        pa.withDecreasedAmount(10);
        assertEquals("&priceAdjustment=CARRYON_BUSINESS_DENMARK_m10", pa.toQueryString(ProductType.CARRYON_BUSINESS_DENMARK));
    }
    
    @Test
    public void shouldBeAbleToSetAndIncreaseAmount() {
        PriceAdjustment pa = new PriceAdjustment();
        pa.withIncreasedAmount(50);
        pa.withAbsoluteAmount(200);
        assertEquals("&priceAdjustment=200&priceAdjustment=p50",pa.toQueryString(ProductType.ALL));
    }
    
    @Test
    public void shouldBeAbleToIncreasePercentage() {
        PriceAdjustment pa = new PriceAdjustment();
        pa.withIncreasedPercentage(50);
        assertEquals("&priceAdjustment=p50p", pa.toQueryString(ProductType.ALL));
    }
    
    @Test
    public void shouldBeAbleToDecreasePercentage() {
        PriceAdjustment pa = new PriceAdjustment();
        pa.withDecreasedPercentage(50);
        assertEquals("&priceAdjustment=m50p", pa.toQueryString(ProductType.ALL));
    }
}
