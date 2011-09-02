package com.bring.api.tracking.dao;

import static org.junit.Assert.assertEquals;

import com.bring.api.tracking.request.TrackingQuery;
import org.junit.Before;
import org.junit.Test;

import com.bring.api.connection.HttpUrlConnectionAdapter;
import com.bring.api.tracking.response.TrackingResult;
import com.bring.api.exceptions.RequestFailedException;

public class TrackingDaoIntegrationTest {
    TrackingDao dao;
    TrackingResult cst;
        
    @Before
    public void setUp() throws RequestFailedException {
        dao = new TrackingDao(new HttpUrlConnectionAdapter("test"));
        TrackingQuery query = new TrackingQuery();
        query.withQueryNumber("70438101015432113");
        cst = dao.query(query);
    }
    
    @Test
    public void shouldFindConsignmentSetOnValidSearch() throws RequestFailedException {
        assertEquals(TrackingResult.class, cst.getClass());
    }
    
    @Test
    public void shouldFindTotalWeigthOnValidSearch() throws RequestFailedException {
        String totalWeight = cst.getConsignments().get(0).getTotalWeight().getValue();
        assertEquals("1.7", totalWeight);
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
    
    @Test
    public void shouldBeAbleToPrepareSignatureWithFullUrl() throws RequestFailedException {
        TrackingResult cs = dao.query(new TrackingQuery("70426101183957512"));
        String url = cs.getConsignment(0).getPackage(0).getEvent(0).getSignature().getLinkToImage();
        assertEquals("http://amphora.norcargo.no/track/servlet/axia.amphora.module.trackandtrace.SignatureServlet?id=18321547", url);
    }
}
