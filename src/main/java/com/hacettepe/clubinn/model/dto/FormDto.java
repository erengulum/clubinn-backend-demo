package com.hacettepe.clubinn.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormDto {

    private Long formId;

    private String bagliolduguGrup;

    private Collection<QuestionDto> questionList;

}