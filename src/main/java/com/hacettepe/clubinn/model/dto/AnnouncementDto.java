package com.hacettepe.clubinn.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Announcement Data Transfer Object")
public class AnnouncementDto {

    @ApiModelProperty(value = "Announcement ID")
    private Long id;

    @ApiModelProperty(value = "Announcement Headline")
    private String headline;

    @ApiModelProperty(value = "Announcement Content")
    private String content;

    @ApiModelProperty(value = "Announcement SubClub")
    private SubClubDto subClub;

}
