package com.bring.api.booking.response.error;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;


/**
 * @author Mathias Bjerke
 */
public class Error {
	@XmlElement
	private String uniqueId;

	@XmlElement
	private String code;

	@XmlElement(name = "message")
	@XmlElementWrapper(name = "messages")
	private List<Message> messages;
}
