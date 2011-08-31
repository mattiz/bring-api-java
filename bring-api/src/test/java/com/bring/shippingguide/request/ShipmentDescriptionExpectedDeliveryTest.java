package com.bring.shippingguide.request;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ShipmentDescriptionExpectedDeliveryTest {
    private Shipment shipment;

    @Before
    public void setUp() {
        shipment = new Shipment();
    }

    @Test
    public void shoulBeAbleToBuildUrlForSearchInExpectedDeliveryXml() {
        shipment.withFromPostalCode("2323");
        shipment.withToPostalCode("4552");
        Package pd = new Package();
        pd.withWeightInGrams("200");
        shipment.addPackage(pd);
        String expected = "?from=2323&to=4552&weightInGrams0=200&edi=true";
        String result = shipment.toQueryString();
        assertEquals(expected,result);
    }
}
