package com.bring.api.tracking.response;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdditionalServicesSetType", propOrder = {
    "additionalService"
})
public class AdditionalServicesSet {

    @XmlElement(name = "AdditionalService", required = true)
    protected List<AdditionalService> additionalService;

    public List<AdditionalService> getAdditionalService() {
        if (additionalService == null) {
            additionalService = new ArrayList<AdditionalService>();
        }
        return new ArrayList<AdditionalService>(this.additionalService);
    }

}
