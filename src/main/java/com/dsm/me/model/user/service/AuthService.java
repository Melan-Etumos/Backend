package com.dsm.me.model.user.service;

import com.dsm.me.global.error.exceptions.*;
import com.dsm.me.global.mail.MailContent;
import com.dsm.me.global.mail.MailHandler;
import com.dsm.me.global.mail.MailReceiver;
import com.dsm.me.global.security.token.JwtUtil;
import com.dsm.me.model.user.dto.TokenResponseDto;
import com.dsm.me.model.user.dto.MemberCreateRequestDto;
import com.dsm.me.model.user.dto.MemberLoginRequestDto;
import com.dsm.me.model.user.model.Member;
import com.dsm.me.model.user.model.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;

@RequiredArgsConstructor
@Service
public class AuthService {
    @Value("${user.profile}")
    private String profile;
    @Value("${user.hax}")
    private String backgroundHax;

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final MailHandler mailHandler;

    public void join(MemberCreateRequestDto userCreateDto) {
        if(memberRepository.existsById(userCreateDto.getEmail())){
            throw new EmailOverlapException();
        }

        memberRepository.save(
                Member.builder()
                        .email(userCreateDto.getEmail())
                        .password(passwordEncoder.encode(userCreateDto.getPassword()))
                        .nickname(userCreateDto.getNickname())
                        .id(Member.Info.createRandomId(userCreateDto.getEmail()))
                        .profile(profile)
                        .backgroundHax(backgroundHax)
                        .build()
        );
    }

    public TokenResponseDto login(MemberLoginRequestDto dto) {
        Member member = memberRepository.findById(dto.getEmail()).orElseThrow(UserNotFoundException::new);
        if (!passwordEncoder.matches(dto.getPassword(), member.getPassword())){
            throw new EmailNotMatchPasswordException();
        }

        return TokenResponseDto.builder()
                .accessToken(jwtUtil.createAccessToken(dto.getEmail()))
                .refreshToken(jwtUtil.createRefreshToken(dto.getEmail())).build();
    }

    @Async
    @Transactional
    // 메서드 이름이 좀 이상한데?
    public void findPassword(String email, String userInputId) {
        final String title = "Melan Etumos 변경된 비밀번호 입니다.";

        Member member = memberRepository.findById(email).orElseThrow(UserNotFoundException::new);
        if(!userInputId.equals(member.getId())) throw new EmailNotMatchIdException();

        String changePassword = Member.Info.createRandomPassword();

        member.changePassword(passwordEncoder.encode(Member.Info.createRandomPassword()));

        try {
            mailHandler.setSenderAndReceiver(MailReceiver.builder().email(email).build());
            mailHandler.setMailContent(MailContent.builder().title(title).content(changePassword).build());
        } catch (MessagingException e) {
            throw new EmailSendException();
        }

        mailHandler.sendMail();
    }
}
