package com.dsm.me.model.user.model.redis;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // 엔티티를 안붙이면오류남
@RedisHash(value = "code", timeToLive = 3000)
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Code {
    @Id
    private String email;

    private String code;
}
