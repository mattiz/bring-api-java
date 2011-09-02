package com.bring.api;

import static org.junit.Assert.assertEquals;

import com.bring.api.tracking.request.TrackingQuery;
import org.junit.Before;
import org.junit.Test;

import com.bring.api.tracking.response.TrackingResult;
import com.bring.api.exceptions.RequestFailedException;

public class BringServiceTrackingIntegrationTest {
    BringService service;
    
    @Before
    public void setUp(){
        service = new BringService("test");
    }
    
    @Test
    public void shouldBeAbleTofindPackage() throws RequestFailedException {
        TrackingQuery query = new TrackingQuery();
        query.withQueryNumber("70438101015432113");
        TrackingResult result = service.queryTracking(query);
        assertEquals(TrackingResult.class, result.getClass());
    }
    
    @Test
    public void shouldBeAbleToFindLatestStatus() throws RequestFailedException {
        TrackingQuery query = new TrackingQuery("70438101015432113");
        String result = service.queryTracking(query).getConsignment(0).getPackage(0).getEvent(0).getDescription();
        assertEquals("Sendingen er utlevert", result);
    }
}
