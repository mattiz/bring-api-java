package com.bring.api.shippingguide.request;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class ProductTest {
    @Before
    public void setUp() {
    }

    @Test
    public void shouldBeAbleToGetFraktguidenProductEnumFromQueryName() throws IOException {
        ProductType actual = ProductType.createFromQueryName("SERVICEPAKKE");
        assertEquals(ProductType.SERVICEPAKKE, actual);
    }
}
