package com.sytoss.trainee.reader;

import com.sytoss.trainee.dto.Person;
import org.apache.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CsvReader extends AbstractReader{
    private static final Logger log = Logger.getLogger(CsvReader.class);

    @Override
    public List<Person> read(String fileName) {
        List<String> lines = new ArrayList<String>(readLinesFromFile(fileName));
        List<String> cells;
        persons = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            cells = new ArrayList<>(parserOneLine(lines.get(i)));

            Person person = new Person(cells.get(0), cells.get(1), cells.get(2), cells.get(3), cells.get(4));
            persons.add(person);
        }
        return Collections.unmodifiableList(persons);
    }

    public List<String> parserOneLine(String line) {
        List<String> result = new ArrayList<String>();

        boolean flag = true;
        StringBuffer current = new StringBuffer("");
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '"') {
                flag = !flag;
            }
            if (line.charAt(i) == ',' && flag) {
                result.add(current.toString());
                current = new StringBuffer("");
            } else {
                current.append(line.charAt(i));
            }
        }
        result.add(current.toString());

        for (int j = 0; j < result.size(); j++) {
            if (result.get(j).charAt(0) == '\"' && result.get(j).charAt(result.get(j).length() - 1) == '\"') {

                StringBuffer temp = new StringBuffer();

                for (int i = 1; i < result.get(j).length() - 1; i++) {
                    temp.append(result.get(j).charAt(i));
                }

                result.remove(j);
                result.add(j, temp.toString());
                temp = new StringBuffer("");

                for (int i = 0; i < result.get(j).length(); i++) {
                    if (result.get(j).charAt(i) == '\"' && result.get(j).charAt(i + 1) == '\"') {
                        temp.append(result.get(j).charAt(i));
                        i++;
                    } else {
                        temp.append(result.get(j).charAt(i));
                    }
                }
                result.remove(j);
                result.add(j, temp.toString());
            }
        }
        return result;
    }

    private List<String> readLinesFromFile(String fileName) {
        List<String> result = new ArrayList<String>();
        FileReader fr = null;
        try {
            fr = new FileReader(fileName);

            Scanner scan = new Scanner(fr);

            while (scan.hasNextLine()) {
                result.add(scan.nextLine());
            }
            scan.close();
            fr.close();
        } catch (IOException e) {
            log.error(fileName + ": file not found!");
        }

        return result;
    }
}
