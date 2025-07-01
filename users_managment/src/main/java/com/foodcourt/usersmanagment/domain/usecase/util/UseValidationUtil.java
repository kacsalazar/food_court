package com.foodcourt.usersmanagment.domain.usecase.util;

import com.foodcourt.usersmanagment.domain.exception.*;
import com.foodcourt.usersmanagment.domain.model.CreateUserModel;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Pattern;

@UtilityClass
public class UseValidationUtil {

    public static void isValidUser(CreateUserModel user) {

        if (user.getEmail() == null || !Pattern.matches("^[\\w-.]+@[\\w-]+\\.[a-zA-Z]{2,}$", user.getEmail())) {
            throw new InvalidEmailException();
        }

        if (user.getPhoneNumber() == null || !Pattern.matches("^\\+?\\d{1,13}$", user.getPhoneNumber())) {
            throw new InvalidPhoneNumberException();
        }

        if (user.getDni() == null || !user.getDni().matches("\\d+")) {
            throw new InvalidDniException();
        }

        if (user.getBirthdayDate() == null || !isValidAge(user.getBirthdayDate())) {
            throw new InvalidBirthDayDateException();
        }
    }

    private boolean isValidAge(Date date) {
        LocalDate birthdayDate = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return Period.between(birthdayDate, LocalDate.now()).getYears() >= 18;
    }
}
