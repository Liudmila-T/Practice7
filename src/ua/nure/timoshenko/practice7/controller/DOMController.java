package ua.nure.timoshenko.practice7.controller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import ua.nure.timoshenko.practice7.constants.Constants;
import ua.nure.timoshenko.practice7.constants.XML;
import ua.nure.timoshenko.practice7.entity.Medicines;
import ua.nure.timoshenko.practice7.entity.Certificate;
import ua.nure.timoshenko.practice7.entity.Manufacturer;
import ua.nure.timoshenko.practice7.entity.Medicine;
import ua.nure.timoshenko.practice7.entity.Package;
import ua.nure.timoshenko.practice7.entity.Versions;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

/**
 * Controller for DOM parser.
 */
public class DOMController {

    private String xmlFileName;

    // main container
    private Medicines medicines;

    public DOMController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public Medicines getMedicines() {
        return medicines;
    }

    /**
     * Parses XML document.
     *
     * @param validate If true validate XML document against its XML schema.
     */
    public void parse(boolean validate) {

        // obtain DOM parser
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        // set properties for Factory

        // XML document contains namespaces
        dbf.setNamespaceAware(true);
        try {
            // make parser validating
            if (validate) {
                // turn validation on

                dbf.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);

                // turn on xsd validation

                dbf.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);


            }

            DocumentBuilder db = null;

            db = dbf.newDocumentBuilder();


            // set error handler
            db.setErrorHandler(new DefaultHandler() {
                @Override
                public void error(SAXParseException e) throws SAXException {
                    // throw exception if XML document is NOT valid
                    throw e;
                }
            });

            // parse XML document
            Document document = db.parse(xmlFileName);

            // get root element
            Element root = document.getDocumentElement();

            // create container
            medicines = new Medicines();

