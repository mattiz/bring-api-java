package com.bring.api.shippingguide.response;

import javax.xml.bind.annotation.XmlElement;

public class Product {

    private String productId;
    private Price price;
    private ExpectedDelivery expectedDelivery;
    private GuiInformation guiInformation;
    private String productCodeInProductionSystem;

    @XmlElement(name = "ProductId")
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @XmlElement(name = "Price")
    public Price getPrice() {
        return price;
    }
    
    public void setPrice(Price price) {
        this.price = price;
    }
    
    @XmlElement(name = "ExpectedDelivery")
    public ExpectedDelivery getExpectedDelivery() {
        return expectedDelivery;
    }
    
    public void setExpectedDelivery(ExpectedDelivery expectedDelivery){
        this.expectedDelivery = expectedDelivery;
    }

    @XmlElement(name = "GuiInformation")
    public GuiInformation getGuiInformation() {
        return guiInformation;
    }

    public void setGuiInformation(GuiInformation guiInformation) {
        this.guiInformation = guiInformation;
    }

    @XmlElement(name = "ProductCodeInProductionSystem")
    public String getProductCodeInProductionSystem() {
        return productCodeInProductionSystem;
    }

    public void setProductCodeInProductionSystem(
            String productCodeInProductionSystem) {
        this.productCodeInProductionSystem = productCodeInProductionSystem;
    }

}
