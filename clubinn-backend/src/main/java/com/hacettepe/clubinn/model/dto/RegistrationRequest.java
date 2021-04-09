package com.hacettepe.clubinn.model.dto;

import lombok.Data;


@Data
public class RegistrationRequest {
    private String firstName;

    private String surname;

    private String username;

    private String password;

    private String email;

}
