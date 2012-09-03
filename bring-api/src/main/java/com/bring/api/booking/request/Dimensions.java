package com.bring.api.booking.request;


import javax.xml.bind.annotation.XmlElement;


/**
 * @author Mathias Bjerke
 */
public class Dimensions {
	@XmlElement
	private String heightInCm;

	@XmlElement
	private String widthInCm;

	@XmlElement
	private String lengthInCm;


	public Dimensions withHeightInCm( String heightInCm ) {
		this.heightInCm = heightInCm;
		return this;
	}


	public Dimensions withWidthInCm( String widthInCm ) {
		this.widthInCm = widthInCm;
		return this;
	}


	public Dimensions withLengthInCm( String lengthInCm ) {
		this.lengthInCm = lengthInCm;
		return this;
	}
}
