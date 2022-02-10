package com.dsm.me.model.user.model.redis;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataRedisTest
public class EmailCodeRepositoryTests {
    @Autowired
    EmailCodeRepository emailCodeRepository;

    @Test
    public void saveTest(){
        final String code = "111111";
        Code entity = emailCodeRepository.save(Code.builder().email("test@naver.com").code(code).build());
        assertEquals(code, entity.getCode());
    }
}
