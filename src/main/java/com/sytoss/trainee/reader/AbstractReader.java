package com.sytoss.trainee.reader;

import com.sytoss.trainee.dto.Person;

import java.util.List;

public abstract class AbstractReader implements Reader {
    protected List<Person> persons;
    @Override
    public abstract List<Person> read(String fileName);
}
