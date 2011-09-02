package com.bring.api.shippingguide.response;

import javax.xml.bind.annotation.XmlElement;

public class ExpectedDeliveryDate {
    private String year;
    private String month;
    private String day;
    
    @XmlElement(name="Year")
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    
    @XmlElement(name="Month")
    public String getMonth() {
        return month;
    }
    public void setMonth(String month) {
        this.month = month;
    }
    
    @XmlElement(name="Day")
    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }
    
    public String toString(){
        return year + "-" + month + "-" + day;
    }
}
