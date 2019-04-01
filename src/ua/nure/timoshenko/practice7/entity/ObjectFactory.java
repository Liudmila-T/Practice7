package ua.nure.timoshenko.practice7.entity;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {



    /**
     * Create an instance of {@link Medicines }
     * 
     */
    public Medicines createMedicines() {
        return new Medicines();
    }

    /**
     * Create an instance of {@link Medicine }
     * 
     */
    public Medicine createMedicine() {
        return new Medicine();
    }

    /**
     * Create an instance of {@link Versions }
     * 
     */
    public Versions createVersions() {
        return new Versions();
    }

    /**
     * Create an instance of {@link Manufacturer }
     * 
     */
    public Manufacturer createManufacturer() {
        return new Manufacturer();
    }

    /**
     * Create an instance of {@link Package }
     * 
     */
    public Package createPackage() {
        return new Package();
    }

    /**
     * Create an instance of {@link Certificate }
     * 
     */
    public Certificate createCertificate() {
        return new Certificate();
    }

}
