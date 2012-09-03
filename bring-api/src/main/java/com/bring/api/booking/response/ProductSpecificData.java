package com.bring.api.booking.response;


import javax.xml.bind.annotation.XmlElement;


/**
 * @author Mathias Bjerke
 */
public class ProductSpecificData {
	@XmlElement
	private String upsShipmentNumber;

	@XmlElement
	private String upsTrackingNumber;
}
