package com.hacettepe.clubinn.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubClubDto {

    private Long id;

    private String imageurl;

    private String subClubName;

    private String description;

    private Long clubCategoryId;

    private ClubCategoryDto clubCategory;



}