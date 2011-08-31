package com.bring.tracking.dao;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBException;

import com.bring.BringParser;
import com.bring.connection.BringConnection;
import com.bring.tracking.request.TrackingQuery;
import com.bring.tracking.response.TrackingResult;
import com.bring.tracking.response.Consignment;
import com.bring.tracking.response.Event;
import com.bring.tracking.response.Package;
import com.bring.tracking.response.Signature;
import com.bring.exceptions.RequestFailedException;
import com.bring.exceptions.UnmarshalException;

public class TrackingDao {

    private BringConnection bringConnection;
    private BringParser<TrackingResult> bringParser;

    public TrackingDao(BringConnection connection){
        bringParser = new BringParser<TrackingResult>(TrackingResult.class);
        this.bringConnection = connection;
    }

    public TrackingDao(BringConnection bringConnection, BringParser<TrackingResult> bringParser) {
        this.bringConnection = bringConnection;
        this.bringParser = bringParser;
    }

    public TrackingResult query(TrackingQuery trackingQuery) throws RequestFailedException {
        return query("http://sporing.bring.no/api/tracking.xml", trackingQuery, null);
    }

    public TrackingResult query(TrackingQuery trackingQuery, String apiUserId, String apiKey) throws RequestFailedException {
        Map<String,String> headers = new HashMap<String,String>();
        headers.put("X-MyBring-API-Uid", apiUserId);
        headers.put("X-MyBring-API-Key", apiKey);
        return query("https://www.mybring.com/tracking/api/tracking.xml", trackingQuery, headers);
    }
    
    private TrackingResult query(String baseUrl, TrackingQuery trackingQuery, Map<String, String> headers) throws RequestFailedException {
        String url = baseUrl + trackingQuery.toQueryString();
        InputStream inputStream = null;
        try {
            if (headers == null) {
                inputStream = bringConnection.openInputStream(url);
            }
            else {
                inputStream = bringConnection.openInputStream(url, headers);
            }
            TrackingResult trackingResult = bringParser.unmarshal(inputStream);
            convertSignatureUrlsToFullUrl(trackingResult, baseUrl.substring(0, baseUrl.lastIndexOf('/') + 1));
            return trackingResult;
        }
        catch (UnmarshalException e) {
            throw new RequestFailedException(e);
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (IOException e) {
                    throw new RequestFailedException(e);
                }
            }
        }
    }

    private void convertSignatureUrlsToFullUrl(TrackingResult trackingResult, String urlPrefix) {
        for (Consignment consignment : trackingResult.getConsignments()) {
            for (Package packet : consignment.getPackageSet().getPackages()) {
                for (Event event : packet.getEventSet().getEvents()) {
                    convertToFullUrl(event, urlPrefix);
                }
            }
        }
    }

    private void convertToFullUrl(Event event, String urlPrefix) {
        String url = event.getSignature().getLinkToImage();
        if (url != null && !url.matches("^https?://.*")) {
            event.getSignature().setLinkToImage(urlPrefix + url);
        }
    }

    /**
     * Get the signature image.
     * Caller is responsible for closing stream.
     * 
     * @param signature
     * @return
     * @throws com.bring.exceptions.RequestFailedException
     */
	public InputStream getSignatureImageAsStream(Signature signature, String apiUserId, String apiKey) throws RequestFailedException {
        Map<String,String> headers = new HashMap<String,String>();
        headers.put("X-MyBring-API-Uid", apiUserId);
        headers.put("X-MyBring-API-Key", apiKey);
		return bringConnection.openInputStream(signature.getLinkToImage(), headers);
	}
	
	public InputStream getSignatureImageAsStream(Signature signature) throws RequestFailedException {
		return bringConnection.openInputStream(signature.getLinkToImage());
	}
}