package com.bring.api.booking.response;


import javax.xml.bind.annotation.XmlElement;


/**
 * @author Mathias Bjerke
 */
public class DateAndTime {
	@XmlElement
	private String earliestPickup;

	@XmlElement
	private String expectedDelivery;


	public String getEarliestPickup() {
		return earliestPickup;
	}


	public String getExpectedDelivery() {
		return expectedDelivery;
	}
}
