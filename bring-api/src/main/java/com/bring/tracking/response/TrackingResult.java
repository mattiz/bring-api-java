package com.bring.tracking.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "ConsignmentSet")
public class TrackingResult {

    @XmlElement(name = "Status")
    protected List<TraceStatus> status;
    @XmlElement(name = "Consignment", required = true)
    protected List<Consignment> consignments;

    @XmlTransient
    public List<TraceStatus> getStatus() {
        if (status == null) {
            status = new ArrayList<TraceStatus>();
        }
        return new ArrayList<TraceStatus>(status);
    }

    public void setStatus(List<TraceStatus> status) {
        this.status = status;
    }

    @XmlTransient
    public List<Consignment> getConsignments() {
        if (consignments == null) {
            consignments = new ArrayList<Consignment>();
        }
        return new ArrayList<Consignment>(this.consignments);
    }

    public void setConsignments(List<Consignment> consignments) {
        this.consignments = consignments;
    }

    public Consignment getConsignment(int i) {
        return consignments.get(i);
    }
}