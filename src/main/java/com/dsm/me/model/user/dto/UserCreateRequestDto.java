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

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*\\\\W)(?=\\\\S+$).{3,25}", message = "password는 영문 소문자, 특수기호, 숫자가 한 개 이상 포함되어야 합니다.")
    @NotBlank
    private final String password;

    @NotBlank
    private final String nickname;
    // id
}
