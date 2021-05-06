package com.sytoss.trainee;

import org.junit.Test;

public class DataConverterTest {
    @Test
    public void testMain(){
        String[] args = new String[]{"src/test/resources/test.csv","src/test/resources/test.xml"};
        DataConverter.main(args);
    }
}
