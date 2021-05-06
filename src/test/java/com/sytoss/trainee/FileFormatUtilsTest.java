package com.sytoss.trainee;

import com.sytoss.trainee.utils.FileFormatUtils;
import org.junit.Assert;
import org.junit.Test;

public class FileFormatUtilsTest {
    @Test
    public void isCsv(){
        Assert.assertTrue(FileFormatUtils.isCsv("filepath/file.csv"));
    }

    @Test
    public void notIsCsv(){
        Assert.assertFalse(FileFormatUtils.isCsv("csv/filepath/file.txt"));
        Assert.assertFalse(FileFormatUtils.isCsv("csv/filepath/filecsv"));
    }

    @Test
    public void isXml(){
        Assert.assertTrue(FileFormatUtils.isXml("filepath/file.xml"));
    }

    @Test
    public void notIsXml(){
        Assert.assertFalse(FileFormatUtils.isXml("xml/filepath/file.txt"));
        Assert.assertFalse(FileFormatUtils.isXml("xml/filepath/filexml"));
    }
}
