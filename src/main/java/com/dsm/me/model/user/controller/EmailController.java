package com.dsm.me.model.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/email")
public class EmailController {
    @Value("${email.send}")
    private String sendFrom;

    @GetMapping("/{email}")
    public void sendEmailCode(@PathVariable String email){
        String title = "Melan Etumos 회원가입 인증 이메일";


    }
}
