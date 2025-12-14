package com.sweetshop.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



@AllArgsConstructor
@Getter
@Setter
public class LoginResponseDTO {
        private Long id;
        private String username;
        private String role;
        private String token;
    }


