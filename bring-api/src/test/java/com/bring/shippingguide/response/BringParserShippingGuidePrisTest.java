package com.bring.shippingguide.response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.io.InputStream;

import com.bring.BringParser;
import com.bring.shippingguide.request.ProductType;
import org.junit.Before;
import org.junit.Test;

import com.bring.exceptions.UnmarshalException;

/**
 * Unit test for simple App.
 * KlasseNavnHvaManTesterTest
 */
public class BringParserShippingGuidePrisTest {
    private BringParser<ShippingGuideResult> parser;
    private InputStream priceXml;

    @Before
    public void setUp() throws FileNotFoundException {
        parser = new BringParser<ShippingGuideResult>(ShippingGuideResult.class);
        priceXml = getClass().getResourceAsStream("price.xml");
    }

    @Test
    public void shouldUnmarshalPriceCurrencyCodeFromPrice1Xml() throws UnmarshalException {
        ShippingGuideResult p = (ShippingGuideResult) parser.unmarshal(priceXml);
        assertEquals("NOK", p.getProduct(ProductType.SERVICEPAKKE).getPrice()
                .getCurrencyIdentificationCode());
    }

    @Test
    public void shouldReturnAmountWithoutVAT81OnPriceXml() throws UnmarshalException {
        ShippingGuideResult p = (ShippingGuideResult) parser.unmarshal(priceXml);
        assertNotNull(p.getProduct(ProductType.SERVICEPAKKE).getPrice()
                .getPackagePriceWithoutAdditionalServices());
        assertEquals("81.00", p.getProduct(ProductType.SERVICEPAKKE).getPrice()
                .getPackagePriceWithoutAdditionalServices()
                .getAmountWithoutVAT());
    }

    @Test
    public void shouldReturnVAT20_25FromPriceXml() throws UnmarshalException {
        ShippingGuideResult p = parser.unmarshal(priceXml);
        assertEquals("20.25", p.getProduct(ProductType.SERVICEPAKKE).getPrice()
                .getPackagePriceWithoutAdditionalServices().getVAT());
    }

    @Test
    public void shouldReturnAmountWithVAT101_25FromPriceXml() throws UnmarshalException {
        ShippingGuideResult p = parser.unmarshal(priceXml);
        assertEquals("101.25", p.getProduct(ProductType.SERVICEPAKKE).getPrice()
                .getPackagePriceWithoutAdditionalServices().getAmountWithVAT());
    }

    // @Test
    // public void shoulReturnAmountWithoutVAT81OnPrice1Request() {
    // bring.setFromPostCode("7600");
    // bring.setToPostCode("1407");
    // bring.setWeight("1500");
    // assertEquals("81", bring.getAmountWithoutVAT());
    // }

    // @Test
    // public void shouldReturnAmountWithoutVAT71OnPrice2Xml() {
    // bring.setFromPostCode("7050");
    // bring.setToPostCode("1407");
    // bring.setWeight("1500");
    // assertEquals("71", bring.getAmountWithoutVAT());
    // }

}
