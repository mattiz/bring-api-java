package com.bring.tracking.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TraceStatusType", propOrder = {
    "system",
    "code",
    "error"
})
public class TraceStatus {

    @XmlElement(name = "System", required = true)
    protected String system;
    @XmlElement(name = "Code")
    protected int code;
    @XmlElement(name = "Error", required = true)
    protected String error;

    public String getSystem() {
        return system;
    }

    public void setSystem(String value) {
        this.system = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int value) {
        this.code = value;
    }

    public String getError() {
        return error;
    }

    public void setError(String value) {
        this.error = value;
    }

}
