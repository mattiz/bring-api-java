package com.bring.api.booking.request;


import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Mathias Bjerke
 */
public class Consignment {
	@XmlAttribute
	private String correlationId;

	@XmlElement
	private String shippingDateTime;

	@XmlElements({
			@XmlElement(name="sender", type=Sender.class),
			@XmlElement(name="recipient", type=Recipient.class)
	})
	@XmlElementWrapper(name = "parties")
	private List<Party> parties;

	@XmlElement
	private Product product;

	@XmlElement(name = "package")
	@XmlElementWrapper(name = "packages")
	private List<Package> packages;


	public Consignment() {
		parties = new ArrayList<Party>();
		packages = new ArrayList<Package>();
	}


	public Consignment withCorrelationId( String correlationId ) {
		this.correlationId = correlationId;
		return this;
	}


	public Consignment withShippingDateTime( String shippingDateTime ) {
		this.shippingDateTime = shippingDateTime;
		return this;
	}


	public Consignment addParty( Party party ) {
		parties.add( party );
		return this;
	}


	public Consignment withProduct( Product product ) {
		this.product = product;
		return this;
	}


	public Consignment addPackage( Package pack ) {
		packages.add( pack );
		return this;
	}
}
