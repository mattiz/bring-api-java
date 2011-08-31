package com.bring.tracking.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsignmentType", propOrder = {
    "totalWeight",
    "totalVolume",
    "packageSet"
})
public class Consignment {

    @XmlElement(name = "TotalWeight", required = true)
    protected Weight totalWeight;
    @XmlElement(name = "TotalVolume", required = true)
    protected Volume totalVolume;
    @XmlElement(name = "PackageSet")
    protected PackageSet packageSet;
    @XmlAttribute
    protected String consignmentId;

    public Weight getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Weight value) {
        this.totalWeight = value;
    }

    public Volume getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(Volume value) {
        this.totalVolume = value;
    }

    public PackageSet getPackageSet() {
        return packageSet;
    }

    public void setPackageSet(PackageSet value) {
        this.packageSet = value;
    }

    public String getConsignmentId() {
        return consignmentId;
    }

    public void setConsignmentId(String value) {
        this.consignmentId = value;
    }

    public Package getPackage(int i) {
        return packageSet.getPackage(i);
    }

}
