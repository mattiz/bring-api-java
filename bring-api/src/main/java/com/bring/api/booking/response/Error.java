package com.bring.api.booking.response;


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


	@Override
	public String toString() {
		StringBuilder summary = new StringBuilder();

		summary.append( code );

		for( Message message : messages ) {
			summary.append( ", " );
			summary.append( message.getMessage() );
		}
		return summary.toString();
	}
}
