package com.bring.api.booking.response.error;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement
public class BookingResponse {
	@XmlElement(name = "consignment")
	@XmlElementWrapper(name = "consignments")
	private List<Consignment> consignments;
}
