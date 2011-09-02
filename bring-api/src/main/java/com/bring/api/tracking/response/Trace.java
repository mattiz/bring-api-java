package com.bring.api.tracking.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TraceType", propOrder = {
    "consignmentSet"
})
public class Trace {

    @XmlElement(name = "ConsignmentSet")
    protected TrackingResult consignmentSet;

    public TrackingResult getConsignmentSet() {
        return consignmentSet;
    }

    public void setConsignmentSet(TrackingResult value) {
        this.consignmentSet = value;
    }

}
