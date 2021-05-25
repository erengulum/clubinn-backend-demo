package com.hacettepe.clubinn.controller;

import com.hacettepe.clubinn.config.helper.ResponseMessage;
import com.hacettepe.clubinn.model.dto.FormDto;
import com.hacettepe.clubinn.model.dto.SubClubDto;
import com.hacettepe.clubinn.service.FormService;
import com.hacettepe.clubinn.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/form")
@CrossOrigin(origins = ApiPaths.LOCAL_CLIENT_BASE_PATH, maxAge = 3600)
@Api(value = "Questionnaire API")
public class FormController {

    private final FormService formService;
    private final ResponseMessage responseMessage;

    public FormController(FormService formService, ResponseMessage responseMessage) {
        this.formService = formService;
        this.responseMessage = responseMessage;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ApiOperation(value = "Create Form Operation", response = ResponseMessage.class)
    public ResponseEntity<ResponseMessage> createForm(@Validated @RequestBody FormDto formDto) {
        Boolean response = formService.createForm(formDto);

        if (!response) {
            responseMessage.setResponseMessage("There is a problem during creation form. Please try again");
            return ResponseEntity.badRequest().body(responseMessage);
        } else {
            responseMessage.setResponseMessage("Form is successfully created");
            return ResponseEntity.ok(responseMessage);
        }
    }

    @RequestMapping(value = "/selected", method = RequestMethod.POST)
    @ApiOperation(value = "Get All Selected SubClub Operation", response = FormDto.class)
    public ResponseEntity<List<FormDto>> getAllSubClubDto(@Validated @RequestBody Long[] subClubIdList) {
        List<FormDto> formDtoList = formService.getAllBySubClub((subClubIdList));
        return ResponseEntity.ok(formDtoList);
    }

    @RequestMapping(value = "/questionnaire", method = RequestMethod.POST)
    @ApiOperation(value = "Questionnaire Operation", response = SubClubDto.class)
    public ResponseEntity<List<SubClubDto>> questionnaire(@Validated @RequestBody List<FormDto> formDtoList) {
        List<SubClubDto> subClubDtoList = formService.questionnaire(formDtoList);
        return ResponseEntity.ok(subClubDtoList);
    }

    @RequestMapping(value = "/completed", method = RequestMethod.PUT)
    @ApiOperation(value = "Form Complete Operation", response = ResponseMessage.class)
    public ResponseEntity<ResponseMessage> formCompleted(@Validated @RequestBody Long[] subClubIdList) {
        Boolean response = formService.formCompleted(subClubIdList);

        if (!response) {
            responseMessage.setResponseMessage("There is a problem during form. Please try again");
            return ResponseEntity.badRequest().body(responseMessage);
        } else {
            responseMessage.setResponseMessage("Form is successfully finished");
            return ResponseEntity.ok(responseMessage);
        }
    }

}

