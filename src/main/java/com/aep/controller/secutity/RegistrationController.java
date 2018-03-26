package com.aep.controller.secutity;

import com.aep.model.exception.EmailIsBlockedException;
import com.aep.model.exception.EmailIsBuseException;
import com.aep.model.exception.UserDataNotValidException;
import com.aep.service.UserService;
import com.aep.service.validator.NewUserDataValidator;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
        result.put("error_type", "");

        JSONObject jsonAuthData = new JSONObject(regString);
        if (jsonAuthData != null) {
            String email = jsonAuthData.getString("username");
            String password = jsonAuthData.getString("password");
            try {
                if (newUserDataValidator.validate(email, password)) {
                    userService.registrateUser(email, password);
                }
            } catch (UserDataNotValidException e) {
                result.put("result", "false");
                result.put("error_type", e.getClass().getSimpleName());
            } catch (EmailIsBlockedException e) {
                result.put("result", "false");
                result.put("error_type", e.getClass().getSimpleName());
            } catch (EmailIsBuseException e) {
                result.put("result", "false");
                result.put("error_type", e.getClass().getSimpleName());
            }
        } else {
            result.put("result", "false");
            result.put("error_type", "server error!!");
        }


        return result.toString();
    }
}