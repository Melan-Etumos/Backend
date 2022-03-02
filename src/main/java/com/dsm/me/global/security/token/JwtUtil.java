package com.dsm.me.global.security.token;

import com.dsm.me.model.user.model.redis.RefreshToken;
import com.dsm.me.model.user.model.redis.RefreshTokenRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    @Value("{auth.jwt.secret}")
    private String secretKey;

    private final RefreshTokenRepository tokenRepository;

    public String createAccessToken(String email) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+30*1000))
                .setSubject(email)
                .claim("type","access")
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String createRefreshToken(String email) {
        String refreshToken =  Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+30000*10000))
                .setSubject(email)
                .claim("type","refresh")
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        tokenRepository.save(RefreshToken.builder().email(email).refreshToken(refreshToken).build());

        return refreshToken;
    }
}
