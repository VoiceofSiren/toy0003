package com.example.springjwt.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    // JWT는 String 타입의 key를 사용하지 않음.
    // JWT는 SecretKey 타입의 객체를 사용함.
    private SecretKey secretKey;

    private JwtUtil(@Value("${spring.jwt.secret}") String secret) {

        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS256.key().build().getAlgorithm());
    }



    // [검증 1]
    public String getUsername(String token) {
        return Jwts.parser()
                .verifyWith(secretKey) // 비밀 키를 통해 토큰이 내 서버에서 생성되었는지, 내 서버에서 생성된 키와 현재 비밀키가 서로 일치하는지 검증
                .build().parseSignedClaims(token)
                .getPayload().get("username", String.class);
    }


    // [검증 2]
    public String getRole(String token) {
        return Jwts.parser()
                .verifyWith(secretKey) // 비밀 키를 통해 토큰이 내 서버에서 생성되었는지, 내 서버에서 생성된 키와 현재 비밀키가 서로 일치하는지 검증
                .build().parseSignedClaims(token)
                .getPayload().get("role", String.class);
    }

    // [검증 3]
    public boolean getExpired(String token) {
        return Jwts.parser()
                .verifyWith(secretKey) // 비밀 키를 통해 토큰이 내 서버에서 생성되었는지, 내 서버에서 생성된 키와 현재 비밀키가 서로 일치하는지 검증
                .build().parseSignedClaims(token)
                .getPayload().getExpiration().before(new Date());
    }

    // [토큰 생성]
    public String createJwt(String username, String role, Long expiredMs) {
        return Jwts.builder()
                .claim("username", username)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))             // 토큰의 발생 시간: 현재로 설정
                .expiration(new Date(System.currentTimeMillis() + expiredMs))   // 토큰의 만료 시간 설정
                .signWith(secretKey)    // secretkey를 통해 signature을 만드는 과정
                .compact();
    }
}
