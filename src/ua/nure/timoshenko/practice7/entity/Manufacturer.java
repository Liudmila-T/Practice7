package ua.nure.timoshenko.practice7.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Manufacturer", propOrder = {
    "nameManufacturer",
    "certificate",
        "packages",
    "dosage"
})
public class Manufacturer {

    @XmlElement(required = true)
    protected String nameManufacturer;
    @XmlElement(name = "Certificate", required = true)
    protected List<Certificate> certificate;
    @XmlElement(name = "Package", required = true)
    protected List<Package> packages;
    @XmlElement(required = true)
    protected String dosage;

    /**
     * Gets the value of the nameManufacturer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameManufacturer() {
        return nameManufacturer;
    }

    /**
     * Sets the value of the nameManufacturer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameManufacturer(String value) {
        this.nameManufacturer = value;
    }

    /**
     * Gets the value of the certificate property.
     * 
     * @return
     *     possible object is
     *     {@link Certificate }
     *     
     */
    public List<Certificate> getCertificate() {
        if (certificate==null){
            certificate=new ArrayList<>();
        }
        return this.certificate;
    }


    public List<Package> getPackage() {
        if (packages == null) {
            packages = new ArrayList<>();
        }
        return this.packages;
    }

    /**
     * Gets the value of the dosage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDosage() {
        return dosage;
    }

    /**
     * Sets the value of the dosage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDosage(String value) {
        this.dosage = value;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "nameManufacturer='" + nameManufacturer + '\'' +
                ", certificate=" + certificate +
                ", package=" + packages +
                ", dosage='" + dosage + '\'' +
                '}';
    }
}
