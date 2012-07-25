package com.bring.api;

import com.bring.api.exceptions.RequestFailedException;
import com.bring.api.tracking.request.TrackingQuery;
import com.bring.api.tracking.response.TrackingResult;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BringServiceTrackingIntegrationTest {
    BringService service;
    
    @Before
    public void setUp(){
        service = new BringService("test");
    }
    
    @Test
    public void shouldBeAbleTofindPackage() throws RequestFailedException {
        TrackingQuery query = new TrackingQuery();
        query.withQueryNumber("TESTPACKAGEATPICKUPPOINT");
        TrackingResult result = service.queryTracking(query);
        assertEquals(TrackingResult.class, result.getClass());
    }
    
    @Test
    public void shouldBeAbleToFindLatestStatus() throws RequestFailedException {
        TrackingQuery query = new TrackingQuery("TESTPACKAGEDELIVERED");
        String result = service.queryTracking(query).getConsignment(0).getPackage(0).getEvent(0).getDescription();
        assertEquals("Sendingen er utlevert", result);
    }
}
