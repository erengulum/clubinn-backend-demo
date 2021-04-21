package com.hacettepe.clubinn.model.dto;


import com.hacettepe.clubinn.model.entity.ClubCategory;
import com.hacettepe.clubinn.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
