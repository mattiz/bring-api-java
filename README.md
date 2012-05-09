bring-api-java
==============

Introduction
------------

This repository contains an open source client library for:

- [Bring Tracking](http://developer.bring.com/learn/tracking/apireference.html)
- [Bring Shipment Guide](http://developer.bring.com/learn/shipping-guide/documentation/apireference.html)

[Javadoc is also available.](http://bring.github.com/bring-api-java/1.0.0/)

License
-------

[2-clause license ("Simplified BSD License" or "FreeBSD License")](http://www.opensource.org/licenses/bsd-license.php)

Copyright 2011 Bring. All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are
permitted provided that the following conditions are met:

   1. Redistributions of source code must retain the above copyright notice, this list of
      conditions and the following disclaimer.

   2. Redistributions in binary form must reproduce the above copyright notice, this list
      of conditions and the following disclaimer in the documentation and/or other materials
      provided with the distribution.

THIS SOFTWARE IS PROVIDED BY Bring ``AS IS'' AND ANY EXPRESS OR IMPLIED
WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL Bring OR
CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

The views and conclusions contained in the software and documentation are those of the
authors and should not be interpreted as representing official policies, either expressed
or implied, of Bring.

Requirements
------------

- Java 1.6, or newer
- [Joda-Time](http://joda-time.sourceforge.net)

Installation
------------

Download jar-file (include as library in your project), or add [Maven](http://maven.apache.org/) dependency 

    <dependencies>
        <dependency>
            <groupId>com.bring</groupId>
            <artifactId>bring-api</artifactId>
            <version>1.0.0</version>
        </dependency>
    </dependencies>

To use with [HttpComponents/HttpClient 4](http://hc.apache.org/httpcomponents-client-ga/), you should also include this jar:

    <dependencies>
        <dependency>
            <groupId>com.bring</groupId>
            <artifactId>bring-api-httpclient4</artifactId>
            <version>1.0.0</version>
        </dependency>
    </dependencies>


To use with [HttpClient 3](http://hc.apache.org/httpclient-3.x/), you should also include this jar:

    <dependencies>
        <dependency>
            <groupId>com.bring</groupId>
            <artifactId>bring-api-httpclient3</artifactId>
            <version>1.0.0</version>
        </dependency>
    </dependencies>



Usage
-----

1. Initialize library (new BringService("url to your web site"))
2. Prepare query
    - Use ShipmentDescription and PackageDescription to describe shipment guide queries
    - Use TrackingQuery to describe tracking queries
3. Fetch information using the BringService class

Examples
--------

Fetch price information from Bring shipment guide:

        // Initialize library
        String clientId = "www.mywebshop.com";
        BringService bringService = new BringService(clientId);

        // Prepare query
        Package packet = new Package();
        packet.setWeightInGrams("4233");
        Shipment shipment = new Shipment();
        shipment.withFromPostalCode("1409");
        shipment.withToPostalCode("7050");
        shipment.addPackage(packet);
        shipment.addProduct(Product.SERVICEPAKKE);

        // Fetch price information from Bring
        ShippingGuideResult shippingGuideResult = bringService.queryShippingGuide(shipment, QueryType.PRICE);
        String amountWithVAT = shippingGuideResult
            .getProduct(Product.SERVICEPAKKE)
            .getPrice()
            .getPackagePriceWithoutAdditionalServices()
            .getAmountWithVAT();

        // Display result
        System.out.println("Price: "+amountWithVAT);


Fetch tracking information from Bring Tracking:

        // Initialize library
        String clientId = "www.mywebshop.com";
        BringService bringService = new BringService(clientId);

    	//Prepare query
    	TrackingQuery query = new TrackingQuery();
    	query.setQueryNumber("1234567");

    	//Fetch Tracking information from Bring
    	TrackingResult trackingResult = bringService.queryTracking(query);
    	String totalWeight = trackingResult.getConsignment(0)
    	    .getTotalWeight()
    	    .getValue();

    	//Display result
    	System.out.println("Total weight:" + totalWeight);


HttpClient
----------

The library will autodect if you put bring-api-httpclient4 or bring-api-httpclient3 on you classpath,
and use the corresponding library instead of URLConnection.

To supply your own instance of the HttpClient, you may use the alternate constructor of BringService:

        String clientId = "www.mywebshop.com";
        BringService bringService = new BringService(new HttpClient4Adapter(clientId, httpClient));

or

        String clientId = "www.mywebshop.com";
        BringService bringService = new BringService(new HttpClient3Adapter(clientId, httpClient));


Contribute
----------

For those of you that would like to contribute, we have some suggestions:

- Module for [offline data](http://developer.bring.com/learn/shipping-guide/documentation/offlinedata.html) (for use if the shipment guide is unavailable)
- [Postal code validation](http://developer.bring.com/learn/postalcode/apireference.html)
- [Postal office chooser](http://developer.bring.com/learn/pickuppoint/apireference.html), based on postal code


Release new version (change version numbers)
--------------------------------------------

    $ mvn release:prepare -DdevelopmentVersion=1.0.2-SNAPSHOT -DautoVersionSubmodules=true -DreleaseVersion=1.0.1 -Dtag=bring-api-java-1.0.1
    $ mvn release:perform -Darguments="-Dmaven.test.skip=true"



