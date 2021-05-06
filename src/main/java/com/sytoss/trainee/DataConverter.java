package com.sytoss.trainee;

import com.sytoss.trainee.dto.Person;
import com.sytoss.trainee.reader.CsvReader;
import com.sytoss.trainee.reader.DomReader;
import com.sytoss.trainee.reader.Reader;
import com.sytoss.trainee.reader.SaxReader;
import com.sytoss.trainee.utils.FileFormatUtils;
import com.sytoss.trainee.writer.CsvWriter;
import com.sytoss.trainee.writer.DomWriter;
import com.sytoss.trainee.writer.SaxWriter;
import com.sytoss.trainee.writer.Writer;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.List;

public class DataConverter {
    private static final Logger log = Logger.getLogger(DataConverter.class);
    private final String inputFilepath;
    private final String outputFilepath;

    public DataConverter(String filePathFrom, String filePathTo) {
        inputFilepath = filePathFrom;
        outputFilepath = filePathTo;
    }

    public void convert() {
        List<Person> persons = null;
        int maxJDOMFileLength = 20480;

        Reader reader = null;
        if (FileFormatUtils.isCsv(inputFilepath)) {
            reader = new CsvReader();
        } else if (FileFormatUtils.isXml(inputFilepath)) {
            if (new File(inputFilepath).length() < maxJDOMFileLength) {
                reader = new DomReader();
            } else {
                reader = new SaxReader();
            }
        }

        if (reader != null) {
            persons = reader.read(inputFilepath);
        } else {
            log.error("Reader is null!");
            throw new NullPointerException();
        }

        Writer writer = null;
        if (FileFormatUtils.isCsv(outputFilepath)) {
            writer = new CsvWriter();
        } else if (FileFormatUtils.isXml(outputFilepath)) {
            if (persons.size() > 20) {
                writer = new SaxWriter();
            } else {
                writer = new DomWriter();
            }
        }

        if (writer != null) {
            writer.write(persons, outputFilepath);
        } else {
            log.error("Writer is null!");
            throw new NullPointerException();
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            log.error("You should have 2 args!");
            System.out.println("You should have 2 args!");
            System.exit(-1);
        }
        DataConverter dataConverter = new DataConverter(args[0], args[1]);
        try {
            dataConverter.convert();
        } catch (NullPointerException e) {
            System.out.println("No such type.\nYou should use [.txt] or [.xml]");
            log.error(e.getMessage());
        }
    }
}
