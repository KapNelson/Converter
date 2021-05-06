package com.sytoss.trainee;

import com.sytoss.trainee.utils.ValidationUtils;
import org.junit.Assert;
import org.junit.Test;


public class ValidationUtilsTest {

    @Test
    public void validateID() {
        Assert.assertTrue(ValidationUtils.isValidateID("1"));
    }

    @Test
    public void notValidateID() {
        Assert.assertFalse(ValidationUtils.isValidateID("One"));
    }

    @Test
    public void validateName() {
        Assert.assertTrue(ValidationUtils.isValidateName("Inokentiy"));
    }

    @Test
    public void notValidateName() {
        Assert.assertFalse(ValidationUtils.isValidateName("Kesha228"));
    }

    @Test
    public void validateStandartDate() {
        Assert.assertTrue(ValidationUtils.isValidateDate("2020-02-27"));
    }

    @Test
    public void notValidateFormatDate() {
        Assert.assertFalse(ValidationUtils.isValidateDate("2020.02.27"));
    }

    @Test
    public void notValidateMonth() {
        Assert.assertFalse(ValidationUtils.isValidateDate("2020-15-27"));
    }

    @Test
    public void notValidateLeapYear() {
        Assert.assertFalse(ValidationUtils.isValidateDate("2019-02-29"));
    }

    @Test
    public void validateLeapYear() {
        Assert.assertTrue(ValidationUtils.isValidateDate("2020-02-29"));
    }
}
