package com.sytoss.trainee.dto;

import com.sytoss.trainee.utils.ValidationUtils;

import java.util.Objects;

public class Person {
    private String id;
    private String firstName;
    private String lastName;
    private String birthday;
    private String comment;

    public Person() {

    }

    public Person(String id, String firstName, String lastName, String birthday, String comment) {
        if (ValidationUtils.isValidateID(id)) {
            this.id = id;
        } else {
            this.id = null;
        }

        if (ValidationUtils.isValidateName(firstName)) {
            this.firstName = firstName;
        } else {
            this.firstName = null;
        }

        if (ValidationUtils.isValidateName(lastName)) {
            this.lastName = lastName;
        } else {
            this.lastName = null;
        }

        if (ValidationUtils.isValidateDate(birthday)) {
            this.birthday = birthday;
        } else {
            this.birthday = null;
        }

        this.comment = comment;
    }

    public String getID() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getComment() {
        return comment;
    }


    public void setID(String id) {
        if (ValidationUtils.isValidateID(id)) {
            this.id = id;
        } else {
            this.id = null;
        }
    }

    public void setFirstName(String firstName) {
        if (ValidationUtils.isValidateName(firstName)) {
            this.firstName = firstName;
        } else {
            this.firstName = null;
        }
    }

    public void setLastName(String lastName) {
        if (ValidationUtils.isValidateName(lastName)) {
            this.lastName = lastName;
        } else {
            this.lastName = null;
        }
    }

    public void setBirthday(String birthday) {
        if (ValidationUtils.isValidateDate(birthday)) {
            this.birthday = birthday;
        } else {
            this.birthday = null;
        }
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(birthday, person.birthday) && Objects.equals(comment, person.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthday, comment);
    }
}
