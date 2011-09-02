package com.bring.api.tracking.response;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PackageSetType", propOrder = {
    "packages"
})
public class PackageSet {

    @XmlElement(name = "Package", required = true)
    protected List<Package> packages;

    public List<Package> getPackages() {
        if (packages == null) {
            packages = new ArrayList<Package>();
        }
        return new ArrayList<Package>(this.packages);
    }

    public Package getPackage(int i) {
        return packages.get(i);
    }

}
