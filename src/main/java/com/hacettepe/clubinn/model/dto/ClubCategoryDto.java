package com.hacettepe.clubinn.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubCategoryDto {

    private Long id;

    private String imageurl;

    private String clubCategoryName;

    private String description;
    //One to many subclub tutacak

}
