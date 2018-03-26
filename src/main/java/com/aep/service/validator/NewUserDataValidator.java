package com.aep.service.validator;

import com.aep.model.exception.UserDataNotValidException;

public class NewUserDataValidator {
    public boolean validate(String email, String password ) throws UserDataNotValidException {
        String exceptionMessage = "";
        boolean isValide = true;
        if (email.trim().isEmpty()) {
            isValide = false;
        }
        if (password.trim().isEmpty()) {
            isValide = false;
        }
        if (!isValide) {
            throw new UserDataNotValidException();
        }
        return isValide;
    }
}