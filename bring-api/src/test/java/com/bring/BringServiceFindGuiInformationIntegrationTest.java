package com.bring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Map;

import com.bring.shippingguide.request.Package;
import com.bring.shippingguide.request.ProductType;
import com.bring.shippingguide.request.QueryType;
import com.bring.shippingguide.request.Shipment;
import com.bring.shippingguide.response.GuiInformation;
import com.bring.shippingguide.response.Product;
import org.junit.Before;
import org.junit.Test;

import com.bring.exceptions.RequestFailedException;

public class BringServiceFindGuiInformationIntegrationTest {
    Shipment shipment;
    BringService service;
    Package packet;
    
    @Before
    public void setUp(){
        shipment = new Shipment();
        packet = new Package();
        service = new BringService("test");
        shipment.withFromPostalCode("1407");
        shipment.withToPostalCode("7050");
        packet.withWeightInGrams("1000");
        shipment.addPackage(packet);
    }

    @Test
    public void shouldReturnGuiInformationObjectOnSearch() throws RequestFailedException {
        shipment.addProduct(ProductType.SERVICEPAKKE);
        GuiInformation guiInformation = service.queryShippingGuide(shipment, QueryType.ALL).getProduct(ProductType.SERVICEPAKKE).getGuiInformation();
        assertEquals(GuiInformation.class,guiInformation.getClass());
    }
    
    @Test
    public void shouldReturnEmptyMapIfNoProductsAreFoundOnSearch() throws RequestFailedException {
        shipment.withPackages(new ArrayList<Package>());
        Package pd2 = new Package();
        pd2.withWeightInGrams("999999999");
        shipment.addPackage(pd2);
        shipment.addProduct(ProductType.SERVICEPAKKE);
        assertTrue(service.queryShippingGuide(shipment, QueryType.ALL).getProducts().isEmpty());
    }
    
    @Test
    public void shouldReturnGuiInformationObjectsOnSearch() throws RequestFailedException {
        shipment.addProduct(ProductType.SERVICEPAKKE);
        shipment.addProduct(ProductType.B_POST);
        Map<ProductType,Product> map = service.queryShippingGuide(shipment, QueryType.ALL).getProducts();
        assertTrue(map.containsKey(ProductType.SERVICEPAKKE));
        assertTrue(map.containsKey(ProductType.B_POST));
        assertEquals(GuiInformation.class,map.get(ProductType.SERVICEPAKKE).getGuiInformation().getClass());
        assertEquals(GuiInformation.class,map.get(ProductType.B_POST).getGuiInformation().getClass());

    }
    
}
