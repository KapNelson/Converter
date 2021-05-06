package com.sytoss.trainee.writer;

import com.sytoss.trainee.dto.Person;

import java.util.List;

public interface Writer {
    void write(List<Person> persons, String fileName);
}
