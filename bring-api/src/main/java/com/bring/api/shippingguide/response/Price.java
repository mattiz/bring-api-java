package com.bring.api.shippingguide.response;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Price {
    private String productId;
    private String currencyIdentificationCode;
    private PackagePriceWithoutAdditionalServices packagePriceWithoutAdditionalServices;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @XmlAttribute(name = "currencyIdentificationCode")
    public String getCurrencyIdentificationCode() {
        return currencyIdentificationCode;
    }

    public void setCurrencyIdentificationCode(String currencyIdentificationCode) {
        this.currencyIdentificationCode = currencyIdentificationCode;
    }

    @XmlElement(name = "PackagePriceWithoutAdditionalServices")
    public PackagePriceWithoutAdditionalServices getPackagePriceWithoutAdditionalServices() {
        return packagePriceWithoutAdditionalServices;
    }
    
    public void setPackagePriceWithoutAdditionalServices(
            PackagePriceWithoutAdditionalServices packagePriceWithoutAdditionalServices) {
        this.packagePriceWithoutAdditionalServices = packagePriceWithoutAdditionalServices;
    }

}