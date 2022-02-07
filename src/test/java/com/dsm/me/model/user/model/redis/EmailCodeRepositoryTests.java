package com.dsm.me.model.user.model.redis;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class EmailCodeRepositoryTests {
    @Autowired
    EmailCodeRepository emailCodeRepository;

    @Test
    public void saveTest(){
        final String code = "111111";
        Code entity = emailCodeRepository.save(Code.builder().email("test@naver.com").code(code).build());
        assertEquals(code, entity.getCode());
    }

    @Test
    public void existCodeTest(){
        saveTest();
        boolean check = emailCodeRepository.existsByEmailAndCode("test@naver.com", "111111");
        assertTrue(check);
    }

    @Test
    public void existCodeEmailFailTest(){
        saveTest();
        boolean check = emailCodeRepository.existsByEmailAndCode("test.com", "111111");
        assertFalse(check);
    }

    @Test
    public void existCodeDifferFailTest(){
        saveTest();
        boolean check = emailCodeRepository.existsByEmailAndCode("test@naver.com", "000000");
        assertFalse(check);
    }
}
