package com.hacettepe.clubinn.model.dto;

import lombok.Data;

//Giris sırasında front-end tarafının bize göndereceği request objesi
@Data
public class LoginRequest {
    private String username;
    private String password;
}