package com.bring.shippingguide.response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import com.bring.BringService;
import com.bring.shippingguide.request.Package;
import com.bring.shippingguide.request.ProductType;
import com.bring.shippingguide.request.QueryType;
import com.bring.shippingguide.request.Shipment;
import org.junit.Before;
import org.junit.Test;

import com.bring.exceptions.RequestFailedException;

public class BringParserShippingGuideFindExpectedDeliveryTest {
    Shipment shipment;
    BringService service;
    
    @Before
    public void setUp(){
        shipment = new Shipment();
        service = new BringService("test");
        shipment.withFromPostalCode("1409");
        shipment.withToPostalCode("7050");
        Package pd = new Package();
        pd.withWeightInGrams("432");
        shipment.addPackage(pd);
    }
    
    @Test
    public void shouldReturnExpectedDeliveryObjectSearch() throws RequestFailedException {
        shipment.addProduct(ProductType.SERVICEPAKKE);
        ExpectedDelivery e = service.queryShippingGuide(shipment, QueryType.EXPECTED_DELIVERY).getProduct(ProductType.SERVICEPAKKE).getExpectedDelivery();
        assertEquals(ExpectedDelivery.class,e.getClass());
    }

    @Test
    public void shouldReturnPriceObjectsOnSearch() throws RequestFailedException {
        shipment.withFromPostalCode("1409");
        shipment.withToPostalCode("7050");
        Package pd2 = new Package();
        pd2.withWeightInGrams("432");
        shipment.addPackage(pd2);
        shipment.addProduct(ProductType.SERVICEPAKKE);
        shipment.addProduct(ProductType.B_POST);
        Map<ProductType,Product> eDMap = service.queryShippingGuide(shipment, QueryType.EXPECTED_DELIVERY).getProducts();
        assertTrue(eDMap.containsKey(ProductType.SERVICEPAKKE));
        assertTrue(eDMap.containsKey(ProductType.B_POST));
        assertEquals(ExpectedDelivery.class,eDMap.get(ProductType.SERVICEPAKKE).getExpectedDelivery().getClass());
        assertEquals(ExpectedDelivery.class,eDMap.get(ProductType.B_POST).getExpectedDelivery().getClass());
    }
    
}