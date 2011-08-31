package com.bring.shippingguide.request;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

public class ShipmentDescriptionDateTimeTest {

    private Shipment shipment;

    @Before
    public void setUp() {
        shipment = new Shipment();
        shipment.withFromPostalCode("7600");
        shipment.withToPostalCode("1407");
        shipment.addProduct(ProductType.SERVICEPAKKE);
    }

    @Test
    public void shouldBeAbleToSetOnlyDateInShipmentDescription() {
        String expected = "?from=7600&to=1407&volume0=33&date=2009-02-03&time=00:00&product=SERVICEPAKKE&edi=true";
        Package pd = new Package();
        pd.withVolume("33");
        shipment.addPackage(pd);
        shipment.withShippingDateTime(new DateTime(2009, 2, 3, 0, 0, 0, 0));
        assertEquals(expected, shipment.toQueryString());
    }
    
    @Test
    public void shouldBeAbleToSetDateAndTimeInShipmentDescription() {
        String expected = "?from=7600&to=1407&volume0=33&date=2009-02-03&time=18:02&product=SERVICEPAKKE&edi=true";
        Package pd = new Package();
        pd.withVolume("33");
        shipment.addPackage(pd);
        shipment.withShippingDateTime(new DateTime(2009, 2, 3, 18, 2, 1, 3));
        assertEquals(expected, shipment.toQueryString());
    }
}
