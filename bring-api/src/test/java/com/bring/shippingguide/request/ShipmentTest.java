package com.bring.shippingguide.request;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.bring.exceptions.ExceededNumberOfPackagesException;

public class ShipmentTest {
    private Shipment shipment;

    @Before
    public void setUp() {
        shipment = new Shipment();
        shipment.withFromPostalCode("7600");
        shipment.withToPostalCode("1407");
        shipment.addProduct(ProductType.SERVICEPAKKE);
    }
    
    @Test(expected = ExceededNumberOfPackagesException.class)
    public void shouldNotBeAbleToAddMoreThan10PackagesUsingWeight() {
        for (int i =0; i < 11; i++) {
            Package pd = new Package();
            pd.withWeightInGrams(Integer.toString(i*10));
            shipment.addPackage(pd);
        }
    }
    
    @Test(expected = ExceededNumberOfPackagesException.class)
    public void shouldNotBeAbleToSetMoreThan10PackagesUsingWeight() {
        List<Package> pdList = new ArrayList<Package>();
        for (int i =0; i < 11; i++) {
            Package pd = new Package();
            pd.withWeightInGrams(Integer.toString(i*10));
            pdList.add(pd);
        }
        shipment.withPackages(pdList);
    }
    
    @Test
    public void shouldBeAbleToAddMultiplePackagesUsingWeight(){
        String expected = "?from=7600&to=1407&weightInGrams0=1500&weightInGrams1=2500&product=SERVICEPAKKE&edi=true";
        Package pd = new Package();
        pd.withWeightInGrams("1500");
        Package pd2 = new Package();
        pd2.withWeightInGrams("2500");
        shipment.addPackage(pd);
        shipment.addPackage(pd2);
        assertEquals(expected, shipment.toQueryString());
    }
    
    @Test(expected = ExceededNumberOfPackagesException.class)
    public void shouldNotBeAbleToAddMoreThan10PackagesUsingVolume() {
        for (int i =0; i < 11; i++) {
            Package pd = new Package();
            shipment.addPackage(pd);
        }
    }
    
    @Test(expected = ExceededNumberOfPackagesException.class)
    public void shouldNotBeAbleToSetMoreThan10PackagesUsingVolume() {
        List<Package> pdList = new ArrayList<Package>();
        for (int i =0; i < 11; i++) {
            pdList.add(new Package());
        }
        shipment.withPackages(pdList);
    }
    
    @Test
    public void shouldBeAbleToAddMultiplePackagesUsingVolume(){
        String expected = "?from=7600&to=1407&volume0=100&volume1=250&product=SERVICEPAKKE&edi=true";
        Package pd = new Package();
        pd.withVolume("100");
        Package pd2 = new Package();
        pd2.withVolume("250");
        shipment.addPackage(pd);
        shipment.addPackage(pd2);
        assertEquals(expected, shipment.toQueryString());
    }
    
    @Test
    public void shouldBeAbleToAddMultiplePackagesUsingVolumeAndWeight(){
        String expected = "?from=7600&to=1407&weightInGrams0=100&volume1=250&product=SERVICEPAKKE&edi=true";
        Package pd = new Package();
        pd.withWeightInGrams("100");
        Package pd2 = new Package();
        pd2.withVolume("250");
        shipment.addPackage(pd);
        shipment.addPackage(pd2);
        assertEquals(expected, shipment.toQueryString());
    }
    
    @Test
    public void shouldBeAbleToAddMultiplePackagesUsingMultipleVolumesAndWeights(){
        String expected = "?from=7600&to=1407&weightInGrams0=100&volume0=250&weightInGrams1=120&volume1=270&product=SERVICEPAKKE&edi=true";
        Package pd = new Package();
        pd.withWeightInGrams("100");
        pd.withVolume("250");
        Package pd2 = new Package();
        pd2.withVolume("270");
        pd2.withWeightInGrams("120");
        shipment.addPackage(pd);
        shipment.addPackage(pd2);
        assertEquals(expected, shipment.toQueryString());
    }
    
    @Test
    public void shouldBeAbleToSetEdiToFalse() {
        String expected = "?from=7600&to=1407&weightInGrams0=100&product=SERVICEPAKKE&edi=false";
        Package pd = new Package();
        pd.withWeightInGrams("100");
        shipment.addPackage(pd);
        shipment.withEdi(false);
        assertEquals(expected, shipment.toQueryString());
    }
    
    @Test
    public void shouldBeAbleToSetPostingAtPostOfficeToTrue() {
        String expected = "?from=7600&to=1407&weightInGrams0=100&product=SERVICEPAKKE&edi=true&postingAtPostoffice=true";
        Package pd = new Package();
        pd.withWeightInGrams("100");
        shipment.addPackage(pd);
        shipment.withPostingAtPostOffice(true);
        assertEquals(expected, shipment.toQueryString());
    }
    
