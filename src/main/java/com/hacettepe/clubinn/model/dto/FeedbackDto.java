package com.hacettepe.clubinn.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDto {

    private Long id;

    private UserDto owner;

    private Long rating;

    private String comment;

}
