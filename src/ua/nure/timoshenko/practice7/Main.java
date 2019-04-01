package ua.nure.timoshenko.practice7;

import ua.nure.timoshenko.practice7.controller.DOMController;
import ua.nure.timoshenko.practice7.controller.SAXController;
import ua.nure.timoshenko.practice7.controller.STAXController;
import ua.nure.timoshenko.practice7.entity.Medicines;
import ua.nure.timoshenko.practice7.util.Sorter;

public class Main {

    public static final String OUTPUT = "Output ==> ";
    public static final String INPUT = "Input ==> ";

    public static void usage() {
        System.out.println("Usage:\njava -jar practice7.jar xmlFileName");
        System.out.println("java ua.nure.timoshenko.practice7.Main xmlFileName");
    }

    public static void main(String[] args){
        if (args.length != 1) {
            usage();
            return;
        }

        String xmlFileName = args[0];
        System.out.println(INPUT + xmlFileName);


        // DOM


        // get

        DOMController domController = new DOMController(xmlFileName);
        domController.parse(true);
        Medicines medicines = domController.getMedicines();

        // sort (case 1)
        Sorter.sortMedicineByName(medicines);

        // save
        String outputXmlFile = "output.dom.xml";
        DOMController.saveToXML(medicines, outputXmlFile);
        System.out.println(OUTPUT + outputXmlFile);


        // SAX


        // get
        SAXController saxController = new SAXController(xmlFileName);
        saxController.parse(true);
        medicines = saxController.getMedicines();

        // sort  (case 2)
        Sorter.sortMedicineByVersionSize(medicines);

        // save
       outputXmlFile = "output.sax.xml";

        // other way:
        DOMController.saveToXML(medicines, outputXmlFile);
        System.out.println(OUTPUT + outputXmlFile);


        // StAX

        // get
        STAXController staxController = new STAXController(xmlFileName);
        staxController.parse();
        medicines = staxController.getMedicines();

        // sort  (case 3)
        Sorter.sortMedicineByAnalog(medicines);

        // save
        outputXmlFile = "output.stax.xml";
        DOMController.saveToXML(medicines, outputXmlFile);
        System.out.println(OUTPUT + outputXmlFile);
    }

}
