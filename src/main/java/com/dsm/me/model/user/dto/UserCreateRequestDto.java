package com.dsm.me.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
public class UserCreateRequestDto {
    @Email(message = "email 형식이 맞지 않습니다.")
    @NotBlank
    private final String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[0-9])(?=.*[#?!@$%^*&-]).{3,}$")
    @NotBlank
    private final String password;

    @NotBlank
    private final String nickname;
    // id
}
