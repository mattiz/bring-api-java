package com.bring.api.booking.request;


import javax.xml.bind.annotation.XmlElement;


/**
 * @author Mathias Bjerke
 */
public class RecipientNotification {
	@XmlElement
	private String email;

	@XmlElement
	private String mobile;


	public RecipientNotification withEmail( String email ) {
		this.email = email;
		return this;
	}


	public RecipientNotification withMobile( String mobile ) {
		this.mobile = mobile;
		return this;
	}
}
