package com.sytoss.trainee;

import com.sytoss.trainee.dto.Person;
import com.sytoss.trainee.reader.CsvReader;
import com.sytoss.trainee.writer.CsvWriter;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CsvParserTest {
    private CsvReader csvReader;
    private CsvWriter csvWriter;

    @Test
    public void standardLineSize() {
        csvReader = new CsvReader();

        List<String> line = new ArrayList<String>(csvReader.parserOneLine("1,Leha,Dark,\"1800-05-05\",Vampir"));

        assertEquals(line.size(), 5);
    }

    @Test
    public void standardLine() {
        csvReader = new CsvReader();

        List<String> line = new ArrayList<String>(csvReader.parserOneLine("1,Leha,Dark,1990,Vampir"));

        String[] test = new String[]{"1", "Leha", "Dark", "1990", "Vampir"};

        for (int i = 0; i < line.size(); i++) {
            assertEquals(line.get(i), test[i]);
        }
    }

    @Test
    public void notStandardLine() {
        csvReader = new CsvReader();

        List<String> line = new ArrayList<String>(csvReader.parserOneLine("1,Leha,Dark,\"1800-05-05\",Vampir"));

        String[] test = new String[]{"1", "Leha", "Dark", "1800-05-05", "Vampir"};

        for (int i = 0; i < line.size(); i++) {
            assertEquals(line.get(i), test[i]);
        }
    }

    @Test
    public void quotedString() {
        csvReader = new CsvReader();

        List<String> line = new ArrayList<String>(csvReader.parserOneLine("1,Leha,Dark,\"1800-05-05\",\"\"\"Ne\"\"Vampir\""));

        String[] test = new String[]{"1", "Leha", "Dark", "1800-05-05", "\"Ne\"Vampir"};

        for (int i = 0; i < line.size(); i++) {
            assertEquals(line.get(i), test[i]);
        }
    }

    @Test
    public void oneQuotedString() {
        csvReader = new CsvReader();

        List<String> line = new ArrayList<String>(csvReader.parserOneLine("1,Leha,Dark,\"1800-05-05\",\"Ne\"\"Vampir\""));

        String[] test = new String[]{"1", "Leha", "Dark", "1800-05-05", "Ne\"Vampir"};

        for (int i = 0; i < line.size(); i++) {
            assertEquals(line.get(i), test[i]);
        }
    }

    @Test
    public void alienString() {
        csvReader = new CsvReader();

        List<String> line = new ArrayList<String>(csvReader.parserOneLine("1,Leha,Dark,\"1800-05-05\",\"Ne,Vampir\""));

        String[] test = new String[]{"1", "Leha", "Dark", "1800-05-05", "Ne,Vampir"};

        for (int i = 0; i < line.size(); i++) {
            assertEquals(line.get(i), test[i]);
        }
    }

    @Test(expected = FileNotFoundException.class)
    public void notFoundFileRead() throws FileNotFoundException {
        csvReader = new CsvReader();

        List<Person> persons = new ArrayList<Person>(csvReader.read("src/test/resources/not.csv"));

        FileReader fr = new FileReader("src/test/resources/not.csv");
    }

    @Test
    public void emptyFileRead() {
        csvReader = new CsvReader();

        List<Person> persons = new ArrayList<Person>(csvReader.read("src/test/resources/empty.csv"));

        assertEquals(persons.size(), 0);
    }

    @Test
    public void oneLineFileRead() {
        csvReader = new CsvReader();

        List<Person> persons = new ArrayList<Person>(csvReader.read("src/test/resources/line.csv"));

        assertEquals(persons.size(), 1);
    }

    @Test
    public void linesFileRead() {
        csvReader = new CsvReader();

        List<Person> persons = new ArrayList<Person>(csvReader.read("src/test/resources/test.csv"));

        assertEquals(persons.size(), 5);
    }


    @Test
    public void emptyFileWrite() {
        csvWriter = new CsvWriter();
        csvReader = new CsvReader();

        List<Person> persons = new ArrayList<Person>(csvReader.read("src/test/resources/empty.csv"));

        csvWriter.write(persons, "src/test/resources/empty.csv");

        File file = new File("src/test/resources/empty.csv");
        assertEquals(file.length(), 0);
    }

    @Test
    public void oneLineFileWrite() {
        csvWriter = new CsvWriter();
        csvReader = new CsvReader();

        List<Person> persons = new ArrayList<Person>(csvReader.read("src/test/resources/line.csv"));

        csvWriter.write(persons, "src/test/resources/line.csv");

        File file = new File("src/test/resources/line.csv");
        assertEquals(file.length(), 33);

        List<Person> personsNew = new ArrayList<Person>(csvReader.read("src/test/resources/line.csv"));

        assertEquals(persons.size(), personsNew.size());
    }

    @Test
    public void linesFileWrite() {
        csvWriter = new CsvWriter();
        csvReader = new CsvReader();

        List<Person> persons = new ArrayList<Person>(csvReader.read("src/test/resources/test.csv"));

        csvWriter.write(persons, "src/test/resources/test.csv");

        File file = new File("src/test/resources/test.csv");
        assertEquals(file.length(), 180);

        List<Person> personsNew = new ArrayList<Person>(csvReader.read("src/test/resources/test.csv"));

        assertEquals(persons.size(), personsNew.size());
    }
}
