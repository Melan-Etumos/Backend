package com.dsm.me.model.user.model.redis;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Entity;
import javax.persistence.Id;

// 캐시
@Entity // 엔티티를 안붙이면오류남
@RedisHash("code")
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
