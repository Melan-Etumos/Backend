package com.dsm.me.model.user.service;

import com.dsm.me.global.error.exceptions.EmailNotMatchIdException;
import com.dsm.me.global.error.exceptions.EmailOverlapException;
import com.dsm.me.global.mail.MailHandler;
import com.dsm.me.model.user.dto.TokenResponseDto;
import com.dsm.me.model.user.dto.UserCreateRequestDto;
import com.dsm.me.model.user.dto.UserLoginRequestDto;
import com.dsm.me.model.user.model.User;
import com.dsm.me.model.user.model.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
public class AuthServiceTests {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private MailHandler mailHandler;

    @InjectMocks
    private AuthService authService;

    private final String email = "test@naver.com";
    private String id = "test_id";

    @Test
    @DisplayName("Join Success")
    public void join(){
        UserCreateRequestDto userCreateDto = new UserCreateRequestDto(email,"password1!", "nickname");

        given(userRepository.existsById(any())).willReturn(false);
        given(userRepository.save(any())).willReturn(User.builder().build());

        authService.join(userCreateDto);
    }

    @Test
    @DisplayName("Join Failed - Email overlap")
    public void emailOverlapJoinFail(){
        UserCreateRequestDto userCreateDto = new UserCreateRequestDto("email@naver","password1!", "nickname");

        given(userRepository.existsById(any())).willReturn(true);

        assertThrows(EmailOverlapException.class, ()->
            authService.join(userCreateDto)
        );
    }

    @Test
    @DisplayName("비밀번호 찾기 성공")
    public void findPasswordSuccessTest() {
        Optional<User> returnUser = Optional.ofNullable(User.builder().email(email).password("test").id(id).build());
        given(userRepository.findById(any())).willReturn(returnUser);
        authService.findPassword(email, id);
    }

    @Test
    @DisplayName("비밀번호 찾기 실패")
    public void findPasswordFailTest() {
        Optional<User> returnUser = Optional.ofNullable(User.builder().email(email).password("test").id(id).build());
        given(userRepository.findById(any())).willReturn(returnUser);
        assertThrows(EmailNotMatchIdException.class, () ->
                authService.findPassword(email, "test_not_id")
        );
    }

    @Test
    @DisplayName("로그인 성공")
    public void loginTest(){
        final String password = "test!1";

        Optional<User> returnUser = Optional.ofNullable(User.builder().email(email).password(password).id(id).build());
        given(userRepository.findById(any())).willReturn(returnUser);

        TokenResponseDto res = authService.login(new UserLoginRequestDto(email, password));

        assertNotNull(res);
    }
}
