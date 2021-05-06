package com.sytoss.trainee.reader;

import com.sytoss.trainee.dto.Person;
import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DomReader extends AbstractReader{
    private static final Logger log = Logger.getLogger(DomReader.class);

    @Override
    public List<Person> read(String fileName) {
        persons = new ArrayList<>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder;
            documentBuilder = dbFactory.newDocumentBuilder();
            org.w3c.dom.Document doc = documentBuilder.parse(new File(fileName));
            DOMBuilder domBuilder = new DOMBuilder();

            Document jdomDocument = domBuilder.build(doc);
            Element root = jdomDocument.getRootElement();
            List<Element> personListElements = root.getChildren("person");
            for (Element personEl : personListElements) {
                Person person = new Person();
                person.setID(personEl.getAttributeValue("id"));
                List<Element> name = personEl.getChildren("Name");
                person.setFirstName(name.get(0).getChildText("FirstName"));
                person.setLastName(name.get(0).getChildText("LastName"));
                List<Element> birthday = personEl.getChildren("Birthday");
                person.setBirthday(birthday.get(0).getAttributeValue("date"));
                person.setComment(personEl.getChildText("Comment"));

                persons.add(person);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return Collections.unmodifiableList(persons);
    }
}
