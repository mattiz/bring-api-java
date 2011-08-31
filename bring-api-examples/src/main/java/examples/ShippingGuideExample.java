package examples;

import com.bring.BringService;
import com.bring.exceptions.RequestFailedException;
import com.bring.shippingguide.request.Package;
import com.bring.shippingguide.request.ProductType;
import com.bring.shippingguide.request.QueryType;
import com.bring.shippingguide.request.Shipment;
import com.bring.shippingguide.response.ShippingGuideResult;

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
