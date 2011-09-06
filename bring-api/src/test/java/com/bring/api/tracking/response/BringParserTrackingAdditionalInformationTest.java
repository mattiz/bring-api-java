package com.bring.api.tracking.response;

import com.bring.api.BringParser;
import com.bring.api.exceptions.UnmarshalException;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BringParserTrackingAdditionalInformationTest {
    InputStream innloggetXml;
    List<AdditionalService> additionalServices;

    @Before
    public void setUp() throws FileNotFoundException, UnmarshalException {
        BringParser<TrackingResult> parser = new BringParser<TrackingResult>(TrackingResult.class);

        innloggetXml = getClass().getResourceAsStream("innlogget.xml");

        TrackingResult consignmentSetInnlogget = parser.unmarshal(innloggetXml);
        Package innloggetPackage = consignmentSetInnlogget.getConsignments().get(0).getPackageSet().getPackages().get(0);
        additionalServices = innloggetPackage.getAdditionalServicesSet().getAdditionalService();
    }
    
    @Test
    public void shouldParseAdditionalInformationFromInnloggetXml() {
        assertEquals("3122", additionalServices.get(0).getId());
        assertEquals("122", additionalServices.get(0).getAmount());
        assertEquals("NOK", additionalServices.get(0).getCurrencyCode());
        assertEquals("tilleggstjeneste", additionalServices.get(0).getDescription());
    }


}