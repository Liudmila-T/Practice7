package ua.nure.timoshenko.practice7.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Versions", propOrder = {
    "nameVersions",
    "manufacturer"
})
public class Versions {

    @XmlElement(required = true)
    protected String nameVersions;
    @XmlElement(name = "Manufacturer", required = true)
    protected List<Manufacturer> manufacturer;

    /**
     * Gets the value of the nameVersions property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameVersions() {
        return nameVersions;
    }

    /**
     * Sets the value of the nameVersions property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameVersions(String value) {
        this.nameVersions = value;
    }

    /**
     * Gets the value of the manufacturer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method
     * for the manufacturer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getManufacturer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Manufacturer }
     * 
     * 
     */
    public List<Manufacturer> getManufacturer() {
        if (manufacturer == null) {
            manufacturer = new ArrayList<>();
        }
        return this.manufacturer;
    }

    @Override
    public String toString() {
        return "Versions{" +
                "nameVersions='" + nameVersions + '\'' +
                ", manufacturer=" + manufacturer +
                '}';
    }
}
