package com.project.stock.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private int code;
    private String email;
    private String token;

    public LoginResponse(int code, String token) {
        this.code = code;
        this.token = token;
    }
}