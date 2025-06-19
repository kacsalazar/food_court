package com.foodcourt.usersmanagment.domain.usecase.util;

import com.foodcourt.usersmanagment.domain.exception.ConstantException;
import com.foodcourt.usersmanagment.domain.exception.DomainException;
import com.foodcourt.usersmanagment.domain.model.SaveUserModel;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Pattern;

@UtilityClass
public class UseValidationUtil {

    public static void isValidUser(SaveUserModel user) {

        if (user.getEmail() == null || !Pattern.matches("^[\\w-.]+@[\\w-]+\\.[a-zA-Z]{2,}$", user.getEmail())) {
            throw new DomainException(ConstantException.INVALID_EMAIL);
        }

        if (user.getPhoneNumber() == null || !Pattern.matches("^\\+?\\d{1,13}$", user.getPhoneNumber())) {
            throw new DomainException(ConstantException.INVALID_PHONE_NUMBER);
        }

        if (user.getDni() == null || !user.getDni().matches("\\d+")) {
            throw new DomainException(ConstantException.INVALID_DNI);
        }

        if (user.getBirthdayDate() == null || !isValidAge(user.getBirthdayDate())) {
            throw new DomainException(ConstantException.INVALID_BIRTHDAY_DATE);
        }

    }

    private boolean isValidAge(Date date) {
        LocalDate birthdayDate = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return Period.between(birthdayDate, LocalDate.now()).getYears() >= 18;
    }
}
