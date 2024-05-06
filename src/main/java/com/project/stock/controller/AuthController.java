package com.project.stock.controller;

import com.project.stock.auth.JwtUtil;
import com.project.stock.constant.AuthConstants;
import com.project.stock.dto.auth.LoginRequest;
import com.project.stock.dto.auth.LoginResponse;
import com.project.stock.dto.user.UserRequest;
import com.project.stock.exception.GlobalExceptionHandler;
import com.project.stock.exception.UserExistsException;
import com.project.stock.mapper.UserMapper;
import com.project.stock.model.User;
import com.project.stock.model.auth.SecurityUser;
import com.project.stock.service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import static com.project.stock.constant.AuthConstants.JWT_RESPONSE_HEADER;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest loginRequest, HttpServletResponse servletResponse) throws GlobalExceptionHandler{
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getEmail(), loginRequest.getPassword()));
        String email = authentication.getName();
        User user = User.builder().email(email).build();
        String token = jwtUtil.createToken(user);
        if (Objects.nonNull(authentication.getPrincipal())) {
            SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
            if (Objects.nonNull(securityUser) && Objects.nonNull(securityUser.getUser())) {
                user = securityUser.getUser();
            }
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        servletResponse.setHeader(JWT_RESPONSE_HEADER, token);
        servletResponse.setHeader("Access-Control-Expose-Headers", JWT_RESPONSE_HEADER);
        return ResponseEntity.ok(new LoginResponse(HttpStatus.OK.value(), email, token));
    }

    @PostMapping("/logout")
    public ResponseEntity<LoginResponse> logout(@RequestHeader("Authorization") String jwt) throws GlobalExceptionHandler{
       jwtUtil.resolveClaims()
    }


    @PostMapping("/signup")
    public ResponseEntity<LoginResponse> signup(@RequestBody UserRequest userRequest) throws GlobalExceptionHandler {
        UserRequest response = userService.create(userRequest);
        if (Objects.isNull(response)) {
            throw new UserExistsException(userRequest.getEmail() + " already exists.");
        }
        User user = User.builder().email(userRequest.getEmail()).build();
        String token = jwtUtil.createToken(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new LoginResponse(HttpStatus.CREATED.value(), user.getEmail(), token));
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String jwt) throws AuthenticationException {
        System.out.println("Line 86: "+StringUtils.isNotBlank(jwt));
        System.out.println("Line 87: "+jwt.startsWith(AuthConstants.TOKEN_PREFIX));
        if (StringUtils.isNotBlank(jwt) && jwt.startsWith(AuthConstants.TOKEN_PREFIX)) {
            jwt = jwt.substring(AuthConstants.TOKEN_PREFIX.length());
        } else {
//            throw new GlobalExceptionHandler();
        }
        System.out.println("Line 93: "+jwt);
        Claims claims = jwtUtil.parseJwtClaims(jwt);
        System.out.println("Line 95: "+claims.toString());
        UserRequest userRequest = new UserRequest();
        if (jwtUtil.validateClaims(claims)) {
            userRequest = userService.fetch(claims.getSubject());
            System.out.println("Line 97: "+userRequest.toString());
        }
        return ResponseEntity.ok("Valid");
    }
}
