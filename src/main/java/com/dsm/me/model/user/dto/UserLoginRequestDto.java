package com.dsm.me.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserLoginRequestDto {
    private final String email;
    private final String password;
}
