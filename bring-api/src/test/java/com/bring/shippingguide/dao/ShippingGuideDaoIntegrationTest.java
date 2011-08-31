package com.bring.shippingguide.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.bring.BringService;
import com.bring.shippingguide.request.Package;
import com.bring.shippingguide.request.ProductType;
import com.bring.shippingguide.request.QueryType;
import com.bring.shippingguide.request.Shipment;
import com.bring.shippingguide.response.ShippingGuideResult;
import com.bring.shippingguide.response.Price;
import org.junit.Before;
import org.junit.Test;

import com.bring.connection.HttpUrlConnectionAdapter;
import com.bring.exceptions.RequestFailedException;

public class ShippingGuideDaoIntegrationTest {

    private ShippingGuideDao dao;
    Package packet;
    Shipment shipment;

    @Before
    public void setUp() {
        dao = new ShippingGuideDao(new HttpUrlConnectionAdapter("test"));
        shipment = new Shipment();
        shipment.withFromPostalCode("1407");
        shipment.withToPostalCode("7050");
        packet = new Package();
        packet.withWeightInGrams("324");
        shipment.addPackage(packet);
    }

    @Test
    public void shouldFindPackageOnValidCriteria() throws RequestFailedException {
        ShippingGuideResult p = dao.query(shipment, QueryType.ALL);
        assertEquals(ShippingGuideResult.class, p.getClass());
    }

    @Test
    public void shouldFindPriceOnValidCriteria() throws RequestFailedException {
        Price price = dao.query(shipment, QueryType.PRICE).getProduct(ProductType.SERVICEPAKKE).getPrice();
        assertEquals(Price.class, price.getClass());
    }

    @Test
    public void shouldFindPackageOnAllCriterias() throws RequestFailedException {
        packet.withHeight("200");
        packet.withLength("300");
        packet.withWidth("400");
        packet.withVolume("6000");
        shipment.addProduct(ProductType.EKSPRESS07);
        ShippingGuideResult p = dao.query(shipment, QueryType.ALL);
        assertEquals(ShippingGuideResult.class, p.getClass());
    }
      
    @Test
    public void shouldBeAbleToAddMultiplePackagesPricesUsingMultipleWeights() throws RequestFailedException {
        shipment = new Shipment();
        shipment.withFromPostalCode("7600");
        shipment.withToPostalCode("1407");
        
        Package packet1 = new Package();
        packet1.withWeightInGrams("1500");
        shipment.addPackage(packet1);

        
        Package packet2 = new Package();
        packet2.withWeightInGrams("2500");
        shipment.addPackage(packet2);
        
        shipment.addProduct(ProductType.SERVICEPAKKE);
        ShippingGuideResult result = dao.query(shipment, QueryType.PRICE);
        assertEquals("174.00", result.getProducts().get(ProductType.SERVICEPAKKE).getPrice().getPackagePriceWithoutAdditionalServices().getAmountWithoutVAT());
    }
    
    @Test(expected = RequestFailedException.class)
    public void shouldFailOnInvalidPublicId() throws RequestFailedException {
        shipment.withPublicId("abc123");
        dao.query(shipment, QueryType.PRICE);
    }
    
    @Test
    public void shouldBeAbleToReadErrorMessageOnBadRequest() throws RequestFailedException {
        shipment.withPublicId("abc123");
        try {
            dao.query(shipment, QueryType.PRICE);
        } catch (RequestFailedException e) {
            assertTrue(e.getMessage().startsWith("FG_INPUT_025"));
        }
        
    }
    
    @Test
    public void shouldBeAbleToGetMultipleProducts() throws RequestFailedException {
        shipment.addProduct(ProductType.SERVICEPAKKE);
        shipment.addProduct(ProductType.B_POST);
        ShippingGuideResult result = dao.query(shipment, QueryType.PRICE);
        assertTrue(result.getProducts().size() == 2);
    }
    
    @Test
    public void testExample() throws RequestFailedException{
        String clientId = "www.mywebshop.com";
        BringService bringService = new BringService(clientId);

        // Prepare query
        Package packet = new Package();
        packet.withWeightInGrams("4233");
        Shipment shipment = new Shipment();
        shipment.withFromPostalCode("1409");
        shipment.withToPostalCode("7050");
        shipment.addPackage(packet);
        shipment.addProduct(ProductType.SERVICEPAKKE);

        // Fetch price information from Bring
        ShippingGuideResult shippingGuideResult = bringService.queryShippingGuide(shipment, QueryType.EXPECTED_DELIVERY);
        String expectedDeliveryDate = shippingGuideResult.getProduct(ProductType.SERVICEPAKKE).getExpectedDelivery().getWorkingDays();
    }
}
