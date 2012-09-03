package com.bring.api.booking.request;


import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement
public class BookingRequest {
	@XmlAttribute
	private boolean testIndicator;

	@XmlElement
	private String schemaVersion;

	@XmlElement(name = "consignment")
	@XmlElementWrapper(name = "consignments")
	private List<Consignment> consignments;


	public BookingRequest() {
		consignments = new ArrayList<Consignment>();
	}


	public BookingRequest withTestIndicator( boolean testIndicator ) {
		this.testIndicator = testIndicator;
		return this;
	}


	public BookingRequest withSchemaVersion( String schemaVersion ) {
		this.schemaVersion = schemaVersion;
		return this;
	}


	public BookingRequest addConsignments( Consignment consignment ) {
		consignments.add( consignment );
		return this;
	}
}
