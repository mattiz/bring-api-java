package com.bring.api.booking.response;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;


/**
 * @author Mathias Bjerke
 */
public class Confirmation {
	@XmlElement
	private String consignmentNumber;

	@XmlElement
	private ProductSpecificData productSpecificData;

	@XmlElement
	private Links links;

	@XmlElement
	private DateAndTime dateAndTimes;

	@XmlElement(name = "package")
	@XmlElementWrapper(name = "packages")
	private List<Package> packages;


	public String getConsignmentNumber() {
		return consignmentNumber;
	}


	public List<Package> getPackages() {
		return packages;
	}


	public Links getLinks() {
		return links;
	}


	public String getEarliestPickup() {
		return dateAndTimes.getEarliestPickup();
	}


	public String getExpectedDelivery() {
		return dateAndTimes.getExpectedDelivery();
	}


	public String getLabelURL() {
		return links.getLabelURL();
	}


	public String getTrackingURL() {
		return links.getTrackingURL();
	}
}
