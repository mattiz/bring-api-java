package com.bring.api.tracking.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RecipientSignatureType", propOrder = {
    "name",
    "linkToImage"
})
public class Signature {

    @XmlElement(name = "Name", required = true)
    protected String name;

    @XmlElement(name = "LinkToImage", required = true)
    protected String linkToImage;

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getLinkToImage() {
        return linkToImage;
    }

    public void setLinkToImage(String value) {
        this.linkToImage = value;
    }

}