package com.hacettepe.clubinn.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hacettepe.clubinn.model.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {


    private String phone;

    private String city;

    private String about;

    private String hobbies;

}
