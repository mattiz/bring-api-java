package com.bring.tracking.response;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ConsignmentSet")
public class TrackingResult {

    @XmlElement(name = "Status")
    protected List<TraceStatus> status;
    @XmlElement(name = "Consignment", required = true)
    protected List<Consignment> consignments;

    public List<TraceStatus> getStatus() {
        if (status == null) {
            status = new ArrayList<TraceStatus>();
        }
        return new ArrayList<TraceStatus>(status);
    }

    public List<Consignment> getConsignments() {
        if (consignments == null) {
            consignments = new ArrayList<Consignment>();
        }
        return new ArrayList<Consignment>(this.consignments);
    }

    public Consignment getConsignment(int i) {
        return consignments.get(i);
    }
}