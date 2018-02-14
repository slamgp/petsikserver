package com.aep.controller;

import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin
@RestController
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping(method = RequestMethod.POST)
    public String rest(@RequestBody String jsonStr) {
        JSONObject jsonObject = new JSONObject(jsonStr);
        Authentication outh = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        JSONObject resultJson = new JSONObject();
        resultJson.put("user_message", "hello " + jsonObject.getString("name"));
        return    resultJson.toString();
    }
}