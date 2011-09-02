package com.bring.api.shippingguide.response;

import com.bring.api.shippingguide.request.ProductType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlRootElement(name = "Package")
public class ShippingGuideResult {
    private List<Product> products = new ArrayList<Product>();
    private Map<ProductType, Product> productsMap = null;
    private String packageId;
    private TraceMessages traceMessages;
    
    public Map<ProductType,Product> getProducts(){
        if(productsMap == null) {
            productsMap = new HashMap<ProductType,Product>();
            for (Product product : products) {
                productsMap.put(ProductType.createFromQueryName(product.getProductId()), product);
            }
        }
        return new HashMap<ProductType,Product>(productsMap);
    }

    public Product getProduct(ProductType productDesc){
        return getProducts().get(productDesc);
    }

    public List<Product> getProductList() {
        return this.products;
    }

    @XmlElement(name = "Product")
    public void setProductList(List<Product> products) {
        this.products = products;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    @XmlElement(name = "TraceMessages")
    public TraceMessages getTraceMessages() {
        return traceMessages;
    }

    public void setTraceMessages(TraceMessages traceMessages) {
        this.traceMessages = traceMessages;
    }
}
