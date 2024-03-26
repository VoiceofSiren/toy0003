package com.example.springjwt.jwt;

import com.example.springjwt.dto.CustomedUserDetails;
import com.example.springjwt.entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // [1] request에서 Authorization이라는 Header를 추출
        String authorization = request.getHeader("Authorization");

        // [2] Authorization Header를 검증
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            System.out.println("token null");

            // [2.1] 메서드 종료 이전에 filterChain을 종료하고 다음 filter로 현재 request와 response를 넘겨줌.
            filterChain.doFilter(request, response);

            // [2.2] Note! 조건문에 해당되면 메서드를 종료시켜야 함.
            return;
        }

        // [3] 순수 token만을 추출
        String token = authorization.split(" ")[1];

        // [4] token 소멸 시간 검증
        if (jwtUtil.getExpired(token)) {
            System.out.println("token expired");

            // [4.1] 메서드 종료 이전에 filterChain을 종료하고 다음 filter로 현재 request와 response를 넘겨줌.
            filterChain.doFilter(request, response);

            // [4.2] Note! 조건문에 해당되면 메서드를 종료시켜야 함.
            return;
        }

        // [5] token에서 username과 role을 추출
        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        // [6] User Entity 생성
        //      - 비밀번호는 token에 담겨있지 않으므로 임시로 초기화시켜야 함.
        User user = new User(username, "tempPassword", role);

        // [7] UserDetails에 User Entity를 담기
        CustomedUserDetails customedUserDetails = new CustomedUserDetails(user);

        // [8] Spring Security Authentication Token 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(customedUserDetails,
                null,
                customedUserDetails.getAuthorities());

        // [9] 세션에 사용자 정보를 등록
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
