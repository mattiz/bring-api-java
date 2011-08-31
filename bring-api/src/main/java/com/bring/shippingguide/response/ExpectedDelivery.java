package com.bring.shippingguide.response;

import javax.xml.bind.annotation.XmlElement;

public class ExpectedDelivery {
    private String workingDays;
    private String userMessage;
    private String formattedExpectedDeliveryDate;
    private ExpectedDeliveryDate expectedDeliveryDate;
    private AlternativeDeliveryDates alternativeDeliveryDates;


    @XmlElement(name="WorkingDays")
    public String getWorkingDays() {
        return workingDays;
    }
    
    public void setWorkingDays(String workingDays) {
        this.workingDays = workingDays;
    }
    
    @XmlElement(name="UserMessage")
    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    /**
     * This will return null if setDate is not specified in the shipment description. 
     */
    @XmlElement(name="FormattedExpectedDeliveryDate")
    public String getFormattedExpectedDeliveryDate() {
        return formattedExpectedDeliveryDate;
    }
    
    public void setFormattedExpectedDeliveryDate(
            String formattedExpectedDeliveryDate) {
        this.formattedExpectedDeliveryDate = formattedExpectedDeliveryDate;
    }

    @XmlElement(name="ExpectedDeliveryDate")
    public ExpectedDeliveryDate getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(ExpectedDeliveryDate expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    @XmlElement(name="AlternativeDeliveryDates")
    public AlternativeDeliveryDates getAlternativeDeliveryDates() {
        return alternativeDeliveryDates;
    }
    
    public void setAlternativeDeliveryDates(AlternativeDeliveryDates alternativeDeliveryDates) {
        this.alternativeDeliveryDates = alternativeDeliveryDates;
    }
}
