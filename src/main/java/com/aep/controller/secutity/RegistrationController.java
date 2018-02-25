package com.aep.controller.secutity;

import com.aep.model.exception.EmailIsBlockedException;
import com.aep.model.exception.EmailIsBuseException;
import com.aep.model.exception.UserDataNowValidException;
import com.aep.service.UserService;
import com.aep.service.validator.NewUserDataValidator;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
@CrossOrigin
public class RegistrationController {
    @Autowired
    NewUserDataValidator newUserDataValidator;
    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public String authentication(@RequestBody String regString) {
        JSONObject result = new JSONObject();
        result.put("result", "true");
        result.put("message", "Congratulations registration was successful!");

        JSONObject jsonAuthData = new JSONObject(regString);
        if (jsonAuthData != null) {
            String email = jsonAuthData.getString("username");
            String password = jsonAuthData.getString("password");
            try {
                if (newUserDataValidator.validate(email, password)) {
                    userService.registrateUser(email, password);
                }
            } catch (UserDataNowValidException e) {
                result.put("result", "false");
                result.put("message", e.getMessage());
            } catch (EmailIsBlockedException e) {
                result.put("result", "false");
                result.put("message", "this is email was blocked!");
            } catch (EmailIsBuseException e) {
                result.put("result", "false");
                result.put("message", "the user with this name is already registered!");
            }
        } else {
            result.put("result", "false");
            result.put("message", "System error!!");
        }


        return result.toString();
    }
}