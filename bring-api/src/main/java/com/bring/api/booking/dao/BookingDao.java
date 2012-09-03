package com.bring.api.booking.dao;


import com.bring.api.booking.BringParser;
import com.bring.api.booking.request.BookingRequest;
import com.bring.api.booking.response.BookingResponse;
import com.bring.api.connection.BringConnection;
import com.bring.api.exceptions.RequestFailedException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import java.net.URI;


public class BookingDao {
	private BringConnection bringConnection;
	private BringParser<BookingRequest> bringParser;


	public BookingDao() {
	}


	public BookingResponse book( BookingRequest bookingRequest, String apiUserId, String apiKey ) throws RequestFailedException {
		return sendBookingRequest( bookingRequest, apiUserId, apiKey );
	}


	private BookingResponse sendBookingRequest( BookingRequest request, String apiUserId, String apiKey ) {
		byte[] xmlRequest;
		HttpResponse httpResponse;
		BookingResponse bookingResponse = null;

		try {
			xmlRequest = new BringParser<BookingRequest>( BookingRequest.class ).marshal( request );

			httpResponse = doPostRequest( xmlRequest, apiUserId, apiKey );

			bookingResponse = new BringParser<BookingResponse>(BookingResponse.class).unmarshal( httpResponse.getEntity().getContent() );
			bookingResponse.setHttpResponseCode( httpResponse.getStatusLine().getStatusCode() );

		} catch( Exception e ) {
			e.printStackTrace();
		}

		return bookingResponse;
	}


	private HttpResponse doPostRequest( byte[] postData, String apiUserId, String apiKey ) {
		HttpClient httpClient;
		HttpEntity entity;
		HttpPost httpPost;
		HttpResponse httpResponse = null;

		try {
			String url = "https://www.mybring.com/booking/api/booking";

			httpClient = new DefaultHttpClient();

			entity = new ByteArrayEntity( postData );

			httpPost = new HttpPost( new URI( url ) );
			httpPost.setEntity( entity );
			httpPost.setHeader( "Content-Type", "application/xml" );
			httpPost.setHeader( "Accept", "application/xml" );
			httpPost.setHeader( "X-MyBring-API-Uid", apiUserId );
			httpPost.setHeader( "X-MyBring-API-Key", apiKey );

			httpResponse = httpClient.execute(httpPost);

		} catch( Exception e ) {
			e.printStackTrace();
		}

		return httpResponse;
	}
}