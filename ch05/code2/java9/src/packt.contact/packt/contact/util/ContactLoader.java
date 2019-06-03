package packt.contact.util;

import java.io.*;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import packt.contact.internal.*;
import packt.contact.model.*;

public class ContactLoader {

    public List<Contact> loadContacts(String fileName) throws ContactLoadException {

        List<Contact> contacts = new ArrayList<>();
        XmlUtil xmlUtil = new XmlUtil();
        Document doc;
        try {
            doc = xmlUtil.loadXmlFile(fileName);
            
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new ContactLoadException("Unable to load Contact file.");
        }
        NodeList nList = doc.getElementsByTagName("contact");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node contactNode = nList.item(temp);
            if (contactNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) contactNode;

                Contact contact = new Contact();
                contact.setFirstName(xmlUtil.getElement(contactNode, "firstname"));
                contact.setLastName(xmlUtil.getElement(contactNode, "lastname"));
                contact.setPhone(xmlUtil.getElement(contactNode, "phone"));

                contacts.add(contact);
            }
        }
        return contacts;
    }

}