package com.dsm.me.model.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Email;

@RestController
public class UserController {
    @GetMapping("/user/{email}")
    public void userPage(@PathVariable @Email  String email){

    }
}
