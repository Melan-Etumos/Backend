package com.dsm.me.global.security;

import com.dsm.me.global.error.exceptions.UserNotFoundException;
import com.dsm.me.model.user.model.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email){
        return new CustomUserDetails(memberRepository.findById(email).orElseThrow(UserNotFoundException::new));
    }
}
