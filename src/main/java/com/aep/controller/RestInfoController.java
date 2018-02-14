package com.aep.controller;

import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/rest")
public class RestInfoController {
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

    @RequestMapping(method = RequestMethod.POST)
    public String restPOST() {
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