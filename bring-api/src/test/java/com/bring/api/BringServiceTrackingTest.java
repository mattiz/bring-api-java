package com.bring.api;

import static org.apache.commons.io.IOUtils.toInputStream;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.bring.api.connection.BringConnection;
import com.bring.api.connection.HttpUrlConnectionAdapter;
import com.bring.api.exceptions.RequestFailedException;
import com.bring.api.shippingguide.response.ShippingGuideResult;
import com.bring.api.tracking.request.TrackingQuery;
import com.bring.api.tracking.response.TrackingResult;

public class BringServiceTrackingTest {
    private BringParser<TrackingResult> trackingParserMock;
    private BringParser<ShippingGuideResult> shippingGuideParserMock;
    private BringConnection bringConnectionMock;

    @Before
    public void setUp() throws IOException, RequestFailedException {
        bringConnectionMock = mock(BringConnection.class);
        InputStream emptyInputStream = toInputStream("");
        when(bringConnectionMock.openInputStream(anyString())).thenReturn(emptyInputStream);
        when(bringConnectionMock.openInputStream(anyString(), anyMap())).thenReturn(emptyInputStream);

        shippingGuideParserMock = mock(BringParser.class);
        trackingParserMock = mock(BringParser.class);
        when(trackingParserMock.unmarshal(emptyInputStream)).thenReturn(new TrackingResult());
        when(shippingGuideParserMock.unmarshal(emptyInputStream)).thenReturn(new ShippingGuideResult());
    }
    
    @Test
    public void shouldNotSetLoginHeadersOnNormalQuery() throws RequestFailedException {
        BringService bringService = new BringService(bringConnectionMock, shippingGuideParserMock, trackingParserMock);
        bringService.queryTracking(new TrackingQuery("12345"));

        verify(bringConnectionMock).openInputStream(anyString());
        verify(bringConnectionMock, never()).openInputStream(anyString(), anyMap());
    }

    @Test
    public void shouldBeAbleToSetCustomHeaders() throws RequestFailedException {
        ArgumentCaptor<Map> mapArgumentCaptor = ArgumentCaptor.forClass(Map.class);

        BringService bringService = new BringService(bringConnectionMock, shippingGuideParserMock, trackingParserMock);
        bringService.queryTracking(new TrackingQuery("12345"), "username", "apiKey");

        verify(bringConnectionMock, never()).openInputStream(anyString());
        verify(bringConnectionMock).openInputStream(anyString(), mapArgumentCaptor.capture());

        assertEquals(2, mapArgumentCaptor.getValue().keySet().size());
        assertEquals("username", mapArgumentCaptor.getValue().get("X-MyBring-API-Uid"));
        assertEquals("apiKey", mapArgumentCaptor.getValue().get("X-MyBring-API-Key"));
    }
    
    @Test
    public void shouldUseHttpUrlConnectionAdaptorWhenNoOtherIsAvailable() {
        BringService bringService = new BringService("id");
        assertEquals(HttpUrlConnectionAdapter.class, bringService.detectAndLoadConnectionAdapter("test").getClass());
    }
}
