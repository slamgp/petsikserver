package com.aep.controller.secutity;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
@CrossOrigin
public class RegistrationController {
    @Autowired
    AuthenticationManager authManager;

    @RequestMapping(method = RequestMethod.POST)
    public String authentication(@RequestBody String authString) {
        JSONObject result = new JSONObject();
        result.put("result", "true");
        return result.toString();
    }
}