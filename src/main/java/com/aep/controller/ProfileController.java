package com.aep.controller;

import com.aep.model.user.User;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin
@RestController
public class ProfileController {
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String rest() {
        Authentication outh = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) outh.getPrincipal();
        JSONObject resultJson = new JSONObject();
        resultJson.put("id", user.getId());
        resultJson.put("email", user.getEmail());
        resultJson.put("name", getValue(user.getName()));
        resultJson.put("phone", getValue(user.getPhone()));
        return    resultJson.toString();
    }

    @RequestMapping(value = "/profile/setnewmaindata",  method = RequestMethod.POST)
    public String setNewMainData(@RequestBody String jsonStr) {
        JSONObject jsonObject = new JSONObject(jsonStr);
        Authentication outh = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) outh.getPrincipal();
        user.setEmail(jsonObject.getString("email"));
        user.setName(jsonObject.getString("name"));
        user.setPhone(jsonObject.getString("phone"));
        JSONObject resultJson = new JSONObject();
        resultJson.put("result", "true");
        return    resultJson.toString();
    }

    private String getValue(String value) {
        if (value == null) {
            return "";
        } else {
            return value;
        }
    }
}