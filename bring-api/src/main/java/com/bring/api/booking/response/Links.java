package com.bring.api.booking.response;


import javax.xml.bind.annotation.XmlElement;


/**
 * @author Mathias Bjerke
 */
public class Links {
	@XmlElement
	private String labels;

	@XmlElement
	private String tracking;


	public String getLabels() {
		return labels;
	}
}
