package com.dsm.me.model.user.service;

import com.dsm.me.global.error.exceptions.EmailNotMatchIdException;
import com.dsm.me.model.user.model.User;
import com.dsm.me.model.user.model.UserRepository;
import com.dsm.me.model.user.model.redis.Code;
import com.dsm.me.model.user.model.redis.EmailCodeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTests {
    @Mock
    EmailCodeRepository codeRepository;
    @Mock
    UserRepository userRepository;

    @InjectMocks
    EmailService emailService;

    private final String email = "test@naver.com";
    private final String id = "test_id";
    private final String code = "000000";

    @Test
    @DisplayName("이메일 인증 코드 확인 성공")
    public void verificationCodeCheckTest(){
        Optional<Code> returnCode = Optional.ofNullable(Code.builder().code(code).email(email).build());

        given(codeRepository.findById(any())).willReturn(returnCode);
        boolean check = emailService.verificationCodeCheck(email, code);

        assertTrue(check);
    }

    @Test
    @DisplayName("이메일 인증 코드 확인 실패")
    public void verificationCodeCheckFailTest(){
        Optional<Code> returnCode = Optional.ofNullable(Code.builder().code("000001").email(email).build());

        given(codeRepository.findById(any())).willReturn(returnCode);
        boolean check = emailService.verificationCodeCheck(email, code);

        assertFalse(check);
    }

    @Test
    @DisplayName("비밀번호 찾기 성공")
    public void findPasswordSuccessTest() {
        Optional<User> returnUser = Optional.ofNullable(User.builder().email(email).password("test").id(id).build());
        given(userRepository.findById(any())).willReturn(returnUser);
        emailService.findPassword(email, id);
    }

    @Test
    @DisplayName("비밀번호 찾기 실패")
    public void findPasswordFailTest() {
        Optional<User> returnUser = Optional.ofNullable(User.builder().email(email).password("test").id(id).build());
        given(userRepository.findById(any())).willReturn(returnUser);
        assertThrows(EmailNotMatchIdException.class, () ->
                emailService.findPassword(email, "test_not_id")
        );
    }
}
