package com.bring.api.booking.response;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
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
}
