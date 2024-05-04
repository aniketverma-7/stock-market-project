package com.project.stock.dto.user;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRequest {
    private String email;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String password;
}
