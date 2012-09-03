package com.bring.api.booking.request;


import javax.xml.bind.annotation.XmlElement;


/**
 * @author Mathias Bjerke
 */
public class Contact {
	@XmlElement
	private String name;

	@XmlElement
	private String email;

	@XmlElement
	private String phoneNumber;


	public Contact withName( String name ) {
		this.name = name;
		return this;
	}


	public Contact withEmail( String email ) {
		this.email = email;
		return this;
	}


	public Contact withPhoneNumber( String phoneNumber ) {
		this.phoneNumber = phoneNumber;
		return this;
	}
}
