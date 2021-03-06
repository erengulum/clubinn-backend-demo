package com.hacettepe.clubinn.service.implementations;

import com.hacettepe.clubinn.model.dto.FormDto;
import com.hacettepe.clubinn.model.dto.QuestionDto;
import com.hacettepe.clubinn.model.dto.SubClubDto;
import com.hacettepe.clubinn.model.entity.Form;
import com.hacettepe.clubinn.model.entity.Question;
import com.hacettepe.clubinn.model.entity.SubClub;
import com.hacettepe.clubinn.model.entity.User;
import com.hacettepe.clubinn.model.repository.*;
import com.hacettepe.clubinn.service.FormService;
import com.hacettepe.clubinn.service.SubClubService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class FormServiceImpl implements FormService {

    private final FormRepository formRepository;
    private final QuestionRepository questionRepository;
    private final SubClubRepository subClubRepository;
    private final UserRepository userRepository;
    private final SubClubService subClubService;
    private final ModelMapper modelMapper;

    public FormServiceImpl(FormRepository formRepository, QuestionRepository questionRepository, SubClubRepository subClubRepository, UserRepository userRepository, SubClubService subClubService, ModelMapper modelMapper) {
        this.formRepository = formRepository;
        this.questionRepository = questionRepository;
        this.subClubRepository = subClubRepository;
        this.userRepository = userRepository;
        this.subClubService = subClubService;
        this.modelMapper = modelMapper;
    }

    /**
     * Allows creating questionnaire for newly created subclub.
     *
     * @param formDto --> Form dto from frontend.
     * @return --> Boolean message
     **/
    @Override
    public Boolean createForm(FormDto formDto) {

        try {
            Form isFormCreated = formRepository.findBySubClub_SubClubName(formDto.getBagliolduguGrup());

            if (isFormCreated != null) {
                log.error("There is already a form for your sub club !!");
                return Boolean.FALSE;
            }

            SubClub isSubClubCreated = subClubRepository.findBySubClubName(formDto.getBagliolduguGrup());
            if (isSubClubCreated == null) {
                log.error("There isn't any subClub for your club name !");
                return Boolean.FALSE;
            }

            Form newForm = new Form();
            newForm.setSubClub(isSubClubCreated);
            newForm.setQuestionList(new ArrayList<>());
            Collection<QuestionDto> questionList = formDto.getQuestionList();

            if (!createQuestions(questionList, newForm)) {
                log.error("Questions are not appropriate !");
                return Boolean.FALSE;
            }

            isSubClubCreated.setForm(newForm);
            subClubRepository.save(isSubClubCreated);
            return Boolean.TRUE;

        } catch (Exception e) {
            e.printStackTrace();
            log.warn("Error while creating form !");
            return Boolean.FALSE;
        }
    }

    /**
     * It creates a list of questions for the form to be created.
     *
     * @param questionList --> Questions to created
     * @param form         --> The form object to which questions will be assigned
     * @return --> Boolean message
     **/
    @Override
    public Boolean createQuestions(Collection<QuestionDto> questionList, Form form) {

        for (QuestionDto questionDto : questionList) {

            String questionContent = questionDto.getQuestionContent();
            Question question = questionRepository.findByQuestionContent(questionContent);

            if (question != null) {
                log.warn("Question already exists !");
                return Boolean.FALSE;
            }
            question = new Question();
            question.setForm(form);
            question.setQuestionContent(questionContent);
            questionRepository.save(question);
            form.getQuestionList().add(question);
        }

        formRepository.save(form);
        return Boolean.TRUE;
    }

    /**
     * It returns forms using the sub club id list that come as parameters.
     *
     * @param subClubIdList --> List of sub club id
     * @return --> List of forms
     **/
    @Override
    public List<FormDto> getAllBySubClub(Long[] subClubIdList) {

        int length = subClubIdList.length;

        FormDto[] formDtoList = new FormDto[length];

        for (int i = 0; i < subClubIdList.length; i++) {
            System.out.println(subClubIdList[i]);
            Form currentForm = formRepository.findBySubClub_Id(subClubIdList[i]);
            System.out.println(currentForm.getSubClub().getSubClubName());
            formDtoList[i] = modelMapper.map(currentForm, FormDto.class);
            formDtoList[i].setBagliolduguGrup(currentForm.getSubClub().getSubClubName());
        }

        return Arrays.asList(formDtoList);

    }

    /**
     * It returns sub clubs using the form list that come as parameters.
     *
     * @param formDtoList --> List of sub club id
     * @return --> List of sub clubs
     **/
    @Override
    public List<SubClubDto> questionnaire(List<FormDto> formDtoList) {

        List<SubClubDto> subClubDtoList = new ArrayList<>();

        for (FormDto formDto : formDtoList) {
            int totalScore = 0;
            for (QuestionDto questionDto : formDto.getQuestionList()) {
                totalScore += percentageCalculator(questionDto.getAnswer());
            }
            if (totalScore > 50) {
                subClubDtoList.addAll(subClubService.getAllByFormId(formDto.getFormId()));
            }
        }

        return subClubDtoList;
    }

    /**
     * It checks whether the user can enter the club by measuring their interest in the club.
     * If user's interest is greater than 50%, user can join the club.
     *
     * @param answer --> Answer come from frontend
     * @return --> Percentage of their interest
     **/
    @Override
    public int percentageCalculator(int answer) {
        switch (answer) {
            case 0:
                return 0;
            case 1:
                return 4;
            case 2:
                return 8;
            case 3:
                return 12;
            case 4:
                return 16;
            case 5:
                return 20;
            default:
                log.warn("0 ile 5 aras?? bir de??er girilmedi !");
                return -1;
        }
    }

    /**
     * After the questionnaire process is over, it sets the clubs that the user wants.
     *
     * @param subClubIdList --> List of sub club id
     * @return --> Boolean message
     **/
    @Override
    public Boolean formCompleted(Long[] subClubIdList) {
        User user = getAuthenticatedUser();
        try {
            for (Long subClubId : subClubIdList) {
                SubClub subClub = subClubRepository.getOne(subClubId);
                subClub.getMembers().add(user);
                subClubRepository.save(subClub);

                user.getSubclubs().add(subClub);
                userRepository.save(user);

            }
            return Boolean.TRUE;
        } catch (Exception e) {
            log.warn("User can not join club!");
            return Boolean.FALSE;
        }

    }

    /**
     * This function retrieves the current authenticated user information.
     *
     * @return --> Current authenticated user
     **/
    private User getAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        return userRepository.findByUsername(username);
    }
}
