package ua.nure.timoshenko.practice7.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Medicine", propOrder = {
        "name",
        "pharm",
        "group",
        "analogs",
        "versions"
})
public class Medicine {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(name = "Pharm", required = true)
    protected String pharm;
    @XmlElement(name = "Group", required = true)
    protected String group;
    @XmlElement(required = true)
    protected List<String> analogs;
    @XmlElement(name = "Versions", required = true)
    protected List<Versions> versions;

    /**
     * Gets the value of the name property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the pharm property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPharm() {
        return pharm;
    }

    /**
     * Sets the value of the pharm property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPharm(String value) {
        this.pharm = value;
    }

    /**
     * Gets the value of the group property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getGroup() {
        return group;
    }

    /**
     * Sets the value of the group property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setGroup(String value) {
        this.group = value;
    }

    /**
     * Gets the value of the analogs property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method
     * for the analogs property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAnalogs().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     */
    public List<String> getAnalogs() {
        if (analogs == null) {
            analogs = new ArrayList<>();
        }
        return this.analogs;
    }

    /**
     * Gets the value of the versions property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method
     * for the versions property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVersions().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Versions }
     */
    public List<Versions> getVersions() {
        if (versions == null) {
            versions = new ArrayList<>();
        }
        return this.versions;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "name='" + name + '\'' +
                ", pharm='" + pharm + '\'' +
                ", group='" + group + '\'' +
                ", analogs=" + analogs.toString() +
                ", versions=" + versions.toString() +
                '}';
    }
}
