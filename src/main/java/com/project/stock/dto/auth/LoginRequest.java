package com.project.stock.dto.auth;

import lombok.*;

@Getter
@Data
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String email;
    private String password;
}
