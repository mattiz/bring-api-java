package com.bring.api.tracking.dao;

import com.bring.api.connection.HttpUrlConnectionAdapter;
import com.bring.api.exceptions.RequestFailedException;
import com.bring.api.tracking.request.TrackingQuery;
import com.bring.api.tracking.response.TrackingResult;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TrackingDaoIntegrationTest {
    TrackingDao dao;
    TrackingResult cst;
        
    @Before
    public void setUp() throws RequestFailedException {
        dao = new TrackingDao(new HttpUrlConnectionAdapter("test"));
        TrackingQuery query = new TrackingQuery();
        query.withQueryNumber("TESTPACKAGELOADEDFORDELIVERY");
        cst = dao.query(query);
    }
    
    @Test
    public void shouldFindConsignmentSetOnValidSearch() throws RequestFailedException {
        assertEquals(TrackingResult.class, cst.getClass());
    }
    
    @Test
    public void shouldFindTotalWeigthOnValidSearch() throws RequestFailedException {
        String totalWeight = cst.getConsignments().get(0).getTotalWeight().getValue();
        assertEquals("16.5", totalWeight);
    }
    
    @Test(expected = RequestFailedException.class)
    public void shouldThrowUnmarshalExceptionOnSearchWithWrongParameter() throws RequestFailedException {
        TrackingQuery query = new TrackingQuery();
        query.withQueryNumber("70438101015432113xc");
        cst = dao.query(query);
    }
    
    @Test(expected = RequestFailedException.class)
    public void shouldHandleInvalidLogins() throws RequestFailedException {
        TrackingDao trackingDao = new TrackingDao(new HttpUrlConnectionAdapter("test"));
        trackingDao.query(new TrackingQuery("1234567"), "username", "apiKey");
    }

}
