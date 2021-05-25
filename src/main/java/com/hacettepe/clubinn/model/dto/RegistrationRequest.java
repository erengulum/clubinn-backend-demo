package com.hacettepe.clubinn.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Registration Data Transfer Object")
public class RegistrationRequest {

    @ApiModelProperty(value = "Registration First Name")
    private String firstName;

    @ApiModelProperty(value = "Registration Surname")
    private String surname;

    @ApiModelProperty(value = "Registration Username")
    private String username;

    @ApiModelProperty(value = "Registration Password")
    private String password;

    @ApiModelProperty(value = "Registration Email")
    private String email;

}

