package com.hacettepe.clubinn.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Token Response Data Transfer Object")
public class TokenResponse {

    @ApiModelProperty(value = "Token Response Username")
    private String username;

    @ApiModelProperty(value = "Token Response Firstname")
    private String firstname;

    @ApiModelProperty(value = "Token Response Surname")
    private String surname;

    @ApiModelProperty(value = "Token Response Role")
    private String role;

    @ApiModelProperty(value = "Token Response Token")
    private String token;

}