    @Test
    public void shouldBeAbleToAddOneAdditionalService() {
        String expected = "?from=7600&to=1407&weightInGrams0=1500&product=SERVICEPAKKE&edi=true&additional=EVARSLING";
        Package pd = new Package();
        pd.withWeightInGrams("1500");
        shipment.addPackage(pd);
        shipment.addAdditionalService(AdditionalService.EVARSLING);
        assertEquals(expected, shipment.toQueryString());
    }
    @Test
    public void shouldBeAbleToAddThreeAdditionalServices() {
        Package pd = new Package();
        pd.withWeightInGrams("1500");
        shipment.addPackage(pd);
        shipment.addAdditionalService(AdditionalService.EVARSLING);
        shipment.addAdditionalService(AdditionalService.POSTOPPKRAV);
        shipment.addAdditionalService(AdditionalService.LORDAGSUTKJORING);
        String result = shipment.toQueryString();
        assertTrue(result.contains("&additional=EVARSLING"));
        assertTrue(result.contains("&additional=POSTOPPKRAV"));
        assertTrue(result.contains("&additional=LORDAGSUTKJORING"));
    }
    
    @Test
    public void shouldNotBeAbleToAddTheSameAdditionalServiceMoreThanOnce() {
        String expected = "?from=7600&to=1407&weightInGrams0=1500&product=SERVICEPAKKE&edi=true&additional=EVARSLING";
        Package pd = new Package();
        pd.withWeightInGrams("1500");
        shipment.addPackage(pd);
        shipment.addAdditionalService(AdditionalService.EVARSLING);
        shipment.addAdditionalService(AdditionalService.EVARSLING);
        shipment.addAdditionalService(AdditionalService.EVARSLING);
        shipment.addAdditionalService(AdditionalService.EVARSLING);
        shipment.addAdditionalService(AdditionalService.EVARSLING);
        shipment.addAdditionalService(AdditionalService.EVARSLING);
        assertEquals(expected, shipment.toQueryString());
    }
    
    @Test
    public void shouldNotBeAbleToAddTheSameProductMoreThanOnce() {
        String expected = "?from=7600&to=1407&weightInGrams0=1500&product=A-POST&edi=true";
        Shipment fsc = new Shipment();
        Package pd = new Package();
        pd.withWeightInGrams("1500");
        fsc.addPackage(pd);
        fsc.withFromPostalCode("7600");
        fsc.withToPostalCode("1407");
        fsc.addProduct(ProductType.A_POST);
        fsc.addProduct(ProductType.A_POST);
        assertEquals(expected, fsc.toQueryString());
    }
    
    @Test
    public void shouldBeAbleToSetPid() {
        String expected = "?from=7600&to=1407&weightInGrams0=100&product=SERVICEPAKKE&edi=true&pid=abc123";
        Package pd = new Package();
        pd.withWeightInGrams("100");
        shipment.addPackage(pd);
        shipment.withPublicId("abc123");
        assertEquals(expected, shipment.toQueryString());
    }
    
    @Test
    public void shouldBeAbleToRequestAllProductsUsingProduct_ALL() {
        String expected = "?from=7600&to=1407&weightInGrams0=100&edi=true";
        Package pd = new Package();
        pd.withWeightInGrams("100");
        shipment.addPackage(pd);
        shipment.addProduct(ProductType.ALL);
        String result = shipment.toQueryString();
        assertEquals(expected,result);
    }
    
    @Test
    public void shouldNotIncludeToAndFromCountryIfNotToCountryIsSpecified(){
        shipment.withFromCountry("NO");
        Package pd = new Package();
        pd.withWeightInGrams("100");
        shipment.addPackage(pd);
        shipment.addProduct(ProductType.ALL);
        String result = shipment.toQueryString();
        assertFalse(result.contains("&toCountry"));
    }
    
    @Test
    public void shouldIncludeFromCountryIfSpecified() {
        shipment.withFromCountry("DK");
        Package pd = new Package();
        pd.withWeightInGrams("100");
        shipment.addPackage(pd);
        assertTrue(shipment.toQueryString().contains("&fromCountry=DK"));
    }
    
    @Test
    public void shouldNotIncludeDestinationCityIfNotSpecified(){
        Package pd = new Package();
        pd.withWeightInGrams("100");
        shipment.addPackage(pd);
        assertFalse(shipment.toQueryString().contains("&toCity="));
    }

    @Test
    public void shouldIncludeDestinationCityIfSpecified() {
        shipment.withToCity("Dublin");
        Package pd = new Package();
        pd.withWeightInGrams("100");
        shipment.addPackage(pd);
        assertTrue(shipment.toQueryString().contains("&toCity=Dublin"));
    }

    @Test
    public void shouldIncludeToCountryIfSpecified() {
        shipment.withToCountry("NO");
        Package pd = new Package();
        pd.withWeightInGrams("100");
        shipment.addPackage(pd);
        assertTrue(shipment.toQueryString().contains("&toCountry=NO"));
        assertFalse(shipment.toQueryString().contains("&fromCountry"));
    }
    
    @Test
    public void shouldIncludeToAndFromCountryIfSpecified() {
        shipment.withToCountry("NO");
        shipment.withFromCountry("SE");
        Package pd = new Package();
        pd.withWeightInGrams("100");
        shipment.addPackage(pd);
        assertTrue(shipment.toQueryString().contains("&toCountry=NO"));
        assertTrue(shipment.toQueryString().contains("&fromCountry=SE"));
    }
}