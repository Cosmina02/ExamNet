package proiect.spring.proiectpa;

import proiect.spring.proiectpa.xmlgenerator.CreateXMLFile;
import proiect.spring.proiectpa.xmlgenerator.Parser;

public class Main {
    public static void main(String[] args) {
        CreateXMLFile.generateXML();
        StringBuilder test=Parser.ReadXML();
        System.out.println(test);
    }
}
