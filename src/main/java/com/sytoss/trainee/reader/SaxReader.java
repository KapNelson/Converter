package com.sytoss.trainee.reader;

import com.sytoss.trainee.dto.Person;
import org.apache.log4j.Logger;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SaxReader extends AbstractReader {
    private static final Logger log = Logger.getLogger(SaxReader.class);

    @Override
    public List<Person> read(String fileName) {
        persons = new ArrayList<>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            XMLHandler handler = new XMLHandler();
            parser.parse(new File(fileName), handler);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return Collections.unmodifiableList(persons);
    }

    private class XMLHandler extends DefaultHandler {
        private String id;
        private String firstName;
        private String lastName;
        private String birthday;
        private String comment;

        private String lastElementName;


        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("person")) {
                id = attributes.getValue("id");
            }
            if (qName.equals("Birthday")) {
                birthday = attributes.getValue("date");
            }
            lastElementName = qName;
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (id != null && firstName != null && lastName != null && birthday != null && comment != null) {
                persons.add(new Person(id, firstName, lastName, birthday, comment));
                id = null;
                firstName = null;
                lastName = null;
                birthday = null;
                comment = null;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String information = new String(ch, start, length);

            information = information.replace("\n", "").trim();

            if (!information.isEmpty()) {
                if (lastElementName.equals("FirstName"))
                    firstName = information;
                if (lastElementName.equals("LastName"))
                    lastName = information;
                if (lastElementName.equals("Comment"))
                    comment = information;
            }
        }
    }
}
