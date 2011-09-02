package com.bring.api.tracking.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EventType", propOrder = {
    "description",
    "status",
        "signature",
    "unitId",
    "postalCode",
    "city",
    "countryCode",
    "country",
    "occuredAtIsoDateTime",
    "occuredAtDisplayDate",
    "occuredAtDisplayTime",
    "consignmentEvent"
})
public class Event {

    @XmlElement(name = "Description", required = true)
    protected String description;
    @XmlElement(name = "Status", required = true)
    protected String status;
    @XmlElement(name = "RecipientSignature", required = true)
    protected Signature signature;
    @XmlElement(name = "UnitId", required = true)
    protected String unitId;
    @XmlElement(name = "PostalCode", required = true)
    protected String postalCode;
    @XmlElement(name = "City", required = true)
    protected String city;
    @XmlElement(name = "CountryCode", required = true)
    protected String countryCode;
    @XmlElement(name = "Country", required = true)
    protected String country;
    @XmlElement(name = "OccuredAtIsoDateTime", required = true)
    protected String occuredAtIsoDateTime;
    @XmlElement(name = "OccuredAtDisplayDate", required = true)
    protected String occuredAtDisplayDate;
    @XmlElement(name = "OccuredAtDisplayTime", required = true)
    protected String occuredAtDisplayTime;
    @XmlElement(name = "ConsignmentEvent")
    protected boolean consignmentEvent;

    public String getDescription() {
        return description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String value) {
        this.status = value;
    }

    public Signature getSignature() {
        return signature;
    }

    public void setSignature(Signature value) {
        this.signature = value;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String value) {
        this.unitId = value;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String value) {
        this.postalCode = value;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String value) {
        this.city = value;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String value) {
        this.countryCode = value;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String value) {
        this.country = value;
    }

    public String getOccuredAtIsoDateTime() {
        return occuredAtIsoDateTime;
    }
    public void setOccuredAtIsoDateTime(String value) {
        this.occuredAtIsoDateTime = value;
    }

    public String getOccuredAtDisplayDate() {
        return occuredAtDisplayDate;
    }

    public void setOccuredAtDisplayDate(String value) {
        this.occuredAtDisplayDate = value;
    }

    public String getOccuredAtDisplayTime() {
        return occuredAtDisplayTime;
    }

    public void setOccuredAtDisplayTime(String value) {
        this.occuredAtDisplayTime = value;
    }

    public boolean isConsignmentEvent() {
        return consignmentEvent;
    }

    public void setConsignmentEvent(boolean value) {
        this.consignmentEvent = value;
    }

}
