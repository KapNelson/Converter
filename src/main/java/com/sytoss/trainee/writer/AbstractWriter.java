package com.sytoss.trainee.writer;

import com.sytoss.trainee.dto.Person;

import java.util.List;

public abstract class AbstractWriter implements Writer{
    @Override
    public abstract void write(List<Person> persons, String fileName);
}
