package com.hacettepe.clubinn.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Password Change Data Transfer Object")

public class PasswordChangeDto {

    @ApiModelProperty(value = "Password Change Old Password")
    private String oldPassword;

    @ApiModelProperty(value = "Password Change New Password")
    private String newPassword;

    @ApiModelProperty(value = "Password Change Password Confirmation")
    private String newPasswordConfirmation;

}