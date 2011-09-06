package com.bring.api.tracking.response;

import com.bring.api.BringParser;
import com.bring.api.exceptions.UnmarshalException;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class BringParserTrackingTest {
    BringParser<TrackingResult> parser;
    InputStream enpakkeXml;
    InputStream noshipmentsXml;
    TrackingResult consignmentSet;
    Package openPackage;

    @Before
    public void setUp() throws FileNotFoundException, UnmarshalException {
        parser = new BringParser<TrackingResult>(TrackingResult.class);

        enpakkeXml = getClass().getResourceAsStream("enpakke.xml");
        noshipmentsXml = getClass().getResourceAsStream("noshipments.xml");

        consignmentSet = parser.unmarshal(enpakkeXml);
        openPackage = consignmentSet.getConsignments().get(0).getPackageSet().getPackages().get(0);
    }
    
    @Test(expected = UnmarshalException.class)
    public void shouldFailIfNoShipmentsAreFound() throws UnmarshalException {
        parser.unmarshal(noshipmentsXml);
    }
    
    @Test
    public void shouldParseConsignmentSetFromEnpakkeXml() throws UnmarshalException {
        assertEquals(TrackingResult.class, consignmentSet.getClass());
    }
    
    @Test
    public void shouldParseConsignmentFromEnpakkeXml() throws UnmarshalException {
        Consignment consignment = consignmentSet.getConsignments().get(0);
        assertEquals(Consignment.class, consignment.getClass());
    }
    
    @Test
    public void shouldParseConsignmentIdFromEnpakkeXml() throws UnmarshalException {
        String consignmentId = consignmentSet.getConsignments().get(0).getConsignmentId();
        assertEquals("70438101015432113", consignmentId);
    }
       
    @Test
    public void shouldParseTotalWeightFromEnpakkeXml() throws UnmarshalException {
        Weight weight = consignmentSet.getConsignments().get(0).getTotalWeight();
        assertEquals(Weight.class, weight.getClass());
    }
    
    @Test
    public void shouldParseWeightUnitFromEnpakkeXml() throws UnmarshalException {
        String weightUnit = consignmentSet.getConsignments().get(0).getTotalWeight().getUnitCode();
        assertEquals("kg", weightUnit);
    }
    
    @Test
    public void shouldParseWeightValueFromEnpakkeXml() throws UnmarshalException {
        String weightValue = consignmentSet.getConsignments().get(0).getTotalWeight().getValue();
        assertEquals("1.7", weightValue);
    }
    
    @Test
    public void shouldParseTotalVolumeFromEnpakkeXml() throws UnmarshalException {
        Volume volume = consignmentSet.getConsignments().get(0).getTotalVolume();
        assertEquals(Volume.class, volume.getClass());
    }
    
    @Test
    public void shouldParseVolumeUnitFromEnpakkeXml() throws UnmarshalException {
        String volumeUnit = consignmentSet.getConsignments().get(0).getTotalVolume().getUnitCode();
        assertEquals("dm3", volumeUnit);
    }
    
    @Test
    public void shouldParseVolumeValueFromEnpakkeXml() throws UnmarshalException {
        String volumeValue = consignmentSet.getConsignments().get(0).getTotalVolume().getValue();
        assertEquals("1.3", volumeValue);
    }
    
    @Test
    public void shouldParsePackageSetFromEnpakkeXml() throws UnmarshalException {
        PackageSet packageSet = consignmentSet.getConsignments().get(0).getPackageSet();
        assertEquals(PackageSet.class, packageSet.getClass());
    }
    
    @Test
    public void shouldParsePackageFromEnpakkeXml() throws UnmarshalException {
        Package pack = openPackage;
        String packId = pack.getPackageId();
        assertEquals(Package.class, pack.getClass());
        assertEquals("370438101015432114", packId);
    }
    
    @Test
    public void shouldParseStatusDescriptionFromEnpakkeXml() throws UnmarshalException {
        String result = openPackage.getStatusDescription();
        assertEquals("Pakken leveres til mottaker og kan ikke hentes på terminal", result);
    }
    
    @Test
    public void shouldParseProductNameFromEnpakkeXml() throws UnmarshalException {
        String result = openPackage.getProductName();
        assertEquals("BEDRIFTSPAKKE DØR - DØR INNLAND", result);
    }
    
    @Test
    public void shouldParseProductCodeFromEnpakkeXml() throws UnmarshalException {
        String result = openPackage.getProductCode();
        assertEquals("1000", result);
    }
    
    @Test
    public void shouldParseBrandFromEnpakkeXml() throws UnmarshalException {
        String result = openPackage.getBrand();
        assertEquals("BRING", result);
    }
    
    @Test
    public void shouldParseWeightFromEnpakkeXml() throws UnmarshalException {
        Weight result = openPackage.getWeight();
        assertEquals("kg", result.getUnitCode());
        assertEquals("1.7", result.getValue());
    }
    
    @Test
    public void shouldParseLengthFromEnpakkeXml() throws UnmarshalException {
        Length result = openPackage.getLength();
        assertEquals("cm", result.getUnitCode());
        assertEquals("30", result.getValue());
    }
    
    @Test
    public void shouldParseWidthFromEnpakkeXml() throws UnmarshalException {
        Length result = openPackage.getWidth();
        assertEquals("cm", result.getUnitCode());
        assertEquals("21", result.getValue());
    }
    
    @Test
    public void shouldParseHeightFromEnpakkeXml() throws UnmarshalException {
        Length result = openPackage.getHeight();
        assertEquals("cm", result.getUnitCode());
        assertEquals("2", result.getValue());
    }
    
    @Test
    public void shouldParseVolumeFromEnpakkeXml() throws UnmarshalException {
        Volume result = openPackage.getVolume();
        assertEquals("dm3", result.getUnitCode());
        assertEquals("1.3", result.getValue());
    }

    @Test
    public void shouldParseReturDatoFromEnpakkeXml(){
    	String returDato = openPackage.getLastRetrievalDate();
    	assertEquals("2010.12.14", returDato);
    }

    @Test
    public void shouldParseHentekodeFromEnpakkeXml(){
    	String pickupCode = openPackage.getPickupCode();
    	assertEquals("KK22", pickupCode);
    }

    @Test
    public void shouldBeAbleToGetOneConsignmentDirectly() throws UnmarshalException {
        Consignment consignment = consignmentSet.getConsignment(0);
        assertEquals(Consignment.class, consignment.getClass());
    }
    
    @Test
    public void shouldBeAbleToGetOnePackageDirectlyFromPackageSet() throws UnmarshalException {
        Package pack = consignmentSet.getConsignment(0).getPackageSet().getPackage(0);
        assertEquals(Package.class, pack.getClass());
    }
    
    @Test
    public void shouldBeAbleToGetPackageDirectlyFromConsignment() throws UnmarshalException {
        Package pack = consignmentSet.getConsignment(0).getPackage(0);
        assertEquals(Package.class, pack.getClass());
    }
    
    @Test
    public void shouldBeAbleToGetEventDirectlyFromPackage() throws UnmarshalException {
        Event event = consignmentSet.getConsignment(0).getPackage(0).getEvent(0);
        assertEquals(Event.class, event.getClass());
    }
}