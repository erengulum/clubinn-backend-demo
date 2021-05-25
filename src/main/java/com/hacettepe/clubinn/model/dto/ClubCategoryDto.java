package com.hacettepe.clubinn.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Club Category Data Transfer Object")
public class ClubCategoryDto {

    @ApiModelProperty(value = "Club Category ID")
    private Long id;

    @ApiModelProperty(value = "Club Category Image")
    private String imageurl;

    @ApiModelProperty(value = "Club Category Name")
    private String clubCategoryName;

    @ApiModelProperty(value = "Club Category Description")
    private String description;

}

