package com.dsm.me.model.user.service;

import com.dsm.me.global.exception.EmailOverlapException;
import com.dsm.me.model.user.dto.UserCreateRequestDto;
import com.dsm.me.model.user.model.User;
import com.dsm.me.model.user.model.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    @Value("${user.profile}")
    private String profile;
    @Value("${user.hax}")
    private String backgroundHax;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void join(UserCreateRequestDto userCreateDto){
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
}
