package com.bring.tracking.request;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.bring.exceptions.MissingParameterException;

public class TrackingQueryTest {
    TrackingQuery sqb;
    
    @Before
    public void setUp() {
        sqb = new TrackingQuery();
    }
    
    @Test(expected = MissingParameterException.class)
    public void shouldFailOnMissingSearchCriteria() {
        sqb.toQueryString();
    }
    
    @Test
    public void shouldGenerateQueryStringFromParameter() {
        sqb.withQueryNumber("123456");
        String str = sqb.toQueryString();
        assertEquals("?q=123456",str);
    }
}