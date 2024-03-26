package com.example.springjwt.jwt;

import com.example.springjwt.dto.CustomedUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collection;
import java.util.Iterator;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public LoginFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

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

        CustomedUserDetails customedUserDetails = (CustomedUserDetails) authentication.getPrincipal();

        // [1] Get username
        String username = customedUserDetails.getUsername();

        // [2] Get role
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority authority = iterator.next();
        String role = authority.getAuthority();

        // [3] Set expiredMs
        Long expiredMs = 60 * 60 * 10L;

        // [4] Generate JWT token.
        String jwtToken = jwtUtil.createJwt(username, role, expiredMs);

        // [5] JWT를 담아서 응답.
        // key => "Authorization"
        // HTTP 인증 방식 (RFC 7235) => prefix에 인증방식 (Bearer) 기재 후 한 칸 띄우고 토큰을 덧붙이기
        response.addHeader("Authorization", "Bearer " + jwtToken);
    }

    /**
     * 로그인 실패 시 실행되는 메서드.
     * 로그인 성공 시 JWT를 발급하지 않음.
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failure) {
        response.setStatus(401);
    }
}
