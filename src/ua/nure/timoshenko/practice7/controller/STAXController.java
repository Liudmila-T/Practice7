package ua.nure.timoshenko.practice7.controller;


import org.xml.sax.helpers.DefaultHandler;
import ua.nure.timoshenko.practice7.constants.XML;
import ua.nure.timoshenko.practice7.entity.Certificate;
import ua.nure.timoshenko.practice7.entity.Manufacturer;
import ua.nure.timoshenko.practice7.entity.Medicine;
import ua.nure.timoshenko.practice7.entity.Medicines;
import ua.nure.timoshenko.practice7.entity.Package;
import ua.nure.timoshenko.practice7.entity.Versions;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.events.EndElement;
import javax.xml.transform.stream.StreamSource;


/**
 * Controller for StAX parser.
 */
public class STAXController extends DefaultHandler {

    private String xmlFileName;

    // main container
    private Medicines medicines;


    public STAXController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public Medicines getMedicines() {
        return medicines;
    }

    public void parse()  {


        Medicine medicine = null;
        Versions versions = null;
        Manufacturer manufacturer = null;
        Certificate certificate = null;
        Package aPackage = null;

        // current element name holder
        String currentElement = null;

        XMLInputFactory factory = XMLInputFactory.newInstance();

        factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);
        try {
            XMLEventReader reader = factory.createXMLEventReader(
                    new StreamSource(xmlFileName));

            addData(medicine, versions, manufacturer, certificate, aPackage, currentElement, reader);
            reader.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private void addData
            (Medicine medicine, Versions versions, Manufacturer manufacturer, Certificate certificate,
             Package aPackage, String currentElement, XMLEventReader reader) throws XMLStreamException {
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();

            // skip any empty content
            if (event.isCharacters() && event.asCharacters().isWhiteSpace()) {
                continue;
            }

            // handler for start tags
            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                currentElement = startElement.getName().getLocalPart();

                if (XML.MEDICINES.equalsTo(currentElement)) {
                    medicines = new Medicines();

                }

                if (XML.MEDICINE.equalsTo(currentElement)) {
                    medicine = new Medicine();

                }

                if (XML.VERSIONS.equalsTo(currentElement)) {
                    versions = new Versions();

                }
                if (XML.MANUFACTURER.equalsTo(currentElement)) {
                    manufacturer = new Manufacturer();

                }
                if (XML.CERTIFICATE.equalsTo(currentElement)) {
                    certificate = new Certificate();

                }
                if (XML.PACKAGE.equalsTo(currentElement)) {
                    aPackage = new Package();
                }
            }

            // handler for contents
            if (event.isCharacters()) {
                Characters characters = event.asCharacters();

                if (XML.NAME.equalsTo(currentElement) && medicine != null) {
                    medicine.setName(characters.getData());

                }

                if (XML.PHARM.equalsTo(currentElement) && medicine != null) {
                    medicine.setPharm(characters.getData());

                }
                if (XML.ANALOGS.equalsTo(currentElement) && medicine != null) {
                    medicine.getAnalogs().add(characters.getData());
                }
                if (XML.NAME_VERSIONS.equalsTo(currentElement) && versions != null) {
                    versions.setNameVersions(characters.getData());

                }
                if (XML.NAME_MANUFACTURER.equalsTo(currentElement) && manufacturer != null) {
                    manufacturer.setNameManufacturer(characters.getData());
                }
                if (XML.NUMBER.equalsTo(currentElement) && certificate != null) {
                    certificate.setNumber(Integer.parseInt(characters.getData()));
                }
                if (XML.DATE.equalsTo(currentElement) && certificate != null) {
                    certificate.setDate(characters.getData());
                }
                if (XML.TYPE.equalsTo(currentElement) && aPackage != null) {
                    aPackage.setType(characters.getData());
                }
                if (XML.QUANTITY.equalsTo(currentElement) && aPackage != null) {
                    aPackage.setQuantity(Integer.parseInt(characters.getData()));
                }
                if (XML.PRICE.equalsTo(currentElement) && aPackage != null) {
                    aPackage.setPrice(Double.parseDouble(characters.getData()));

                }
                if (XML.DOSAGE.equalsTo(currentElement) && manufacturer != null) {
                    manufacturer.setDosage(characters.getData());
                }
            }

            // handler for end tags
            if (event.isEndElement()) {
                EndElement endElement = event.asEndElement();
                String localName = endElement.getName().getLocalPart();

                addElement(medicine, versions, manufacturer, certificate, aPackage, localName);
            }
        }
    }

    private void addElement
            (Medicine medicine, Versions versions, Manufacturer manufacturer, Certificate certificate,
             Package aPackage, String localName) {
        if (XML.MEDICINE.equalsTo(localName) && medicines != null) {
            medicines.getMedicine().add(medicine);

        }

        if (XML.VERSIONS.equalsTo(localName) && medicine != null) {
            medicine.getVersions().add(versions);

        }
        if (XML.MANUFACTURER.equalsTo(localName) && versions != null) {
            versions.getManufacturer().add(manufacturer);

        }
        if (XML.CERTIFICATE.equalsTo(localName) && manufacturer != null) {
            manufacturer.getCertificate().add(certificate);
        }
        if (XML.PACKAGE.equalsTo(localName) && manufacturer != null) {
            manufacturer.getPackage().add(aPackage);
        }
    }

}