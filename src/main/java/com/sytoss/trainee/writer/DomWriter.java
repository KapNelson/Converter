package com.sytoss.trainee.writer;

import com.sytoss.trainee.dto.Person;
import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class DomWriter extends AbstractWriter{
    private static final Logger log = Logger.getLogger(DomWriter.class);

    @Override
    public void write(List<Person> persons, String fileName) {

        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        Document doc = new Document();

        doc.setRootElement(new Element("persons"));
        for (Person p : persons) {
            Element person = new Element("person");
            person.setAttribute("id", p.getID());

            Element name = new Element("Name");
            name.addContent(new Element("FirstName").setText(p.getFirstName()));
            name.addContent(new Element("LastName").setText(p.getLastName()));
            person.addContent(name);

            Element date = new Element("Birthday");
            date.setAttribute("date", p.getBirthday());
            person.addContent(date);
            person.addContent(new Element("Comment").setText(p.getComment()));
            doc.getRootElement().addContent(person);

            try {
                xmlOutputter.output(doc, new FileOutputStream(fileName));
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }
}
