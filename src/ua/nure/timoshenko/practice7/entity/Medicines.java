package ua.nure.timoshenko.practice7.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "medicine"
})
@XmlRootElement(name = "Medicines")
public class Medicines {

    @XmlElement(name = "Medicine", required = true)
    protected List<Medicine> medicine;

    /**
     * Gets the value of the medicine property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for
     * the medicine property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMedicine().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Medicine }
     * 
     * 
     */
    public List<Medicine> getMedicine() {
        if (medicine == null) {
            medicine = new ArrayList<>();
        }
        return this.medicine;
    }

    @Override
    public String toString() {
        if (medicine == null || medicine.isEmpty()) {
            return "Medicines contains no medicine";
        }
        StringBuilder result = new StringBuilder();
        for (Medicine med : medicine) {
            result.append(med).append(System.lineSeparator());
        }
        return result.toString();
    }
}
