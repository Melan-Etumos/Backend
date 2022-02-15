package com.dsm.me.model.user.dto;

import lombok.Builder;

@Builder
public class UserPageResponseDto {
    private final String nickname;
    private final String id;
    private final String profile;
    private final String background;
    private final String introduce;
    private final boolean isTrack;
}
