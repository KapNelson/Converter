package com.sytoss.trainee;

import com.sytoss.trainee.dto.Person;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PersonTest {
    private Person personLine;

    @Test
    public void createEmptyPerson() {
        personLine = new Person();
    }

    @Test
    public void createPerson() {
        personLine = new Person("1", "Leha", "Dark", "1800-05-05", "Vampir");
    }

    @Test
    public void getPersonAttribute() {
        personLine = new Person("1", "Leha", "Dark", "1800-05-05", "Vampir");

        assertEquals(personLine.getID(), "1");
        assertEquals(personLine.getFirstName(), "Leha");
        assertEquals(personLine.getLastName(), "Dark");
        assertEquals(personLine.getBirthday(), "1800-05-05");
        assertEquals(personLine.getComment(), "Vampir");
    }

    @Test
    public void setPersonAttribute() {
        personLine = new Person();

        personLine.setID("1");
        personLine.setFirstName("Leha");
        personLine.setLastName("Dark");
        personLine.setBirthday("1800-05-05");
        personLine.setComment("Vampir");

        assertEquals(personLine.getID(), "1");
        assertEquals(personLine.getFirstName(), "Leha");
        assertEquals(personLine.getLastName(), "Dark");
        assertEquals(personLine.getBirthday(), "1800-05-05");
        assertEquals(personLine.getComment(), "Vampir");
    }

    @Test
    public void notValidID() {
        personLine = new Person("One", "Leha", "Dark", "1800-05-05", "Vampir");

        Assert.assertNull(personLine.getID());

        personLine.setID("One");

        Assert.assertNull(personLine.getID());
    }

    @Test
    public void notValidName() {
        personLine = new Person("1", "Leha123", "Dark222", "1800-05-05", "Vampir");

        Assert.assertNull(personLine.getFirstName());
        Assert.assertNull(personLine.getLastName());

        personLine.setFirstName("Leha123");
        personLine.setLastName("Dark222");

        Assert.assertNull(personLine.getFirstName());
        Assert.assertNull(personLine.getLastName());
    }

    @Test
    public void notValidBirthday() {
        personLine = new Person("1", "Leha", "Dark", "1800.05.05", "Vampir");

        Assert.assertNull(personLine.getBirthday());

        personLine.setBirthday("1800.05.05");

        Assert.assertNull(personLine.getBirthday());
    }
}
