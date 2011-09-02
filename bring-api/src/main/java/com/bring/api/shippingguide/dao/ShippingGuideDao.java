package com.bring.api.shippingguide.dao;

import java.io.IOException;
import java.io.InputStream;

import com.bring.api.BringParser;
import com.bring.api.connection.BringConnection;
import com.bring.api.exceptions.RequestFailedException;
import com.bring.api.exceptions.UnmarshalException;
import com.bring.api.shippingguide.request.QueryType;
import com.bring.api.shippingguide.request.Shipment;
import com.bring.api.shippingguide.response.ShippingGuideResult;

public class ShippingGuideDao {

    private BringConnection bringConnection;
    private BringParser<ShippingGuideResult> bringParser;

    public ShippingGuideDao(BringConnection bringConnection){
        this.bringParser = new BringParser<ShippingGuideResult>(ShippingGuideResult.class);
        this.bringConnection = bringConnection;
    }

    public ShippingGuideDao(BringConnection bringConnection, BringParser<ShippingGuideResult> bringParser) {
        this.bringConnection = bringConnection;
        this.bringParser = bringParser;
    }

    public ShippingGuideResult query(Shipment shipment, QueryType queryType) throws RequestFailedException {
        String url = "http://fraktguide.bring.no/fraktguide/products/" + queryType.getName() + ".xml" + shipment.toQueryString();
        InputStream inputStream = null;
        try {
            inputStream = bringConnection.openInputStream(url);
            return bringParser.unmarshal(inputStream);
        }
        catch (UnmarshalException e) {
            throw new RequestFailedException(e);
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (IOException e) {
                    throw new RequestFailedException(e);
                }
            }
        }
    }
}