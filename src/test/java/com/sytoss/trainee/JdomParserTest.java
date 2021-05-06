package com.sytoss.trainee;

import com.sytoss.trainee.dto.Person;
import com.sytoss.trainee.reader.DomReader;
import com.sytoss.trainee.writer.DomWriter;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class JdomParserTest {
    private DomReader domReader;
    private DomWriter domWriter;

    @Test
    public void emptyFileRead() {
        domReader = new DomReader();

        List<Person> persons = new ArrayList<Person>(domReader.read("src/test/resources/emptyJdom.xml"));

        assertEquals(persons.size(), 0);
    }

    @Test
    public void oneLineFileRead() {
        domReader = new DomReader();

        List<Person> persons = new ArrayList<Person>(domReader.read("src/test/resources/lineJdom.xml"));

        assertEquals(persons.size(), 1);

        Person person = new Person("1", "Leha", "Dark", "2001-01-05", "cool");

        assertEquals(persons.get(0), person);
    }

    @Test
    public void linesFileRead() {
        domReader = new DomReader();

        List<Person> persons = new ArrayList<Person>(domReader.read("src/test/resources/test.xml"));

        assertEquals(persons.size(), 5);

        assertEquals(persons.get(0), new Person("1", "Leha", "Dark", "2001-01-05", "cool"));
        assertEquals(persons.get(1), new Person("2", "Jack", "Hayes", "2005-12-19", "cool"));
        assertEquals(persons.get(2), new Person("3", "June", "Gray", "1985-04-12", "theather\"Round\""));
        assertEquals(persons.get(3), new Person("4", "Mary", "Sun", "2001-02-16", "Comm,ent"));
        assertEquals(persons.get(4), new Person("5", "Jolie", "Morn", "1976-12-29", "cu,y \"ghj"));
    }

    @Test
    public void emptyFileWrite() throws IOException {
        domReader = new DomReader();
        domWriter = new DomWriter();

        String filename = new String("src/test/resources/emptyJdom.xml");

        List<Person> persons = new ArrayList<Person>(domReader.read(filename));

        domWriter.write(persons, filename);

        String standard = new String("<?xml version=\"1.0\" encoding=\"UTF-8\"?><persons></persons>");

        StringBuffer temp = new StringBuffer();
        FileReader fr = null;

        fr = new FileReader(filename);

        Scanner scan = new Scanner(fr);

        while (scan.hasNextLine()) {
            temp.append(scan.nextLine());
        }

        fr.close();

        assertEquals(standard, temp.toString());
    }

    @Test
    public void oneLineFileWrite() throws IOException {
        domReader = new DomReader();
        domWriter = new DomWriter();

        String filename = new String("src/test/resources/lineJdom.xml");

        List<Person> persons = new ArrayList<Person>(domReader.read(filename));

        domWriter.write(persons, filename);

        List<String> standard = new ArrayList<String>();
        standard.add("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        standard.add("<persons>");
        standard.add("  <person id=\"1\">");
        standard.add("    <Name>");
        standard.add("      <FirstName>Leha</FirstName>");
        standard.add("      <LastName>Dark</LastName>");
        standard.add("    </Name>");
        standard.add("    <Birthday date=\"2001-01-05\" />");
        standard.add("    <Comment>cool</Comment>");
        standard.add("  </person>");
        standard.add("</persons>");

        StringBuffer temp = new StringBuffer();
        FileReader fr = null;

        fr = new FileReader(filename);

        Scanner scan = new Scanner(fr);

        int i = 0;
        while (scan.hasNextLine()) {
            assertEquals(standard.get(i), scan.nextLine());
            i++;
        }

        fr.close();
    }

    @Test
    public void linesFileWrite() throws IOException {
        domReader = new DomReader();
        domWriter = new DomWriter();

        String filename = new String("src/test/resources/test.xml");

        List<Person> persons = new ArrayList<Person>(domReader.read(filename));

        domWriter.write(persons, filename);

        StringBuffer temp = new StringBuffer();

        FileReader fr = new FileReader(filename);
        FileReader frStandard = new FileReader("src/test/resources/standardJdom.xml");

        Scanner scan = new Scanner(fr);
        Scanner scanStandard = new Scanner(frStandard);

        while (scan.hasNextLine() || scanStandard.hasNextLine()) {
            assertEquals(scanStandard.nextLine(), scan.nextLine());
        }

        fr.close();
        frStandard.close();
    }
}

