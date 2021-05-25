package com.hacettepe.clubinn.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Join Data Transfer Object")
public class JoinDto {

    @ApiModelProperty(value = "Join Username")
    String username;

    @ApiModelProperty(value = "Join SubClub ID")
    Long subclubId;
}

