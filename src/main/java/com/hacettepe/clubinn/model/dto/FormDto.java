package com.hacettepe.clubinn.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Questionnaire Data Transfer Object")
public class FormDto {

    @ApiModelProperty(value = "Questionnaire ID")
    private Long formId;

    @ApiModelProperty(value = "Questionnaire SubClub")
    private String bagliolduguGrup;

    @ApiModelProperty(value = "Questionnaire Question List")
    private Collection<QuestionDto> questionList;

}
