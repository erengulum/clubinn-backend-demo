package com.hacettepe.clubinn.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "User Data Transfer Object")
public class UserDto {

    @ApiModelProperty(value = "User Username")
    private String username;

    @ApiModelProperty(value = "User ID")
    private Long id;

    @ApiModelProperty(value = "User Firstname")
    private String firstName;

    @ApiModelProperty(value = "User Surname")
    private String surname;

    @ApiModelProperty(value = "User Email")
    private String email;

}
