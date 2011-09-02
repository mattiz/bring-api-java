package com.bring.api.shippingguide.response;

import javax.xml.bind.annotation.XmlElement;

public class PackagePriceWithoutAdditionalServices {
    private String amountWithoutVAT;
    private String VAT;
    private String amountWithVAT;

    @XmlElement(name = "AmountWithoutVAT")
    public String getAmountWithoutVAT() {
        return amountWithoutVAT;
    }

    public void setAmountWithoutVAT(String amountWithoutVAT) {
        this.amountWithoutVAT = amountWithoutVAT;
    }

    @XmlElement(name = "VAT")
    public String getVAT() {
        return VAT;
    }

    public void setVAT(String VAT) {
        this.VAT = VAT;
    }

    @XmlElement(name = "AmountWithVAT")
    public String getAmountWithVAT() {
        return amountWithVAT;
    }

    public void setAmountWithVAT(String amountWithVAT) {
        this.amountWithVAT = amountWithVAT;
    }
}
