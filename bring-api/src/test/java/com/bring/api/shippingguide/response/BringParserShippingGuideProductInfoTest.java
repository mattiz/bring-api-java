package com.bring.api.shippingguide.response;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.InputStream;

import com.bring.api.BringParser;
import com.bring.api.shippingguide.request.ProductType;
import org.junit.Before;
import org.junit.Test;

import com.bring.api.exceptions.UnmarshalException;

public class BringParserShippingGuideProductInfoTest {
    BringParser<ShippingGuideResult> parser;
    InputStream productInfo;
    
    @Before
    public void setUp() throws FileNotFoundException {
        parser = new BringParser<ShippingGuideResult>(ShippingGuideResult.class);
        productInfo = getClass().getResourceAsStream("productinfo.xml");
    }
    
    @Test
    public void shouldUnmarshalGuiInformationFromProductinfoXml() throws UnmarshalException {
        ShippingGuideResult pkg = parser.unmarshal(productInfo);
        assertEquals(GuiInformation.class, pkg.getProduct(ProductType.SERVICEPAKKE).getGuiInformation().getClass());
    }
    
    @Test
    public void shouldFindYearAndMonthAndDayInShippingXml() throws UnmarshalException {
        ShippingGuideResult pkg = parser.unmarshal(productInfo);
        assertEquals("11", pkg.getProduct(ProductType.SERVICEPAKKE).getGuiInformation().getSortOrder());
        assertEquals("Hent sendingen selv", pkg.getProduct(ProductType.SERVICEPAKKE).getGuiInformation().getMainDisplayCategory());
        assertEquals("", pkg.getProduct(ProductType.SERVICEPAKKE).getGuiInformation().getSubDisplayCategory());
        assertEquals("På postkontor eller post i butikk", pkg.getProduct(ProductType.SERVICEPAKKE).getGuiInformation().getDisplayName());
        assertEquals("Klimanøytral Servicepakke", pkg.getProduct(ProductType.SERVICEPAKKE).getGuiInformation().getProductName());
        assertEquals("Vinterbro Post i Butikk, COOP OBS Vinterbro. Åpningstider Man - Fre: 1000-2100, Lør: 0900-1800", pkg.getProduct(ProductType.SERVICEPAKKE).getGuiInformation().getDescriptionText());
        assertEquals("Sendingen er en Klimanøytral Servicepakke som blir levert til mottakers postkontor/ post i butikk. Mottaker kan velge å hente sendingen på et annet postkontor/post i butikk enn sitt lokale. Mottaker varsles om at sendingen er ankommet via SMS, e-post eller hentemelding i postkassen. Transporttid er normalt 1-3 virkedager, avhengig av strekning. Sendingen kan spores ved hjelp av sporingsnummeret.", pkg.getProduct(ProductType.SERVICEPAKKE).getGuiInformation().getHelpText());
        assertEquals("", pkg.getProduct(ProductType.SERVICEPAKKE).getGuiInformation().getTip());
    }
    
    @Test
    public void shouldFindProductCodeInProductionSystemInProductInfoXml() throws UnmarshalException {
        ShippingGuideResult pkg = parser.unmarshal(productInfo);
        assertEquals("1202", pkg.getProduct(ProductType.SERVICEPAKKE).getProductCodeInProductionSystem());
    }
}
