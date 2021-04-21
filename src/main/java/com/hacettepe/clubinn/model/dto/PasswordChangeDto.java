package com.hacettepe.clubinn.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordChangeDto {

    private String oldPassword;

    private String newPassword;

    private String newPasswordConfirmation;

}