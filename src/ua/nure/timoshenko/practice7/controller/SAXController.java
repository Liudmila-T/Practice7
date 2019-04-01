package ua.nure.timoshenko.practice7.controller;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ua.nure.timoshenko.practice7.constants.Constants;
import ua.nure.timoshenko.practice7.constants.XML;
import ua.nure.timoshenko.practice7.entity.Certificate;
import ua.nure.timoshenko.practice7.entity.Manufacturer;
import ua.nure.timoshenko.practice7.entity.Medicine;
import ua.nure.timoshenko.practice7.entity.Medicines;
import ua.nure.timoshenko.practice7.entity.Package;
import ua.nure.timoshenko.practice7.entity.Versions;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;


/**
 * Controller for SAX parser.
 */
public class SAXController extends DefaultHandler {

    private String xmlFileName;

    // current element name holder
    private String currentElement;

    // main container
    private Medicines medicines;
    private Medicine medicine;
    private Versions versions;
    private Manufacturer manufacturer;
    private Certificate certificate;
    private Package aPackage;


    public SAXController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    /**
     * Parses XML document.
     *
     * @param validate If true validate XML document against its XML schema. With
     *                 this parameter it is possible make parser validating.
     */
    public void parse(boolean validate) {

        // obtain sax parser factory
        SAXParserFactory factory = SAXParserFactory.newInstance();

        // XML document contains namespaces
        factory.setNamespaceAware(true);
        try {
            // set validation
            if (validate) {

                factory.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
                factory.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
            }

            SAXParser parser = factory.newSAXParser();
            parser.parse(xmlFileName, this);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
        @Override
        public void error (org.xml.sax.SAXParseException e) throws SAXException {
            // if XML document not valid just throw exception
            throw e;
        }

        public Medicines getMedicines () {
            return medicines;
        }

        @Override
        public void startElement (String uri, String localName, String qName,
                Attributes attributes) {

            currentElement = localName;

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

        @Override
        public void characters ( char[] ch, int start, int length) {

            String elementText = new String(ch, start, length).trim();

            // return if content is empty
            if (elementText.isEmpty()) {
                return;
            }

            if (XML.NAME.equalsTo(currentElement)) {
                medicine.setName(elementText);
            }

            if (XML.PHARM.equalsTo(currentElement)) {
                medicine.setPharm(elementText);
            }
            if (XML.GROUP.equalsTo(currentElement)) {
                medicine.setGroup(elementText);
            }
            if (XML.ANALOGS.equalsTo(currentElement)) {
                medicine.getAnalogs().add(elementText);
            }
            if (XML.NAME_VERSIONS.equalsTo(currentElement)) {
                versions.setNameVersions(elementText);
            }
            if (XML.NAME_MANUFACTURER.equalsTo(currentElement)) {
                manufacturer.setNameManufacturer(elementText);
            }
            if (XML.NUMBER.equalsTo(currentElement)) {
                certificate.setNumber(Integer.parseInt(elementText));
            }
            if (XML.DATE.equalsTo(currentElement)) {
                certificate.setDate(elementText);
            }
            if (XML.TYPE.equalsTo(currentElement)) {
                aPackage.setType(elementText);
                return;
            }
            if (XML.QUANTITY.equalsTo(currentElement)) {
                aPackage.setQuantity(Integer.parseInt(elementText));
            }
            if (XML.PRICE.equalsTo(currentElement)) {
                aPackage.setPrice(Double.parseDouble(elementText));
            }
            if (XML.DOSAGE.equalsTo(currentElement)) {
                manufacturer.setDosage(elementText);
            }
        }

        @Override
        public void endElement (String uri, String localName, String qName) {

            if (XML.MEDICINE.equalsTo(localName)) {
                // just add question to container
                medicines.getMedicine().add(medicine);
            }

            if (XML.VERSIONS.equalsTo(localName)) {
                medicine.getVersions().add(versions);
            }

            if (XML.MANUFACTURER.equalsTo(localName)) {
                versions.getManufacturer().add(manufacturer);
            }
            if (XML.CERTIFICATE.equalsTo(localName)) {
                manufacturer.getCertificate().add(certificate);
            }
            if (XML.PACKAGE.equalsTo(localName)) {
                manufacturer.getPackage().add(aPackage);
            }
        }

    }