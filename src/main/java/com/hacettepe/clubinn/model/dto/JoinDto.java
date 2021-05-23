package com.hacettepe.clubinn.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinDto {

    String username; //username of the member

    Long subclubId;
}
