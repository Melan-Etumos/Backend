package com.dsm.me.model.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenResponseDto {
    private final String accessToken;
    private final String refreshToken;
}
