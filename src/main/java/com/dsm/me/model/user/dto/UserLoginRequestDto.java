package com.dsm.me.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@Getter
public class UserLoginRequestDto {
    @Email
    @NotBlank
    private final String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[0-9])(?=.*[#?!@$%^*&-]).{3,}$")
    private final String password;
}
