package com.foodcourt.usersmanagment.domain.util;

import com.foodcourt.usersmanagment.domain.model.OwnerModel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Pattern;

@Component
public class UseValidationUtil {

    public void isValidUser(OwnerModel user) {

        if (user.getEmail() == null || !Pattern.matches("^[\\w-.]+@[\\w-]+\\.[a-zA-Z]{2,}$", user.getEmail())) {
            throw new IllegalArgumentException("The email is invalid");
        }

        if (user.getPhoneNumber() == null || !Pattern.matches("^\\+?\\d{1,13}$", user.getPhoneNumber())) {
            throw new IllegalArgumentException(" The phone number must have a maximum of 13 characters and can start with +");
        }

        if (user.getDni() == null || !user.getDni().matches("\\d+")) {
            throw new IllegalArgumentException("The document must contain only numbers");
        }

        if (user.getBirthdayDate() == null || !isValidAge(user.getBirthdayDate())) {
            throw new IllegalArgumentException("The user must be of legal age");
        }

    }

    private boolean isValidAge(Date date) {
        LocalDate birthdayDate = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return Period.between(birthdayDate, LocalDate.now()).getYears() >= 18;
    }
}