            // obtain medicine nodes
            NodeList medicineNodes = root
                    .getElementsByTagName(XML.MEDICINE.value());
            // process medicine nodes
            for (int j = 0; j < medicineNodes.getLength(); j++) {
                Medicine medicine = getMedicine(medicineNodes.item(j));
                // add medicine to container

                medicines.getMedicine().add(medicine);
            }
        } catch (ParserConfigurationException |IOException |SAXException e) {
            e.printStackTrace();
        }

    }

        /**
         * Extracts medicine object from the medicine XML node.
         *
         * @param mNode Medicine node.
         * @return Medicine object.
         */
        public Medicine getMedicine (Node mNode){
            Medicine medicine = new Medicine();
            Element mElement = (Element) mNode;

            // process name
            Node nameNode = mElement.getElementsByTagName(XML.NAME.value())
                    .item(0);
            // set name
            medicine.setName(nameNode.getTextContent());

            Node pharmNode = mElement.getElementsByTagName(XML.PHARM.value())
                    .item(0);
            // set pharm
            medicine.setPharm(pharmNode.getTextContent());

            Node groupNode = mElement.getElementsByTagName(XML.GROUP.value())
                    .item(0);
            // set group
            medicine.setGroup(groupNode.getTextContent());

            // process analog
            NodeList analogNodeList = mElement.getElementsByTagName(XML.ANALOGS.value());
            for (int j = 0; j < analogNodeList.getLength(); j++) {
                medicine.getAnalogs().add(analogNodeList.item(j).getTextContent());

            }

            // process version
            NodeList versionNodeList = mElement.getElementsByTagName(XML.VERSIONS.value());
            for (int j = 0; j < versionNodeList.getLength(); j++) {
                Versions versions = getVersions(versionNodeList.item(j));
                medicine.getVersions().add(versions);
            }

            return medicine;
        }

        /**
         * Extracts Versions object from the version XML node.
         * * @return Versions object.
         */
        private static Versions getVersions (Node versionsNode){
            Versions versions = new Versions();
            Element vElement = (Element) versionsNode;

            Node nameVersionsNode = vElement.getElementsByTagName(XML.NAME_VERSIONS.value()).item(0);
            versions.setNameVersions(nameVersionsNode.getTextContent());

            NodeList manufactureNodeList = vElement.getElementsByTagName(XML.MANUFACTURER.value());
            for (int j = 0; j < manufactureNodeList.getLength(); j++) {
                Manufacturer manufacturer = addManufacturerElement(manufactureNodeList.item(j));
                versions.getManufacturer().add(manufacturer);
            }
            return versions;
        }

        private static Manufacturer addManufacturerElement(Node manufacturerNode){
            Manufacturer manufacturer = new Manufacturer();
            Element manElement = (Element) manufacturerNode;

            Node nameManufacturerNode = manElement.getElementsByTagName(XML.NAME_MANUFACTURER.value()).item(0);
            manufacturer.setNameManufacturer(nameManufacturerNode.getTextContent());

            NodeList certificateNodeList = manElement.getElementsByTagName(XML.CERTIFICATE.value());
            for (int j = 0; j < certificateNodeList.getLength(); j++) {
                Certificate certificate = getCertificate(certificateNodeList.item(j));
                manufacturer.getCertificate().add(certificate);
            }
            NodeList packageNodeList = manElement.getElementsByTagName(XML.PACKAGE.value());
            for (int j = 0; j < packageNodeList.getLength(); j++) {
                Package aPackage = getPackage(packageNodeList.item(j));
                manufacturer.getPackage().add(aPackage);
            }
            Node dosageNode = manElement.getElementsByTagName(XML.DOSAGE.value()).item(0);
            manufacturer.setDosage(dosageNode.getTextContent());

            return manufacturer;
        }

        private static Certificate getCertificate (Node certificateNode){
            Certificate certificate = new Certificate();
            Element certificateElement = (Element) certificateNode;

            Node numberNode = certificateElement.getElementsByTagName(XML.NUMBER.value()).item(0);
            certificate.setNumber(Integer.parseInt(numberNode.getTextContent()));

            Node dateNode = certificateElement.getElementsByTagName(XML.DATE.value()).item(0);
            certificate.setDate(dateNode.getTextContent());

            return certificate;
        }

        private static Package getPackage (Node packNode){
            Package aPackage = new Package();
            Element packElement = (Element) packNode;

            Node typeNode = packElement.getElementsByTagName(XML.TYPE.value()).item(0);
            aPackage.setType(typeNode.getTextContent());

            Node quantityNode = packElement.getElementsByTagName(XML.QUANTITY.value()).item(0);
            aPackage.setQuantity(Integer.parseInt(quantityNode.getTextContent()));

            Node priceNode = packElement.getElementsByTagName(XML.PRICE.value()).item(0);
            aPackage.setPrice(Double.parseDouble(priceNode.getTextContent()));

            return aPackage;
        }

        /**
         * Creates and returns DOM of the Medicines container.
         *
         * @param medicines Medicines object.
         * @throws ParserConfigurationException
         */
        public static Document getDocument (Medicines medicines) throws ParserConfigurationException {

            // obtain DOM parser
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            // set properties for Factory

            // XML document contains namespaces
            dbf.setNamespaceAware(true);

            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.newDocument();

            // create root element
            Element medElement = document.createElement(XML.MEDICINES.value());
            // add root element
            document.appendChild(medElement);

            // add medicine elements
            addMedicineElements(medicines, document, medElement);

            return document;
        }

    private static void addMedicineElements(Medicines medicines, Document document, Element medElement) {
        for (Medicine medicine : medicines.getMedicine()) {

            // add medicine
            Element mElement = document.createElement(XML.MEDICINE.value());
            medElement.appendChild(mElement);


            Element nameElement = document.createElement(XML.NAME.value());
            nameElement.setTextContent(medicine.getName());
            mElement.appendChild(nameElement);

            Element pharmElement = document.createElement(XML.PHARM.value());
            pharmElement.setTextContent(medicine.getPharm());
            mElement.appendChild(pharmElement);

            Element groupElement = document.createElement(XML.GROUP.value());
            groupElement.setTextContent(medicine.getGroup());
            mElement.appendChild(groupElement);

            for (String analog : medicine.getAnalogs()) {
                Element aElement = document.createElement(XML.ANALOGS.value());
                aElement.setTextContent(analog);
                mElement.appendChild(aElement);
            }
            addManufacturerElement(document, medicine, mElement);
        }
    }

    private static void addManufacturerElement(Document document, Medicine medicine, Element mElement) {
        for (Versions versions : medicine.getVersions()) {
            Element verElement = document.createElement(XML.VERSIONS.value());
            mElement.appendChild(verElement);


            Element nameVersionsElement = document.createElement(XML.NAME_VERSIONS.value());
            nameVersionsElement.setTextContent(versions.getNameVersions());
            verElement.appendChild(nameVersionsElement);

            for (Manufacturer manufacturer : versions.getManufacturer()) {

                Element manufacturerElement = document.createElement(XML.MANUFACTURER.value());
                verElement.appendChild(manufacturerElement);

                Element nameManufacturerElement = document.createElement(XML.NAME_MANUFACTURER.value());
                nameManufacturerElement.setTextContent(manufacturer.getNameManufacturer());
                manufacturerElement.appendChild(nameManufacturerElement);

                addCertificateElement(document, manufacturer, manufacturerElement);

                addPackageElement(document, manufacturer, manufacturerElement);

                Element dosageElement = document.createElement(XML.DOSAGE.value());
                dosageElement.setTextContent(manufacturer.getDosage());
                manufacturerElement.appendChild(dosageElement);
            }
        }
    }

    private static void addPackageElement(Document document, Manufacturer manufacturer, Element manufacturerElement) {
        for (Package pack : manufacturer.getPackage()) {

            Element packElement = document.createElement(XML.PACKAGE.value());
            manufacturerElement.appendChild(packElement);

            Element typeElement = document.createElement(XML.TYPE.value());
            typeElement.setTextContent(pack.getType());
            packElement.appendChild(typeElement);

            Element quantityElement = document.createElement(XML.QUANTITY.value());
            quantityElement.setTextContent(String.valueOf(pack.getQuantity()));
            packElement.appendChild(quantityElement);

            Element priceElement = document.createElement(XML.PRICE.value());
            priceElement.setTextContent(String.valueOf(pack.getPrice()));
            packElement.appendChild(priceElement);


        }
    }

    private static void addCertificateElement
            (Document document, Manufacturer manufacturer, Element manufacturerElement) {
        for (Certificate certificate : manufacturer.getCertificate()) {

            Element certificateElement = document.createElement(XML.CERTIFICATE.value());
            manufacturerElement.appendChild(certificateElement);

            Element numberElement = document.createElement(XML.NUMBER.value());
            numberElement.setTextContent(String.valueOf(certificate.getNumber()));
            certificateElement.appendChild(numberElement);

            Element dateElement = document.createElement(XML.DATE.value());
            dateElement.setTextContent(String.valueOf(certificate.getDate()));
            certificateElement.appendChild(dateElement);
        }
    }

    /**
         * Saves Medicines object to XML file.
         *
         * @param medicines   Medicines object to be saved.
         * @param xmlFileName Output XML file name.
         */
        public static void saveToXML (Medicines medicines, String xmlFileName) {
            // Medicines -> DOM -> XML
            try {
                saveToXML(getDocument(medicines), xmlFileName);
            } catch (TransformerException |ParserConfigurationException e) {
                e.printStackTrace();
            }
        }

        /**
         * Save DOM to XML.
         *
         * @param document    DOM to be saved.
         * @param xmlFileName Output XML file name.
         */
        public static void saveToXML (Document document, String xmlFileName)
            throws TransformerException {

            StreamResult result = new StreamResult(new File(xmlFileName));

            // set up transformation
            TransformerFactory tf = TransformerFactory.newInstance();
            javax.xml.transform.Transformer t = tf.newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");

            // run transformation
            t.transform(new DOMSource(document), result);
        }

    }
