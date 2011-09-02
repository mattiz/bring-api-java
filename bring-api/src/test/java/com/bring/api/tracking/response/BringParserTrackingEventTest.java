package com.bring.api.tracking.response;

import com.bring.api.BringParser;
import com.bring.api.exceptions.UnmarshalException;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BringParserTrackingEventTest {
    BringParser<TrackingResult> parser;
    InputStream enpakkeXml;
    TrackingResult consignmentSet;
    EventSet eventSet;
    
    @Before
    public void setUp() throws FileNotFoundException, UnmarshalException {
        parser = new BringParser<TrackingResult>(TrackingResult.class);
        enpakkeXml = getClass().getResourceAsStream("enpakke.xml");
        consignmentSet = parser.unmarshal(enpakkeXml);
        eventSet = consignmentSet.getConsignments().get(0).getPackageSet().getPackages().get(0).getEventSet();
    }
    
    @Test
    public void shouldParseEventSetFromEnpakkeXml(){
    	assertEquals(EventSet.class, eventSet.getClass());
    }
    
    @Test
    public void shouldParseEventFromEnpakkeXml(){
    	Event event = eventSet.getEvents().get(0);
    	assertEquals(Event.class, event.getClass());
    }
    
    @Test
    public void shouldParseEventDescFromEnpakkeXml(){
    	String eventDesc = eventSet.getEvents().get(0).getDescription();
    	assertEquals("Sendingen er ankommet terminal og blir videresendt", eventDesc);
    }    
    
    @Test
    public void shouldParseEventStatusFromEnpakkeXml(){
    	String status = eventSet.getEvents().get(0).getStatus();
    	assertEquals("IN_TRANSIT", status);
    } 
    
    @Test
    public void shouldParseEventRecipientSignatureFromEnpakkeXml(){
    	Signature rs = eventSet.getEvents().get(0).getSignature();
    	String recipientSignatureName = rs.getName();
    	assertEquals(Signature.class, rs.getClass());
    	assertNotNull(recipientSignatureName);
    } 
    
    @Test
    public void shouldParseEventUnitIdFromEnpakkeXml(){
    	String unitId = eventSet.getEvents().get(0).getUnitId();
    	assertEquals("032825", unitId);
    } 
    
    @Test
    public void shouldParseEventPostalCodeFromEnpakkeXml(){
    	String postalCode = eventSet.getEvents().get(0).getPostalCode();
    	assertEquals("3007", postalCode);
    } 
    
    @Test
    public void shouldParseEventCityFromEnpakkeXml(){
    	String city = eventSet.getEvents().get(0).getCity();
    	assertEquals("DRAMMEN", city);
    } 
    
    @Test
    public void shouldParseEventCountryCodeFromEnpakkeXml(){
    	String countryCode = eventSet.getEvents().get(0).getCountryCode();
    	assertEquals("NO", countryCode);
    } 
    
    @Test
    public void shouldParseEventCountryFromEnpakkeXml(){
    	String country = eventSet.getEvents().get(0).getCountry();
    	assertEquals("Norway", country);
    } 
    
    
    @Test
    public void shouldParseEventOccuredAtIsoDateTimeFromEnpakkeXml(){
    	String occuredAtIsoDateTime = eventSet.getEvents().get(0).getOccuredAtIsoDateTime();
    	assertEquals("2011-06-28T08:03:20.000+02:00", occuredAtIsoDateTime);
    } 
    
    @Test
    public void shouldParseEventOccuredAtDisplayDateFromEnpakkeXml(){
    	String occuredAtDisplayDate = eventSet.getEvents().get(0).getOccuredAtDisplayDate();
    	assertEquals("28.06.2011", occuredAtDisplayDate);
    }
    
    @Test
    public void shouldParseEventOccuredAtDisplayTimeFromEnpakkeXml(){
    	String occuredAtDisplayTime = eventSet.getEvents().get(0).getOccuredAtDisplayTime();
    	assertEquals("08.03", occuredAtDisplayTime);
    } 
    
    @Test
    public void shouldParseEventConsignmentEventFromEnpakkeXml(){
    	boolean ce = eventSet.getEvents().get(0).isConsignmentEvent();
    	assertEquals(false, ce);
    }

}