package com.bring.api.booking.request;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author Mathias Bjerke
 */
public class Package {
	@XmlAttribute
	private String correlationId;

	@XmlElement
	private String weightInKg;

	@XmlElement
	private String goodsDescription;

	@XmlElement
	private Dimensions dimensions;


	public Package withCorrelationId( String correlationId ) {
		this.correlationId = correlationId;
		return this;
	}


	public Package withWeightInKg( String weightInKg ) {
		this.weightInKg = weightInKg;
		return this;
	}


	public Package withGoodsDescription( String goodsDescription ) {
		this.goodsDescription = goodsDescription;
		return this;
	}


	public Package withDimensions( Dimensions dimensions ) {
		this.dimensions = dimensions;
		return this;
	}
}
