package com.bring.api.shippingguide.response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.io.InputStream;

import com.bring.api.BringParser;
import com.bring.api.shippingguide.request.ProductType;
import org.junit.Before;
import org.junit.Test;

import com.bring.api.exceptions.UnmarshalException;

public class BringParserShippingGuideShippingTimeTest {
    BringParser<ShippingGuideResult> parser;
    InputStream shippingXml;
    InputStream shippingXml2;

    @Before
    public void setUp() throws FileNotFoundException {
        parser = new BringParser<ShippingGuideResult>(ShippingGuideResult.class);
        shippingXml = getClass().getResourceAsStream("shipping.xml");
        shippingXml2 =getClass().getResourceAsStream("shipping2.xml");

    }
    
    @Test(expected = NullPointerException.class)
    public void shouldNotFindExpectedDeliveryInPriceXml() throws FileNotFoundException, UnmarshalException{
        InputStream priceXml = getClass().getResourceAsStream("price.xml");
        ShippingGuideResult pkg = parser.unmarshal(priceXml);
        assertEquals(ExpectedDelivery.class,pkg.getProduct(ProductType.SERVICEPAKKE).getExpectedDelivery().getClass());
    }
    
    @Test(expected = UnmarshalException.class)
    public void shouldNotFindAnythingInEmptyXml() throws FileNotFoundException, UnmarshalException{
        InputStream emptyXml = getClass().getResourceAsStream("empty.xml");
        parser.unmarshal(emptyXml);
    }
    
    @Test
    public void shouldFindExpectedDeliveryInShippingXml() throws UnmarshalException {
        ShippingGuideResult pkg = parser.unmarshal(shippingXml);
        assertEquals(ExpectedDelivery.class,pkg.getProduct(ProductType.SERVICEPAKKE).getExpectedDelivery().getClass());
    }
    
    @Test
    public void shouldFindWorkingDaysInShippingXml() throws UnmarshalException {
        ShippingGuideResult pkg = parser.unmarshal(shippingXml);
        assertEquals("2",pkg.getProduct(ProductType.SERVICEPAKKE).getExpectedDelivery().getWorkingDays());
    }
    
    @Test
    public void shouldFindEmptyUserMessageInShippingXml() throws UnmarshalException {
        ShippingGuideResult pkg = parser.unmarshal(shippingXml2);
        assertNotNull(pkg.getProduct(ProductType.SERVICEPAKKE).getExpectedDelivery().getUserMessage());
    }
    
    @Test
    public void shouldFindUserMessageInShipping2Xml() throws UnmarshalException {
        ShippingGuideResult pkg = parser.unmarshal(shippingXml2);
        assertEquals("e", pkg.getProduct(ProductType.SERVICEPAKKE).getExpectedDelivery().getUserMessage());
    }
    
    @Test
    public void shouldFindUserFormattedExpectedDeliveryDateInShippingXml() throws UnmarshalException {
        ShippingGuideResult pkg = parser.unmarshal(shippingXml);
        assertEquals("05.02.2009", pkg.getProduct(ProductType.SERVICEPAKKE).getExpectedDelivery().getFormattedExpectedDeliveryDate());
    }
    
    @Test
    public void shouldFindExpectedDeliveryDateInShippingXml() throws UnmarshalException {
        ShippingGuideResult pkg = parser.unmarshal(shippingXml);
        assertEquals(ExpectedDeliveryDate.class, pkg.getProduct(ProductType.SERVICEPAKKE).getExpectedDelivery().getExpectedDeliveryDate().getClass());
    }
    
    @Test
    public void shouldFindYearAndMonthAndDayInShippingXml() throws UnmarshalException {
        ShippingGuideResult pkg = parser.unmarshal(shippingXml);
        assertEquals("2009", pkg.getProduct(ProductType.SERVICEPAKKE).getExpectedDelivery().getExpectedDeliveryDate().getYear());
        assertEquals("2", pkg.getProduct(ProductType.SERVICEPAKKE).getExpectedDelivery().getExpectedDeliveryDate().getMonth());
        assertEquals("5", pkg.getProduct(ProductType.SERVICEPAKKE).getExpectedDelivery().getExpectedDeliveryDate().getDay());
    }
    
    @Test
    public void shouldFindEmptyAlternativeShippingDateInShippingXml() throws UnmarshalException {
        ShippingGuideResult pkg = parser.unmarshal(shippingXml);
        assertEquals(AlternativeDeliveryDates.class, pkg.getProduct(ProductType.SERVICEPAKKE).getExpectedDelivery().getAlternativeDeliveryDates().getClass());
    }
    
}