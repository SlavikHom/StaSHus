package ru.slavikhom.userservice.dto;

import lombok.Data;

@Data
public class SigninRequest {
    private String handle;
    private String password;
}
