package com.bring;

import static org.junit.Assert.assertEquals;

import com.bring.shippingguide.request.Package;
import com.bring.shippingguide.request.QueryType;
import com.bring.shippingguide.request.Shipment;
import com.bring.shippingguide.response.ShippingGuideResult;
import org.junit.Before;
import org.junit.Test;

import com.bring.exceptions.RequestFailedException;

public class BringServiceIntegrationTest {
    Shipment shipment;
    BringService service;
    
    @Before
    public void setUp(){
        shipment = new Shipment();
        service = new BringService("test");
    }
    
    @Test
    public void shouldBeAbleTofindPackage() throws RequestFailedException {
        shipment.withToPostalCode("7050");
        shipment.withFromPostalCode("1407");
        Package packet = new Package();
        packet.withWeightInGrams("345");
        shipment.addPackage(packet);

        ShippingGuideResult result = service.queryShippingGuide(shipment, QueryType.ALL);

        assertEquals(ShippingGuideResult.class,result.getClass());
    }
    
    @Test
    public void shouldBeAbleToSetUserIdentity() throws RequestFailedException {
        shipment.withToPostalCode("7050");
        shipment.withFromPostalCode("1407");
        Package packet = new Package();
        packet.withWeightInGrams("345");
        shipment.addPackage(packet);

        ShippingGuideResult result = service.queryShippingGuide(shipment, QueryType.ALL);

        assertEquals(ShippingGuideResult.class, result.getClass());
    }
}