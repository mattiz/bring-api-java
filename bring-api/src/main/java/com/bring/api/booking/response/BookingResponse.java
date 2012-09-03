package com.bring.api.booking.response;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement
public class BookingResponse {
	@XmlElement(name = "consignment")
	@XmlElementWrapper(name = "consignments")
	private List<Consignment> consignments;

	private int httpResponseCode;


	public void setHttpResponseCode( int httpResponseCode ) {
		this.httpResponseCode = httpResponseCode;
	}


	public boolean hasErrors() {
		return httpResponseCode != 200;
	}


	public List<Consignment> getConsignments() {
		return consignments;
	}
}
