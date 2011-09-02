package com.bring.api.tracking.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.bring.api.BringParser;
import com.bring.api.connection.BringConnection;
import com.bring.api.connection.HttpUrlConnectionAdapter;
import com.bring.api.exceptions.RequestFailedException;
import com.bring.api.exceptions.UnmarshalException;
import com.bring.api.tracking.request.TrackingQuery;
import com.bring.api.tracking.response.Consignment;
import com.bring.api.tracking.response.Signature;
import com.bring.api.tracking.response.TrackingResult;

public class TrackingDaoTest {
    private BringConnection bringConnectionMock;
    private BringConnection bringConnectionMock2;
    private final HttpURLConnection connectionMock = mock(HttpURLConnection.class);
    private TrackingResult trackingResultMock;
    BringParser<TrackingResult> bringParserMock;
    TrackingDao dao;

    @Before
    public void setUp() throws IOException{
        bringConnectionMock2 = mock(BringConnection.class);
        bringConnectionMock = new HttpUrlConnectionAdapter("test") {
            @Override
            protected HttpURLConnection openConnection(URL url) throws IOException {
                return connectionMock;
            }
        };
        when(connectionMock.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);
        
        trackingResultMock = mock(TrackingResult.class);
        when(trackingResultMock.getConsignments()).thenReturn(new ArrayList<Consignment>());
        
        bringParserMock = mock(BringParser.class);
        when(bringParserMock.unmarshal((InputStream) any())).thenReturn(trackingResultMock);
        
        dao = new TrackingDao(bringConnectionMock, bringParserMock);
    }
    
    @Test
    public void shouldNotSetLoginHeadersOnNormalFind() throws RequestFailedException, UnmarshalException {
        dao.query(new TrackingQuery("123456"));
        verify(connectionMock, never()).addRequestProperty("X-MyBring-API-Uid",eq(anyString()));
        verify(connectionMock, never()).addRequestProperty("X-MyBring-API-Key",eq(anyString()));
    }
    
    @Test
    public void shouldBeAbleToSetCustomHeaders() throws RequestFailedException, UnmarshalException {
        dao.query(new TrackingQuery("123456"), "username", "apiKey");
        verify(connectionMock).addRequestProperty("X-MyBring-API-Uid","username");
        verify(connectionMock).addRequestProperty("X-MyBring-API-Key","apiKey");
    }
    
    @Test
    public void shouldBeAbleToDownloadSignatureImage() throws RequestFailedException, IOException {
        TrackingDao trackingDao = new TrackingDao(bringConnectionMock);
        Signature signatureMock = mock(Signature.class);
        URL signatureUrl = getClass().getResource("signature.png");
        when(bringConnectionMock.openInputStream(signatureUrl.toString())).thenReturn(signatureUrl.openStream());
        when(signatureMock.getLinkToImage()).thenReturn(signatureUrl.toString());
        InputStream signatureStream = trackingDao.getSignatureImageAsStream(signatureMock);
        assertNotNull(signatureStream);
        assertTrue(signatureStream instanceof InputStream);
    }
    
    @Test(expected = RequestFailedException.class)
    public void shouldThrowFailedRequestIfSignatureDoesNotExist() throws RequestFailedException, IOException {
        TrackingDao trackingDao = new TrackingDao(bringConnectionMock2);
        Signature signatureMock = mock(Signature.class);
        URL signatureUrl = new URL("file://BringSignatureMockImageThatDoesNotExist.png");
        when(bringConnectionMock2.openInputStream(signatureUrl.toString())).thenThrow(new RequestFailedException());
//      Mockito.doThrow(new RequestFailedException()).when(bringConnectionMock).openInputStream(signatureUrl.toString());
        when(signatureMock.getLinkToImage()).thenReturn(signatureUrl.toString());
        InputStream signatureStream = trackingDao.getSignatureImageAsStream(signatureMock);
    }
}