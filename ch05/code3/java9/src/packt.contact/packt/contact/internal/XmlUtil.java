package packt.contact.internal;

import java.io.*;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class XmlUtil {

    public Document loadXmlFile(String fileName) throws
        ParserConfigurationException, SAXException, IOException {
        File inputFile = new File(fileName);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
        Document doc = dbBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        return doc;
    }

    public String getElement(Node nNode, String tagName) {
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) nNode;
            return eElement.getElementsByTagName(tagName).item(0).getTextContent();
        }
        return "";
    }
    
}