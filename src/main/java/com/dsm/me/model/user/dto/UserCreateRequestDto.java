package com.dsm.me.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCreateRequestDto {
    private final String email;
    private final String password;
    private final String nickname;
    // id
}
