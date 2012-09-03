package com.bring.api.booking.request;


import javax.xml.bind.annotation.XmlElement;


/**
 * @author Mathias Bjerke
 */
public class Service {
	@XmlElement
	private RecipientNotification recipientNotification;


	public Service withRecipientNotification( RecipientNotification recipientNotification ) {
		this.recipientNotification = recipientNotification;
		return this;
	}
}
