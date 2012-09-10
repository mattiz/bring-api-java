package com.bring.api.booking.response;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;


/**
 * @author Mathias Bjerke
 */
public class Package {
	@XmlAttribute
	private String correlationId;

	@XmlElement
	private String packageNumber;


	public String getPackageNumber() {
		return packageNumber;
	}
}
