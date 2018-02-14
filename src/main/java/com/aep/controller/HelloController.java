package com.aep.controller;

import org.json.simple.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class AuthorizationController {
    @RequestMapping(method = RequestMethod.GET)
    public String rest() {
        Authentication outh = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        JSONObject resultJson = new JSONObject();
        resultJson.put("program", "petsikserver");
        resultJson.put("version", "1.0");
        resultJson.put("company", "aep");
        resultJson.put("user_message", "hello " + outh.getName());
        return    resultJson.toString();
    }
}