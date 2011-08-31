package com.bring.shippingguide.request;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.bring.exceptions.MissingParameterException;

public class ShipmentDescriptionPriceTest {
    private Shipment shipment;
    private Shipment shipment2;
    private Package packet;

    @Before
    public void setUp() {
        shipment = new Shipment();
        shipment2 = new Shipment();
        shipment2.withFromPostalCode("7600");
        shipment2.withToPostalCode("1407");
        packet = new Package();
        packet.withWeightInGrams("324");
        shipment2.addPackage(packet);
        }

    @Test(expected = MissingParameterException.class)
    public void shouldFailOnMissingFromPostalCode() {
        shipment.withToPostalCode("1407");
        shipment.toQueryString();
    }

    @Test(expected = MissingParameterException.class)
    public void shouldFailOnMissingToPostalCode() {
        shipment.withFromPostalCode("1407");
        shipment.toQueryString();
    }

    public void shouldSucceedOnMissingPacketParameter() {
        String expected = "?from=7600&to=1407&edi=true";
        shipment.withFromPostalCode("1407");
        shipment.withToPostalCode("7050");
        assertEquals(expected, shipment.toQueryString());
    }

    @Test
    public void shouldBuildUrlForSearchOnWeight() {
        String expected = "?from=7600&to=1407&weightInGrams0=1500&product=SERVICEPAKKE&edi=true";
        shipment.withFromPostalCode("7600");
        shipment.withToPostalCode("1407");
        Package pd = new Package();
        pd.withWeightInGrams("1500");
        shipment.addPackage(pd);
        shipment.addProduct(ProductType.SERVICEPAKKE);
        assertEquals(expected, shipment.toQueryString());
    }

    @Test
    public void shouldBuildUrlForSearchOnWeightAndHeightAndLenghtAndWidthAndVolume() {
        String expected = "?from=7600&to=1407&weightInGrams0=1500&volume0=33&length0=30&width0=40&height0=40&product=SERVICEPAKKE&edi=true";
        shipment.withFromPostalCode("7600");
        shipment.withToPostalCode("1407");
        Package pd = new Package();
        pd.withWeightInGrams("1500");
        pd.withLength("30");
        pd.withWidth("40");
        pd.withHeight("40");
        pd.withVolume("33");
        shipment.addPackage(pd);
        shipment.addProduct(ProductType.SERVICEPAKKE);
        assertEquals(expected, shipment.toQueryString());
    }

    @Test
    public void shouldBuildUrlForSearchOnProductIdA_POST() {
        String expected = "?from=7600&to=1407&weightInGrams0=324&product=A-POST&edi=true";
        shipment.withFromPostalCode("7600");
        shipment.withToPostalCode("1407");
        Package pd = new Package();
        pd.withWeightInGrams("324");
        shipment.addPackage(pd);
        shipment.addProduct(ProductType.A_POST);
        assertEquals(expected, shipment.toQueryString());
    }

    @Test
    public void shouldBuildUrlForSearchOnTwoProducts() {
        shipment.withFromPostalCode("7600");
        shipment.withToPostalCode("1407");
        Package pd = new Package();
        pd.withWeightInGrams("1500");
        shipment.addPackage(pd);
        Set<ProductType> prodList = new HashSet<ProductType>();
        prodList.add(ProductType.A_POST);
        prodList.add(ProductType.SERVICEPAKKE);
        shipment.withProducts(prodList);
        String result = shipment.toQueryString();
        assertTrue(result.contains("&product=A-POST"));
        assertTrue(result.contains("&product=SERVICEPAKKE"));
    }

    @Test
    public void shouldBuildUrlForSearchOnAllProducts() {
        String expected = "?from=7600&to=1407&weightInGrams0=324&edi=true";
        assertEquals(expected, shipment2.toQueryString());
    }
    
    @Test
    public void shouldBeAbleToSetPriceForOneProduct() {
        PriceAdjustment priceAdjustment = new PriceAdjustment();
        priceAdjustment.withAbsoluteAmount(200);
        shipment2.addPriceAdjustment(priceAdjustment, ProductType.B_POST);
        assertTrue(shipment2.toQueryString().contains("&priceAdjustment=B-POST_20"));
    }
    
    @Test
    public void shouldBeAbleToSetPriceForMultipleSpecifiedProducts() {
        PriceAdjustment bPostPa = new PriceAdjustment();
        PriceAdjustment aPostPa = new PriceAdjustment();
        bPostPa.withAbsoluteAmount(20);
        aPostPa.withAbsoluteAmount(10);
        shipment2.addPriceAdjustment(bPostPa, ProductType.B_POST);
        shipment2.addPriceAdjustment(aPostPa, ProductType.A_POST);
        String result = shipment2.toQueryString();
        assertTrue(result.contains("&priceAdjustment=B-POST_20"));
        assertTrue(result.contains("&priceAdjustment=A-POST_10"));
    }
    
    @Test
    public void shouldBeAbleToSetPostalCodeAndDimensions() {
        String expected = "?from=7600&to=1407&length0=30&width0=40&height0=40&edi=true";
        shipment.withFromPostalCode("7600");
        shipment.withToPostalCode("1407");
        Package pd = new Package();
        pd.withLength("30").withWidth("40").withHeight("40");
        shipment.addPackage(pd);
        assertEquals(expected, shipment.toQueryString());
    }
}