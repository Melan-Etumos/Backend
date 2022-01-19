package com.dsm.me.model.user.controller;

import com.dsm.me.model.user.dto.UserCreateRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AuthController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users")
    public void join(@RequestBody @Valid UserCreateRequestDto userCreateDto){

    }
}
