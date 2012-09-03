package com.bring.api;

import com.bring.api.booking.dao.BookingDao;
import com.bring.api.booking.request.BookingRequest;
import com.bring.api.booking.response.BookingResponse;
import com.bring.api.connection.BringConnection;
import com.bring.api.connection.HttpUrlConnectionAdapter;
import com.bring.api.exceptions.CouldNotLoadBringConnectionAdapterException;
import com.bring.api.exceptions.RequestFailedException;
import com.bring.api.shippingguide.dao.ShippingGuideDao;
import com.bring.api.shippingguide.request.QueryType;
import com.bring.api.shippingguide.request.Shipment;
import com.bring.api.shippingguide.response.ShippingGuideResult;
import com.bring.api.tracking.dao.TrackingDao;
import com.bring.api.tracking.request.TrackingQuery;
import com.bring.api.tracking.response.Signature;
import com.bring.api.tracking.response.TrackingResult;
import java.io.InputStream;
import java.lang.reflect.Constructor;

/**
 * Service containing all Bring APIs.
 */
public class BringService {

    ShippingGuideDao shippingGuideDao;
    TrackingDao trackingDao;
	BookingDao bookingDao;
    
    /**
     * Constructor that autodetects available BringConnections (i.e. chooses automatically
     * between UrlConnection, HttpClient 3 and HttpClient 4 depending on what's on the classpath.
     * HttpClient 3 and HttpClient 4 are sub-modules that must be included).
     * If no extra BringConnection sub-modules are loaded, then UrlConnection is used as default.
     *
     * @param clientIdentifier Unique string that identifies the caller. Domain of the web site is preferred.
     */
    public BringService(String clientIdentifier) {
        initDaos(detectAndLoadConnectionAdapter(clientIdentifier));
    }
    
    /**
     * Constructor that accepts a custom BringConnection implementation.
     *
     * @param bringConnection Custom BringConnection
     */
    public BringService(BringConnection bringConnection) {
        initDaos(bringConnection);
    }
    
    /**
     * Constructor that accepts a custom BringConnection and a custom BringParser.
     *
     * @param bringConnection Custom BringConnection
     */
    public BringService(BringConnection bringConnection, BringParser<ShippingGuideResult> shippingGuideParser, BringParser<TrackingResult> trackingParser) {
        initDaos(bringConnection, shippingGuideParser, trackingParser);
    }

    /**
     * Queries Bring Fraktguide for products available for passed in Shipment instance.
     *
     * @param shipment Parameters for the request
     * @param queryType Used to limit search (faster requests)
     * @return ShippingGuideResult Product prices, expected delivery, trace messages etc.
     * @throws RequestFailedException
     */
    public ShippingGuideResult queryShippingGuide(Shipment shipment, QueryType queryType) throws RequestFailedException {
        return shippingGuideDao.query(shipment, queryType);
    }

    /**
     * Queries Bring Tracking for passed in query.
     *
     * @param trackingQuery Search parameters (reference-, transmission-, or package number)
     * @return TrackingResult Tracking data
     * @throws RequestFailedException
     */
    public TrackingResult queryTracking(TrackingQuery trackingQuery) throws RequestFailedException {
        return trackingDao.query(trackingQuery);
    }
    
    /**
     * Queries Bring Tracking for passed in query using given API userID and key.
     * Returns more info than open (non-logged in) query.
     *
     * @param trackingQuery Search parameters (reference-, transmission-, or package number)
     * @param apiUserId API user identity
     * @param apiKey API key
     * @return TrackingResult Tracking data for authenticated users
     * @throws RequestFailedException
     */
    public TrackingResult queryTracking(TrackingQuery trackingQuery, String apiUserId, String apiKey) throws RequestFailedException {
        return trackingDao.query(trackingQuery, apiUserId, apiKey);
    }

	/**
	 * Book a shipment using the Bring booking API.
	 * Requires user id and api key.
	 *
	 * @param bookingRequest The shipment you want to book.
	 * @param apiUserId API user identity
	 * @param apiKey API key
	 * @return BookingResponse Response object containing shipment numbers, pick-up date and link to labels
	 * @throws RequestFailedException
	 */
	public BookingResponse bookShipment(BookingRequest bookingRequest, String apiUserId, String apiKey) throws RequestFailedException {
		bookingDao = new BookingDao();
        return bookingDao.book( bookingRequest, apiUserId, apiKey );
    }
    
    /**
     * Query for signature image.
     * Caller is responsible for closing stream.
     * 
     * @param recipentSignature received from queryTracking
     * @return InputStream (used to access signature images)
     * @throws RequestFailedException
     */
    public InputStream getSignatureImageAsStream(Signature recipentSignature) throws RequestFailedException {
        return trackingDao.getSignatureImageAsStream(recipentSignature);
    }
    
    /**
     * Query for signature image for passed in query using given API userID and key.
     * The signature must exist to be downloaded.
     * Caller is responsible for closing stream.
     * 
     * @param recipentSignature received from queryTracking
     * @return InputStream (used to access signature images)
     * @throws RequestFailedException
     */
    public InputStream getSignatureImageAsStream(Signature recipentSignature, String apiUserId, String apiKey) throws RequestFailedException {
        return trackingDao.getSignatureImageAsStream(recipentSignature, apiUserId, apiKey);
    }
    
    protected BringConnection detectAndLoadConnectionAdapter(String clientIdentifier) {
        BringConnection bringConnection;

        try {
            bringConnection = loadConnectionAdapter("com.bring.api.connection.HttpClient4Adapter", clientIdentifier);
        }
        catch (CouldNotLoadBringConnectionAdapterException e1) {
            try {
                bringConnection = loadConnectionAdapter("com.bring.api.connection.HttpClient3Adapter", clientIdentifier);
            }
            catch (CouldNotLoadBringConnectionAdapterException e2) {
                bringConnection = new HttpUrlConnectionAdapter(clientIdentifier);
            }
        }
        return bringConnection;
    }
    
    protected BringConnection loadConnectionAdapter(String className, String identifier) {
        try {
            Constructor constructor = Class.forName(className).getConstructor(String.class);
            return (BringConnection) constructor.newInstance(identifier);
        }
        catch (Exception e) {
            throw new CouldNotLoadBringConnectionAdapterException(e);
        }
    }
    
    private void initDaos(BringConnection conn) {
        shippingGuideDao = new ShippingGuideDao(conn);
        trackingDao = new TrackingDao(conn);
    }

    private void initDaos(BringConnection bringConnection, BringParser<ShippingGuideResult> shippingGuideParser, BringParser<TrackingResult> trackingParser) {
        shippingGuideDao = new ShippingGuideDao(bringConnection, shippingGuideParser);
        trackingDao = new TrackingDao(bringConnection, trackingParser);
    }
}