package com.dsm.me.model.user.service;

import com.dsm.me.global.error.exceptions.EmailNotMatchIdException;
import com.dsm.me.global.error.exceptions.EmailOverlapException;
import com.dsm.me.global.error.exceptions.EmailSendException;
import com.dsm.me.global.error.exceptions.UserNotFoundException;
import com.dsm.me.global.mail.MailContent;
import com.dsm.me.global.mail.MailHandler;
import com.dsm.me.global.mail.MailReceiver;
import com.dsm.me.model.user.dto.TokenResponseDto;
import com.dsm.me.model.user.dto.UserCreateRequestDto;
import com.dsm.me.model.user.dto.UserLoginRequestDto;
import com.dsm.me.model.user.model.User;
import com.dsm.me.model.user.model.UserRepository;
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

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailHandler mailHandler;

    public void join(UserCreateRequestDto userCreateDto) {
        if(userRepository.existsById(userCreateDto.getEmail())){
            throw new EmailOverlapException();
        }

        userRepository.save(
                User.builder()
                        .email(userCreateDto.getEmail())
                        .password(passwordEncoder.encode(userCreateDto.getPassword()))
                        .nickname(userCreateDto.getNickname())
                        .id(User.Info.createRandomId(userCreateDto.getEmail()))
                        .profile(profile)
                        .backgroundHax(backgroundHax)
                        .build()
        );
    }

    public TokenResponseDto login(UserLoginRequestDto dto) {
        return new TokenResponseDto();
    }

    @Async
    @Transactional
    // 메서드 이름이 좀 이상한데?
    public void findPassword(String email, String userInputId) {
        final String title = "Melan Etumos 변경된 비밀번호 입니다.";

        User user = userRepository.findById(email).orElseThrow(UserNotFoundException::new);
        if(!userInputId.equals(user.getId())) throw new EmailNotMatchIdException();

        String changePassword = User.Info.createRandomPassword();

        user.changePassword(passwordEncoder.encode(User.Info.createRandomPassword()));

        try {
            mailHandler.setSenderAndReceiver(MailReceiver.builder().email(email).build());
            mailHandler.setMailContent(MailContent.builder().title(title).content(changePassword).build());
        } catch (MessagingException e) {
            throw new EmailSendException();
        }

        mailHandler.sendMail();
    }
}
