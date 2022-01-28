package com.dsm.me.model.user.controller;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Email;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Value("${email.send}")
    private String sendFrom;

    @GetMapping
    public void sendEmailCode(@RequestParam @Email String email){
        String title = "Melan Etumos 회원가입 인증 이메일";
    }
}
