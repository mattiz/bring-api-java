package com.bring.api.booking.response;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;


/**
 * @author Mathias Bjerke
 */
public class Message {
	@XmlAttribute
	private String lang;

	@XmlValue
	private String message;


	public String getMessage() {
		return message;
	}
}
