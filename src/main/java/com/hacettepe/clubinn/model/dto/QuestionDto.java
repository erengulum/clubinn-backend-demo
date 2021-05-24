package com.hacettepe.clubinn.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Question Data Transfer Object")
public class QuestionDto {

    @ApiModelProperty(value = "Question Content")
    private String questionContent;

    @ApiModelProperty(value = "Question Answer")
    private int answer;
}
