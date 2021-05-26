package com.hacettepe.clubinn.service;

import com.hacettepe.clubinn.model.dto.FormDto;
import com.hacettepe.clubinn.model.dto.QuestionDto;
import com.hacettepe.clubinn.model.dto.SubClubDto;
import com.hacettepe.clubinn.model.entity.Form;

import java.util.Collection;
import java.util.List;

public interface FormService {

    Boolean createForm(FormDto formDto);

    Boolean createQuestions(Collection<QuestionDto> questionList, Form form);

    List<FormDto> getAllBySubClub(Long[] subClubIdList);

    List<SubClubDto> questionnaire(List<FormDto> formDtoList);

    int percentageCalculator(int answer);

    Boolean formCompleted(Long[] subClubIdList);

}