package com.aep.service.validator;

import com.aep.model.exception.UserDataNowValidException;

public class NewUserDataValidator {
    public boolean validate(String email, String password ) throws UserDataNowValidException {
        String exceptionMessage = "";
        boolean isValide = true;
        if (email.trim().isEmpty()) {
            exceptionMessage += "email musn't be ampty";
            isValide = false;
        }
        if (password.trim().isEmpty()) {
            exceptionMessage += "password musn't be ampty";
            isValide = false;
        }
        if (!isValide) {
            throw new UserDataNowValidException(exceptionMessage);
        }
        return isValide;
    }
}