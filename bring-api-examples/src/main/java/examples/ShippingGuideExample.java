package examples;

import com.bring.api.BringService;
import com.bring.api.exceptions.RequestFailedException;
import com.bring.api.shippingguide.request.Package;
import com.bring.api.shippingguide.request.ProductType;
import com.bring.api.shippingguide.request.QueryType;
import com.bring.api.shippingguide.request.Shipment;
import com.bring.api.shippingguide.response.ShippingGuideResult;

public class ShippingGuideExample {
    public ShippingGuideExample() throws RequestFailedException {

        // Initialize library
        String clientId = "www.mywebshop.com";
        BringService bringService = new BringService(clientId);

        // Prepare query
        Package packet = new Package();
        packet.withWeightInGrams("4233");

        Shipment shipment = new Shipment();
        shipment.withFromPostalCode("1409");
        shipment.withToPostalCode("7050");
        shipment.addPackage(packet);
        shipment.addProduct(ProductType.SERVICEPAKKE);

        // Fetch price information from Bring
        ShippingGuideResult shippingGuideResult = bringService.queryShippingGuide(shipment, QueryType.PRICE);
        String amountWithVAT = shippingGuideResult
            .getProduct(ProductType.SERVICEPAKKE)
            .getPrice()
            .getPackagePriceWithoutAdditionalServices()
            .getAmountWithVAT();

        // Display result
        System.out.println("Price: "+amountWithVAT);
    }
}
