package com.bring.api.booking.request;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author Mathias Bjerke
 */
public class Party {
	@XmlElement
	private String name;

	@XmlElement
	private String addressLine;

	@XmlElement
	private String postalCode;

	@XmlElement
	private String city;

	@XmlElement
	private String countryCode;

	@XmlElement
	private String reference;

	@XmlElement
	private String additionalAddressInfo;

	@XmlElement
	private Contact contact;


	public Party withName( String name ) {
		this.name = name;
		return this;
	}


	public Party withAddressLine( String addressLine ) {
		this.addressLine = addressLine;
		return this;
	}


	public Party withPostalCode( String postalCode ) {
		this.postalCode = postalCode;
		return this;
	}


	public Party withCity( String city ) {
		this.city = city;
		return this;
	}


	public Party withCountryCode( String countryCode ) {
		this.countryCode = countryCode;
		return this;
	}


	public Party withReference( String reference ) {
		this.reference = reference;
		return this;
	}


	public Party withAdditionalAddressInfo( String additionalAddressInfo ) {
		this.additionalAddressInfo = additionalAddressInfo;
		return this;
	}


	public Party withContact( Contact contact ) {
		this.contact = contact;
		return this;
	}
}
