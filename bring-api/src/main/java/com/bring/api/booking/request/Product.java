package com.bring.api.booking.request;


import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Mathias Bjerke
 */
public class Product {
	@XmlElement
	private String id;

	@XmlElement
	private String customerNumber;

	@XmlElement
	private List<Service> services;


	public Product() {
		services = new ArrayList<Service>();
	}


	public Product withId( String id ) {
		this.id = id;
		return this;
	}


	public Product withCustomerNumber( String customerNumber ) {
		this.customerNumber = customerNumber;
		return this;
	}


	public Product addService( Service service ) {
		services.add( service );
		return this;
	}
}
