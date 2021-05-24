package com.hacettepe.clubinn.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "SubClub Data Transfer Object")
public class SubClubDto {

    @ApiModelProperty(value = "SubClub ID")
    private Long id;

    @ApiModelProperty(value = "SubClub Image")
    private String imageurl;

    @ApiModelProperty(value = "SubClub SubClub")
    private String subClubName;

    @ApiModelProperty(value = "SubClub Description")
    private String description;

    @ApiModelProperty(value = "SubClub Club Category ID")
    private Long clubCategoryId;

    @ApiModelProperty(value = "SubClub Club Category")
    private ClubCategoryDto clubCategory;

}