package com.dsm.me.model.user.service;

import com.dsm.me.model.user.model.redis.Code;
import com.dsm.me.model.user.model.redis.EmailCodeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTests {
    @Mock
    EmailCodeRepository codeRepository;

    @InjectMocks
    EmailService emailService;

    @Test
    @DisplayName("이메일 인증 코드 확인 성공")
    public void verificationCodeCheckTest(){
        given(codeRepository.existsByEmailAndCode(any(), any())).willReturn(true);
        boolean check = emailService.verificationCodeCheck(Code.builder().email("email@naver.com").code("000000").build());

        assertTrue(check);
    }

    @Test
    @DisplayName("이메일 인증 코드 확인 실패")
    public void verificationCodeCheckFailTest(){
        given(codeRepository.existsByEmailAndCode(any(), any())).willReturn(false);
        boolean check = emailService.verificationCodeCheck(Code.builder().email("email@naver.com").code("000000").build());

        assertFalse(check);
    }
}
