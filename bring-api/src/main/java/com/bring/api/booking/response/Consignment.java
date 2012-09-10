package com.bring.api.booking.response;


import com.bring.api.booking.dao.BookingDao;
import com.bring.api.exceptions.RequestFailedException;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.io.InputStream;
import java.util.List;


/**
 * @author Mathias Bjerke
 */
public class Consignment {
	@XmlAttribute
	private String correlationId;

	@XmlElement
	private Confirmation confirmation;

	@XmlElement(name = "error")
	@XmlElementWrapper(name = "errors")
	private List<Error> errors;


	public String getCorrelationId() {
		return correlationId;
	}


	public List<Error> getErrors() {
		return errors;
	}


	public String getConsignmentNumber() {
		return confirmation.getConsignmentNumber();
	}


	public List<Package> getPackages() {
		return confirmation.getPackages();
	}


	public InputStream getLabel() throws RequestFailedException {
		return new BookingDao().getLabel( confirmation.getLinks().getLabelURL() );
	}


	public String getEarliestPickup() {
		return confirmation.getEarliestPickup();
	}


	public String getExpectedDelivery() {
		return confirmation.getExpectedDelivery();
	}


	public String getLabelURL() {
		return confirmation.getLabelURL();
	}


	public String getTrackingURL() {
		return confirmation.getTrackingURL();
	}
}
