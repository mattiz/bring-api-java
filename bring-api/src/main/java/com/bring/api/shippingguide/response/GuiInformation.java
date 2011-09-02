package com.bring.api.shippingguide.response;

import javax.xml.bind.annotation.XmlElement;

public class GuiInformation {
    private String sortOrder;
    private String mainDisplayCategory;
    private String subDisplayCategory;
    private String displayName;
    private String productName;
    private String descriptionText;
    private String helpText;
    private String tip;
    
    @XmlElement(name = "SortOrder")
    public String getSortOrder() {
        return sortOrder;
    }
    
    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
    
    @XmlElement(name = "MainDisplayCategory")
    public String getMainDisplayCategory() {
        return mainDisplayCategory;
    }
    
    public void setMainDisplayCategory(String mainDisplayCategory) {
        this.mainDisplayCategory = mainDisplayCategory;
    }
    
    @XmlElement(name = "DisplayName")
    public String getDisplayName() {
        return displayName;
    }
    
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    
    @XmlElement(name = "ProductName")
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    @XmlElement(name = "DescriptionText")
    public String getDescriptionText() {
        return descriptionText;
    }
    
    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }
    
    @XmlElement(name = "HelpText")
    public String getHelpText() {
        return helpText;
    }
    
    public void setHelpText(String helpText) {
        this.helpText = helpText;
    }
    
    @XmlElement(name = "Tip")
    public String getTip() {
        return tip;
    }
    
    public void setTip(String tip) {
        this.tip = tip;
    }

    @XmlElement(name = "SubDisplayCategory")
    public String getSubDisplayCategory() {
        return subDisplayCategory;
    }
    
    public void setSubDisplayCategory(String subDisplayCategory) {
        this.subDisplayCategory = subDisplayCategory;
    }
}
