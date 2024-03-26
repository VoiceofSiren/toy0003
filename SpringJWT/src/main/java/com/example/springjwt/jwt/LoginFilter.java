package com.example.springjwt.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        // [1] 클라이언트에 요청하여 username과 password를 추출.
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        System.out.println("LoginFilter.attemptAuthentication(request, response) --> username = " + username);

        // [2] Spring Security에서 username과 password를 검증하기 위해서는 token에 username과 password를 담아야 함.
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password, null);

        // [3] AuthenticationManager에게 검증을 위해 token을 전달.
        return authenticationManager.authenticate(authenticationToken);
    }

    /**
     * 로그인 성공 시 실행되는 메서드.
     * 로그인 성공 시 JWT를 발급하면 됨.
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
        System.out.println("Login Successfully!");
    }

    /**
     * 로그인 실패 시 실행되는 메서드.
     * 로그인 성공 시 JWT를 발급하지 않음.
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failure) {
        System.out.println("Login failed...");
    }
}
