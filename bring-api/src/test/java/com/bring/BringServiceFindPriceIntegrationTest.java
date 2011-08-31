package com.bring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.bring.exceptions.RequestFailedException;
import com.bring.shippingguide.request.Package;
import com.bring.shippingguide.request.ProductType;
import com.bring.shippingguide.request.QueryType;
import com.bring.shippingguide.request.Shipment;
import com.bring.shippingguide.response.Price;
import com.bring.shippingguide.response.Product;

public class BringServiceFindPriceIntegrationTest {
    Shipment shipment;
    BringService service;
    
    @Before
    public void setUp(){
        shipment = new Shipment();
        service = new BringService("test");
    }
    
    @Test
    public void shouldReturnPriceObjectOnPriceSearch() throws RequestFailedException {
        shipment.withFromPostalCode("1409");
        shipment.withToPostalCode("7050");
        Package packet = new Package();
        packet.withWeightInGrams("4233");
        shipment.addPackage(packet);
        shipment.addProduct(ProductType.SERVICEPAKKE);

        Price price = service.queryShippingGuide(shipment, QueryType.PRICE).getProduct(ProductType.SERVICEPAKKE).getPrice();

        assertEquals(Price.class, price.getClass());
    }
        
    @Test
    public void shouldReturnPriceObjectsOnSearch() throws RequestFailedException {
        shipment.withFromPostalCode("1409");
        shipment.withToPostalCode("7050");
        Package packet = new Package();
        packet.withWeightInGrams("23");
        shipment.addPackage(packet);
        shipment.addProduct(ProductType.SERVICEPAKKE);
        shipment.addProduct(ProductType.B_POST);

        Map<ProductType,Product> priceMap = service.queryShippingGuide(shipment, QueryType.PRICE).getProducts();

        assertTrue(priceMap.containsKey(ProductType.SERVICEPAKKE));
        assertTrue(priceMap.containsKey(ProductType.B_POST));
        assertEquals(Price.class, priceMap.get(ProductType.SERVICEPAKKE).getPrice().getClass());
        assertEquals(Price.class, priceMap.get(ProductType.B_POST).getPrice().getClass());
    }
}