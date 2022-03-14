package com.dsm.me.global.security.token;

import com.dsm.me.global.error.exceptions.token.TokenErrorCode;
import com.dsm.me.global.error.exceptions.token.TokenException;
import com.dsm.me.global.security.CustomUserDetailsService;
import com.dsm.me.model.user.model.redis.RefreshToken;
import com.dsm.me.model.user.model.redis.RefreshTokenRepository;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    @Value("{auth.jwt.secret}")
    private String secretKey;

    private final RefreshTokenRepository tokenRepository;
    private final CustomUserDetailsService userDetailsService;

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

    private String getEmail(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody().getSubject();
    }

    public void validateToken(String token){
        try{
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        } catch (SecurityException | MalformedJwtException e){
            throw new TokenException(TokenErrorCode.INVALID_SIGNATURE);
        } catch (ExpiredJwtException e){
            throw new TokenException(TokenErrorCode.TOKEN_EXPIRED);
        } catch (UnsupportedJwtException e){
            throw new TokenException(TokenErrorCode.UNSUPPORTED_TOKEN);
        } catch (SignatureException e) {
            throw new TokenException(TokenErrorCode.SIGNATURE_TOKEN);
        } catch (Exception e){
            throw new TokenException(TokenErrorCode.INVALID_TOKEN);
        }
    }

    public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(getEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
