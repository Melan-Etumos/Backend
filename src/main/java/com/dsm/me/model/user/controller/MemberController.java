package com.dsm.me.model.user.controller;

import com.dsm.me.global.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Email;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final AuthenticationFacade authenticationFacade;

    @GetMapping("/user/{email}")
    public void userPage(@PathVariable @Email  String email){

    }

    @GetMapping("/user")
    public void myPage(){

    }
}
