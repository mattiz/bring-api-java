package com.bring.tracking.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VolumeType", propOrder = {
    "value"
})
public class Volume {

    @XmlValue
    protected String value;
    @XmlAttribute
    protected String unitCode;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnitCode() {
        if (unitCode == null) {
            return "dm3";
        } else {
            return unitCode;
        }
    }

    public void setUnitCode(String value) {
        this.unitCode = value;
    }

}
