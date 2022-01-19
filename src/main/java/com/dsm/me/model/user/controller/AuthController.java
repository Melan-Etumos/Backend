package com.dsm.me.model.user.controller;

import com.dsm.me.model.user.dto.UserCreateRequestDto;
import com.dsm.me.model.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class AuthController {
    private final AuthService authService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users")
    public void join(@RequestBody @Valid UserCreateRequestDto userCreateDto){
        authService.join(userCreateDto);
    }
}
