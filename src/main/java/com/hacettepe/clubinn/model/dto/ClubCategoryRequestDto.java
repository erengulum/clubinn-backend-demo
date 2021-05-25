package com.hacettepe.clubinn.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Club Request Data Transfer Object")
public class ClubCategoryRequestDto {

    @ApiModelProperty(value = "Club Request ID")
    private Long req_id;

    @ApiModelProperty(value = "Club Request Category Name")
    private String clubCategoryName;

    @ApiModelProperty(value = "Club Request Reason")
    private String reason;

    @ApiModelProperty(value = "Club Request Description")
    private String description;

    @ApiModelProperty(value = "Total number of votes of Club Request")
    private Long numberOfVotes;

    @ApiModelProperty(value = "Club Request Image")
    private String imageurl;

}

