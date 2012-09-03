package com.bring.api.booking.response.error;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;


/**
 * @author Mathias Bjerke
 */
public class Consignment {
	@XmlElement(name = "error")
	@XmlElementWrapper(name = "errors")
	private List<Error> errors;
}
