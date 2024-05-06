package com.project.stock.config;

import com.project.stock.auth.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.naming.AuthenticationException;


@Component
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtFilter;

    public JwtInterceptor(JwtUtil jwtTokenValidator) {
        this.jwtFilter = jwtTokenValidator;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws AuthenticationException {
            String token = request.getHeader("Authorization");
            if(token == null){
                throw new AuthenticationException("No Authorization header found.");
            }else if(!jwtFilter.validateClaims(jwtFilter.parseJwtClaims(token))){
                throw new AuthenticationException("Invalid JWT token");
            }
            else return true;
    }
}
