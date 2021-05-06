package com.sytoss.trainee.writer;

import com.sytoss.trainee.dto.Person;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SaxWriter extends AbstractWriter {
    private static final Logger log = Logger.getLogger(SaxWriter.class);

    @Override
    public void write(List<Person> persons, String fileName) {

        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = null;
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            writer = factory.createXMLStreamWriter(fileWriter);

            writer.writeStartDocument();

            writer.writeCharacters("\n");
            writer.writeStartElement("persons");
            writer.writeCharacters("\n  ");
            for (Person p : persons) {
                writer.writeStartElement("person");
                writer.writeAttribute("id", p.getID());
                writer.writeCharacters("\n    ");

                writer.writeStartElement("Name");
                writer.writeCharacters("\n      ");

                writer.writeStartElement("FirstName");
                writer.writeCharacters(p.getFirstName());
                writer.writeEndElement();
                writer.writeCharacters("\n      ");

                writer.writeStartElement("LastName");
                writer.writeCharacters(p.getLastName());
                writer.writeEndElement();
                writer.writeCharacters("\n    ");

                writer.writeEndElement();
                writer.writeCharacters("\n    ");

                writer.writeEmptyElement("Birthday");
                writer.writeAttribute("date", p.getBirthday());
                writer.writeCharacters("\n    ");

                writer.writeStartElement("Comment");
                writer.writeCharacters(p.getComment());
                writer.writeEndElement();
                writer.writeCharacters("\n  ");

                writer.writeEndElement();
                if (persons.get(persons.size() - 1) != p) {
                    writer.writeCharacters("\n  ");
                } else {
                    writer.writeCharacters("\n");
                }
            }
            writer.writeEndElement();
            writer.writeEndDocument();

            writer.flush();
            writer.close();
        } catch (XMLStreamException | IOException e) {
            log.error(e.getMessage());
        }
    }
}
