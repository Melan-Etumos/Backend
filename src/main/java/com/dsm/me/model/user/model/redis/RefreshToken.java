package com.dsm.me.model.user.model.redis;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;


@RedisHash(value = "token", timeToLive = 90000000) // 보류
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {
    @Id
    private String email;

    private String refreshToken;
}
