package com.bring.api.tracking.response;

import com.bring.api.BringParser;
import com.bring.api.exceptions.UnmarshalException;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class BringParserTrackingSenderAndRecipientTest {
    InputStream innloggetXml;
    TrackingResult consignmentSetInnlogget;
    Package innloggetPackage;

    @Before
    public void setUp() throws FileNotFoundException, UnmarshalException {
        BringParser<TrackingResult> parser = new BringParser<TrackingResult>(TrackingResult.class);

        innloggetXml = getClass().getResourceAsStream("innlogget.xml");

        consignmentSetInnlogget = parser.unmarshal(innloggetXml);
        innloggetPackage = consignmentSetInnlogget.getConsignments().get(0).getPackageSet().getPackages().get(0);
    }
    
    @Test
    public void shouldParseSendersNameFromInnloggetXml() {
        assertEquals("POSTEN NORGE AS POST BEDRIFTSSENTER POSTHUSET", innloggetPackage.getSenderName());
    }

    @Test
    public void shouldParseRecipientNameFromInnloggetXml() {
        assertEquals("OLA NORDMANN", innloggetPackage.getRecipientName());
    }

    @Test
    public void shouldParseRecipientAddressAddressLine1FromInnloggetXml() {
        assertEquals("STORVEIEN 2", innloggetPackage.getRecipientAddress().getAddressLine1());
    }

    @Test
    public void shouldParseRecipientAddressAddressLine2FromInnloggetXml() {
        assertEquals("Adresselinje2", innloggetPackage.getRecipientAddress().getAddressLine2());
    }

    @Test
    public void shouldParseRecipientAddressCityFromInnloggetXml() {
        assertEquals("OSLO", innloggetPackage.getRecipientAddress().getCity());
    }

    @Test
    public void shouldParseRecipientAddressCountryCodeFromInnloggetXml() {
        assertEquals("NO", innloggetPackage.getRecipientAddress().getCountryCode());
    }

    @Test
    public void shouldParseRecipientAddressCountryFromInnloggetXml() {
        assertEquals("NORGE", innloggetPackage.getRecipientAddress().getCountry());
    }

}