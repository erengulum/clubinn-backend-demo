package com.hacettepe.clubinn.model.repository;

import com.hacettepe.clubinn.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Collection<Question> getAllByForm_FormId(Long formId);

    Question findByQuestionContent(String questionContent);

}
