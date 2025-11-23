package ru.slavikhom.userservice.dto;

import lombok.Data;

@Data
public class SignupRequest {
    private String handle;
    private String email;
    private String password;
}
