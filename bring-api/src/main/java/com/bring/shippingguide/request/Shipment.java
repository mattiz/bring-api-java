package com.bring.shippingguide.request;

import com.bring.exceptions.ExceededNumberOfPackagesException;
import com.bring.exceptions.MissingParameterException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Arrays.asList;

public class Shipment {

    private static final int MAX_NUMBER_OF_PACKAGES = 10;
    private String fromPostalCode;
    private String toPostalCode;
    private String toCity;
    private String fromCountry;
    private String toCountry;
    private DateTime shippingDateTime;
    private String publicId;
    private boolean edi = true;
    private boolean postingAtPostOffice = false;
    private Set<ProductType> products;
    private Set<AdditionalService> additionalServices;
    private Map<ProductType, PriceAdjustment> priceAdjustments;
    private List<Package> packages;
    private DateTimeFormatter dateFormatter;


    public Shipment() {
        products = new HashSet<ProductType>();
        additionalServices = new HashSet<AdditionalService>();
        priceAdjustments = new HashMap<ProductType,PriceAdjustment>();
        packages = new ArrayList<Package>();
        dateFormatter = DateTimeFormat.forPattern("'&date='yyyy-MM-dd'&time='HH:mm");
    }
    
    /**
     * Required in all requests.
     */
    public Shipment withFromPostalCode(String fromPostalCode) {
        this.fromPostalCode = fromPostalCode;
        return this;
    }
    
    /**
     * Required in all requests.
     */
    public Shipment withToPostalCode(String toPostalCode) {
        this.toPostalCode = toPostalCode;
        return this;
    }

    /**
     * Add product type to query for, otherwise all products are included.
     */
    public Shipment addProduct(ProductType product) {
        products.add(product);
        return this;
    }

    /**
     * Set a java.util.Set of product types to query for.
     */
    public Shipment withProducts(Set<ProductType> products) {
        this.products = products;
        return this;
    }
    
    /**
     * Set a list of product types to query for.
     */
    public Shipment withProducts(ProductType... products) {
        this.products = new LinkedHashSet<ProductType>(asList(products));
        return this;
    }

    /**
     * Set shipping date time if you want a shipping date other than "now".
     */
    public Shipment withShippingDateTime(DateTime shippingDateTime) {
        this.shippingDateTime = shippingDateTime;
        return this;
    }
    
    /**
     * Set to false if the package will not be pre-registered with EDI before Bring handles the package.
     * Default is true.
     */
    public Shipment withEdi(boolean edi) {
        this.edi = edi;
        return this;
    }
    
    /**
     * Flag indicating whether you deliver the package to the post office when it is sent. 
     * Default is false.
     */
    public Shipment withPostingAtPostOffice(boolean postingAtPostOffice) {
        this.postingAtPostOffice = postingAtPostOffice;
        return this;
    }
    
    /**
     * Add additional service code to query price for.
     */
    public Shipment addAdditionalService(AdditionalService additionalService) {
        additionalServices.add(additionalService);
        return this;
    }
    
    /**
     * Add a java.util.Set of additional service codes to query price for.
     */
    public Shipment withAdditionalServices(Set<AdditionalService> additionalServices) {
        this.additionalServices = additionalServices;
        return this;
    }
    
    /**
     * Set public ID which allows Fraktguiden to know who the query comes from.
     */
    public Shipment withPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }
    
    /**
     * Allows you to adjust the prices that are returned. 
     * Announced by setting the map of production descriptions and the corresponding price adjustment.
     * Note that price adjustment is made on the price without VAT.
     */
    public Shipment withPriceAdjustments(Map<ProductType, PriceAdjustment> priceAdjustments) {
        this.priceAdjustments = priceAdjustments;
        return this;
    }
    
    /**
     * Allows you to adjust the prices that are returned for all products. 
     * Note that price adjustment is made on the price without VAT.
     */
    public Shipment addPriceAdjustmentForAllProducts(PriceAdjustment priceAdjustment) {
        priceAdjustments.put(ProductType.ALL, priceAdjustment);
        return this;
    }
    
    /**
     * Allows you to adjust the prices that are returned. 
     * Announced by adding a product description and the corresponding price adjustment.
     * Note that price adjustment is made on the price without VAT.
     */
    public Shipment addPriceAdjustment(PriceAdjustment priceAdjustment, ProductType product) {
        priceAdjustments.put(product, priceAdjustment);
        return this;

    }
    
    /**
     * Set from country.
     * Note that the returned currency is based on the from country.
     */
    public Shipment withFromCountry(String fromCountry) {
        this.fromCountry = fromCountry;
        return this;
    }
    /**
     * Set from country.
     */
    public Shipment withToCountry(String toCountry) {
        this.toCountry = toCountry;
        return this;
    }
    
    /**
     * Set a list with package dimensions.
     * Note that the size limit is 10.
     */
    public Shipment withPackages(List<Package> fraktguidenPackage) {
        failOnToManyPackages(fraktguidenPackage);
        this.packages = fraktguidenPackage;
        return this;
    }
    
    /**
     * Used for sending the package measurements for one or more packages.
     * The size limit is 10.
     */
    public Shipment addPackage(Package fraktguidenPackage) {
        this.packages.add(fraktguidenPackage);
        failOnToManyPackages(packages);
        return this;
    }

    /**
     * Set the city the package should be delivered to.
     * This parameter is optional (only relevant for destination countries without postal codes, e.g. Ireland).
     */
    public Shipment withToCity(String toCity) {
        this.toCity = toCity;
        return this;
    }
    /**
     * Creates query string that is sent to fraktguiden.bring.no
     */
    public String toQueryString() {
        if (fromPostalCode == null || toPostalCode == null) {
            throw new MissingParameterException("To- and from postal code is required.");
        }

        // Postal code
        String str = "?from=" + fromPostalCode + "&to=" + toPostalCode;
        
        // Package dimension
        for (int i = 0; i < packages.size(); i++) {
            str += packages.get(i).toQueryString(Integer.toString(i));
        }
                
        // Date
        if (shippingDateTime != null) {
            str += dateFormatter.print(shippingDateTime);
        }
        
        // Products
        if(!products.contains(ProductType.ALL)){
            for (ProductType product : products) {
                str += "&product=" + product.getQueryName();
            }    
        }
        
        // EDI
        str += "&edi="+edi;
        
        
        // Posting at office
        if(postingAtPostOffice){
            str += "&postingAtPostoffice=" + postingAtPostOffice;
        }
        
        // Additional services
        if(additionalServices.size() > 0){
            for(AdditionalService additionalService : additionalServices) {
                str += "&additional=" + additionalService.getName(); 
            }
        }
        
        // PID
        if (publicId != null) {
            str += "&pid="+ publicId;
        }
        
        // Price adjustments
        for (ProductType product : priceAdjustments.keySet()) {
            str += priceAdjustments.get(product).toQueryString(product);
        }
        
        // To and from country
        if (fromCountry != null) {
            str += "&fromCountry="+fromCountry;
        }
        if (toCountry != null) {
            str += "&toCountry="+toCountry;
        }

        // To city
        if (toCity != null) {
            str += "&toCity="+toCity;
        }

        return str;
    }

    private void failOnToManyPackages(List<Package> list) {
        if(list.size() > MAX_NUMBER_OF_PACKAGES) {
            throw new ExceededNumberOfPackagesException();            
        }
    }
}