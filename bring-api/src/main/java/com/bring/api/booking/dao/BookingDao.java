package com.bring.api.booking.dao;


import com.bring.api.BringParser;
import com.bring.api.booking.request.BookingRequest;
import com.bring.api.booking.response.BookingResponse;
import com.bring.api.connection.BringConnection;


public class BookingDao {
    private BringConnection bringConnection;
    private BringParser<BookingResponse> bringParser;


    public BookingDao( BringConnection bringConnection ){
        this.bringParser = new BringParser<BookingResponse>(BookingResponse.class);
        this.bringConnection = bringConnection;
    }


    public BookingDao( BringConnection bringConnection, BringParser<BookingResponse> bringParser ) {
        this.bringConnection = bringConnection;
        this.bringParser = bringParser;
    }


	public BookingResponse book( BookingRequest bookingRequest, String apiUserId, String apiKey ) {
		return null;
	}
}