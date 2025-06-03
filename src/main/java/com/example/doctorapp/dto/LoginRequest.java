package com.example.doctorapp.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
