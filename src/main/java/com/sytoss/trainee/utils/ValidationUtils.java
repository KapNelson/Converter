package com.sytoss.trainee.utils;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class ValidationUtils {
    private static final Logger log = Logger.getLogger(ValidationUtils.class);

    private ValidationUtils(){

    }

    public static boolean isValidateID(String strID) {
        if (strID.matches("^[0-9]+")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isValidateName(String strName) {
        if (strName.matches("^[a-zA-Z]+")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isValidateDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setLenient(false);

        try {
            Date date = formatter.parse(strDate);
            return true;
        } catch (ParseException e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
