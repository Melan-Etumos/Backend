package com.dsm.me.model.user.model.redis;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import javax.validation.constraints.Email;

@RedisHash(value = "code", timeToLive = 3000)
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Code {
    @Id @Email
    private String email;

    private String code;
}
