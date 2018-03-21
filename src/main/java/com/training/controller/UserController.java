package com.training.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.training.model.common.CreateUpdateResponse;
import com.training.model.login.LoginRequest;
import com.training.model.login.LoginResponse;
import com.training.model.profile.ProfileResponse;
import com.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    private LoginResponse login(@RequestBody LoginRequest request) throws JsonProcessingException {
        return userService.authLogin(request);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    private CreateUpdateResponse login(@RequestBody ProfileResponse request) throws JsonProcessingException {
        return userService.createOrUpdate(request, true);
    }

}
