package com.aep.controller.secutity;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/outh")
@CrossOrigin
public class AuthenticationController {
    @Autowired
    AuthenticationManager authManager;

    @RequestMapping(method = RequestMethod.POST)
    public String authentication(@RequestBody String authString) {
        JSONObject result = new JSONObject();
        result.put("isAuthenticate", "false");
        result.put("data", "no_user");
        JSONObject jsonAuthData = new JSONObject(authString);
        if (jsonAuthData != null) {
            String user = jsonAuthData.getString("username");
            String password = jsonAuthData.getString("password");
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, password);
            try {
                Authentication aouth = authManager.authenticate(token);
                if (aouth.isAuthenticated()) {
                    result.put("isAuthenticate", "true");
                    result.put("data", getToken(user, password));
                }
            } catch (AuthenticationException exception) {
                result.put("data", exception.getMessage());
            }
        }
        return result.toString();
    }

    private String getToken(String user, String password) {
        String userData = user + ":" + password;
        String result = "Basic " + Base64.encode(userData.getBytes());

        return result;
    }


}