package com.sytoss.trainee.writer;

import com.sytoss.trainee.dto.Person;
import org.apache.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvWriter extends AbstractWriter{
    private static final Logger log = Logger.getLogger(CsvWriter.class);

    @Override
    public void write(List<Person> persons, String fileName) {
        StringBuffer current;
        List<String> lines = new ArrayList<String>();

        for (Person person : persons) {
            current = new StringBuffer("");
            current.append(person.getID());
            current.append(",");
            current.append(person.getFirstName());
            current.append(",");
            current.append(person.getLastName());
            current.append(",\"");
            current.append(person.getBirthday());
            current.append("\",");

            if (person.getComment().matches("^[a-zA-Z0-9]+")) {
                current.append(person.getComment());
                current.append("\n");
            } else {
                StringBuffer temp = new StringBuffer(person.getComment());
                for (int i = 0; i < temp.length(); i++) {
                    if (temp.charAt(i) == '\"') {
                        temp.insert(i + 1, '\"');
                        i++;
                    }
                }

                current.append("\"");
                current.append(temp);
                current.append("\"\n");
            }

            lines.add(current.toString());
        }

        writeLinesInFile(lines, fileName);
    }

    private void writeLinesInFile(List<String> lines, String filename) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(filename);

            for (int i = 0; i < lines.size(); i++) {
                fw.write(lines.get(i));
            }

            fw.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
