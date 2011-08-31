package com.bring.shippingguide.response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

import com.bring.BringParser;
import com.bring.shippingguide.request.ProductType;
import org.junit.Before;
import org.junit.Test;

import com.bring.exceptions.UnmarshalException;

/**
 * Unit test for simple App.
 */
public class BringParserShippingGuideTest {
    private BringParser<ShippingGuideResult> bringParser;
    private InputStream priceXml;
    private InputStream priceXml3;
    private InputStream shippingXml;
    private InputStream noproductsXml;



    @Before
    public void setUp() throws FileNotFoundException {
        bringParser = new BringParser<ShippingGuideResult>(ShippingGuideResult.class);
        priceXml = getClass().getResourceAsStream("price.xml");
        priceXml3 = getClass().getResourceAsStream("price3.xml");
        shippingXml = getClass().getResourceAsStream("shipping.xml");
        noproductsXml = getClass().getResourceAsStream("noproducts.xml");
    }

    @Test
    public void shouldUnmarshalPackageFromPriceXml() throws UnmarshalException {
        ShippingGuideResult p = bringParser.unmarshal(priceXml);
        assertEquals(ShippingGuideResult.class, p.getClass());
    }

    @Test
    public void shouldUnmarshalProductFromPriceXml() throws UnmarshalException {
        ShippingGuideResult p = bringParser.unmarshal(priceXml);
        assertEquals(Product.class, p.getProduct(ProductType.SERVICEPAKKE).getClass());
    }
    
    @Test
    public void shouldUnmarshalMultipleProductsFromPrice3Xml() throws UnmarshalException {
        ShippingGuideResult p = bringParser.unmarshal(priceXml3);
        Collection<Product> products = p.getProducts().values();
        for (Product product: products) {
            assertEquals(Product.class,product.getClass());
        }
        assertEquals(2,products.size());
    }
    
    @Test
    public void shouldUnmarshalTraceMessagesFromPriceXml() throws UnmarshalException {
        ShippingGuideResult p = bringParser.unmarshal(priceXml);
        assertEquals(TraceMessages.class, p.getTraceMessages().getClass());
    }
    
    @Test
    public void shouldFindTraceMessageFromShippingXml() throws UnmarshalException {
        ShippingGuideResult p = bringParser.unmarshal(shippingXml);
        assertEquals("Blabla", p.getTraceMessages().getFirstMessage());
    }
    
    @Test
    public void shouldFindMultipleTraceMessagesFromShippingXml() throws UnmarshalException {
        ShippingGuideResult p = bringParser.unmarshal(shippingXml);
        assertEquals("Blabla", p.getTraceMessages().getMessages().get(0));
        assertEquals("En melding", p.getTraceMessages().getMessages().get(1));
    }
    
    @Test
    public void shouldGiveNoMatchesOnWrongParameters() throws UnmarshalException {
        ShippingGuideResult p = bringParser.unmarshal(noproductsXml);
        assertNull(p.getProduct(ProductType.SERVICEPAKKE));
    }
    
    @Test
    public void shouldParseProductListIntoHashMap() throws UnmarshalException {
        ShippingGuideResult p = bringParser.unmarshal(priceXml3);
        assertTrue(p.getProducts() instanceof Map<?, ?>);
    }
    
    @Test
    public void shouldBeAbleToFetchSERVICEPAKKEAndPA_DORENFromProductMap() throws UnmarshalException {
        ShippingGuideResult p = bringParser.unmarshal(priceXml3);
        assertEquals(Product.class, p.getProduct(ProductType.SERVICEPAKKE).getClass());
        assertEquals(Product.class, p.getProduct(ProductType.PA_DOREN).getClass());
    }
}
