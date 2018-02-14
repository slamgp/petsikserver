package com.aep.controller.secutity;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/outh")
@CrossOrigin
public class AuthenticationController {
    @Autowired
    AuthenticationManager authManager;

    @RequestMapping(method = RequestMethod.POST)
    public String authentication(@RequestBody  String authString) {
        JSONObject jsonAuthData = new JSONObject(authString);
        if (jsonAuthData == null) {
            return "false";
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                jsonAuthData.getString("username"), jsonAuthData.getString("password"));
        try {
            Authentication aouth = authManager.authenticate(token);
            if (aouth.isAuthenticated()) {
                return "true";
            } else {
                return "false";
            }
        } catch (AuthenticationException exception) {
                return "false " + exception.getMessage();
        }
    }
}