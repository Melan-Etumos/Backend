package com.dsm.me.model.user.service;

import com.dsm.me.global.error.exceptions.EmailNotMatchIdException;
import com.dsm.me.global.error.exceptions.EmailOverlapException;
import com.dsm.me.global.mail.MailHandler;
import com.dsm.me.global.security.token.JwtUtil;
import com.dsm.me.model.user.dto.TokenResponseDto;
import com.dsm.me.model.user.dto.MemberCreateRequestDto;
import com.dsm.me.model.user.dto.MemberLoginRequestDto;
import com.dsm.me.model.user.model.Member;
import com.dsm.me.model.user.model.MemberRepository;
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
    private MemberRepository memberRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private MailHandler mailHandler;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthService authService;

    private final String email = "test@naver.com";
    private String id = "test_id";

    @Test
    @DisplayName("Join Success")
    public void join(){
        MemberCreateRequestDto userCreateDto = new MemberCreateRequestDto(email,"password1!", "nickname");

        given(memberRepository.existsById(any())).willReturn(false);
        given(memberRepository.save(any())).willReturn(Member.builder().build());

        authService.join(userCreateDto);
    }

    @Test
    @DisplayName("Join Failed - Email overlap")
    public void emailOverlapJoinFail(){
        MemberCreateRequestDto userCreateDto = new MemberCreateRequestDto("email@naver","password1!", "nickname");

        given(memberRepository.existsById(any())).willReturn(true);

        assertThrows(EmailOverlapException.class, ()->
            authService.join(userCreateDto)
        );
    }

    @Test
    @DisplayName("비밀번호 찾기 성공")
    public void findPasswordSuccessTest() {
        Optional<Member> returnUser = Optional.ofNullable(Member.builder().email(email).password("test").id(id).build());
        given(memberRepository.findById(any())).willReturn(returnUser);
        authService.findPassword(email, id);
    }

    @Test
    @DisplayName("비밀번호 찾기 실패")
    public void findPasswordFailTest() {
        Optional<Member> returnUser = Optional.ofNullable(Member.builder().email(email).password("test").id(id).build());
        given(memberRepository.findById(any())).willReturn(returnUser);
        assertThrows(EmailNotMatchIdException.class, () ->
                authService.findPassword(email, "test_not_id")
        );
    }

    @Test
    @DisplayName("로그인 성공")
    public void loginTest(){
        final String password = "test!1";
        final String encodePassword = passwordEncoder.encode(password);

        Optional<Member> returnUser = Optional.ofNullable(Member.builder().email(email).password(encodePassword).id(id).build());
        given(memberRepository.findById(any())).willReturn(returnUser);
        given(passwordEncoder.matches(password, encodePassword)).willReturn(true);

        TokenResponseDto res = authService.login(new MemberLoginRequestDto(email, password));

        assertNotNull(res);
    }
}
