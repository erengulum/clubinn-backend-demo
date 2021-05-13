package com.hacettepe.clubinn.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubCategoryRequestDto {


    private Long req_id;

    private String clubCategoryName;

    private String reason;

    private String description;

    private Long numberOfVotes;

    private String imageurl;


}
