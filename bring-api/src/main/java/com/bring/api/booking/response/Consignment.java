package com.bring.api.booking.response;


import com.bring.api.booking.dao.BookingDao;
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


	public InputStream getLabel() {
		return new BookingDao().getLabel( confirmation.getLinks().getLabels() );
	}
}
