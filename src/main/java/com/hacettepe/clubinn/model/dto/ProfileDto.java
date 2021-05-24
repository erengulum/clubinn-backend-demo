package com.hacettepe.clubinn.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Profile Data Transfer Object")
public class ProfileDto {

    @ApiModelProperty(value = "Profile Phone")
    private String phone;

    @ApiModelProperty(value = "Profile City")
    private String city;

    @ApiModelProperty(value = "Profile About")
    private String about;

    @ApiModelProperty(value = "Profile Hobbies")
    private String hobbies;

}
