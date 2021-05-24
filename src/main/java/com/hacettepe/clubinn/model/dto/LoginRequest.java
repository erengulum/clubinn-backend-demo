package com.hacettepe.clubinn.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Login Request Data Transfer Object")
public class LoginRequest {

    @ApiModelProperty(value = "Login Request Username")
    private String username;

    @ApiModelProperty(value = "Login Request Password")
    private String password;
}