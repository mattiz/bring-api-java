package com.bring.shippingguide.dao;

import java.io.IOException;
import java.io.InputStream;

import com.bring.BringParser;
import com.bring.connection.BringConnection;
import com.bring.exceptions.RequestFailedException;
import com.bring.exceptions.UnmarshalException;
import com.bring.shippingguide.request.QueryType;
import com.bring.shippingguide.request.Shipment;
import com.bring.shippingguide.response.ShippingGuideResult;

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