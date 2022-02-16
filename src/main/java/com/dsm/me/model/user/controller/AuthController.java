package com.dsm.me.model.user.controller;

import com.dsm.me.model.user.dto.TokenResponseDto;
import com.dsm.me.model.user.dto.UserCreateRequestDto;
import com.dsm.me.model.user.dto.UserLoginRequestDto;
import com.dsm.me.model.user.service.AuthService;
import com.dsm.me.model.user.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@RequiredArgsConstructor
@RestController
public class AuthController {
    private final AuthService authService;
    private final EmailService emailService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users")
    public void join(@RequestBody @Valid UserCreateRequestDto userCreateDto){
        authService.join(userCreateDto); // 닉네임 필터 되면 좋을 듯 https://lcodea.tistory.com/23
    }

    @PostMapping("/auth")
    public TokenResponseDto login(@RequestBody UserLoginRequestDto loginRequestDto){
        return authService.login(loginRequestDto);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/password")
    public void findPassword(@RequestParam @Email final String email, @RequestParam final String id){
        authService.findPassword(email, id);
    }
}
