package com.bring.tracking.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdditionalServiceType", propOrder = {
    "id",
    "description",
    "amount",
    "currencyCode"
})
public class AdditionalService {

    @XmlElement(name = "Id", required = true)
    protected String id;
    @XmlElement(name = "Description", required = true)
    protected String description;
    @XmlElement(name = "Amount", required = true)
    protected String amount;
    @XmlElement(name = "CurrencyCode", required = true)
    protected String currencyCode;

    public String getId() {
        return id;
    }

    public void setId(String value) {
        this.id = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String value) {
        this.amount = value;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String value) {
        this.currencyCode = value;
    }

}
