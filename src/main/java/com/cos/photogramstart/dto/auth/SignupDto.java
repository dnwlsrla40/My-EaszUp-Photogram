package com.cos.photogramstart.dto.auth;

import lombok.Data;

@Data   // Getter, Setter
public class SignupDto {
    private String username;
    private String password;
    private String email;
    private String name;
}