package com.sytoss.trainee.reader;

import com.sytoss.trainee.dto.Person;

import java.util.List;

public interface Reader {
    List<Person> read(String fileName);
}
