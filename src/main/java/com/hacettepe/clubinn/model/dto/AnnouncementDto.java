package com.hacettepe.clubinn.model.dto;


import com.hacettepe.clubinn.model.entity.SubClub;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementDto {


    private Long id;

    private String headline;

    private String content;

    private SubClubDto subClub;


}

