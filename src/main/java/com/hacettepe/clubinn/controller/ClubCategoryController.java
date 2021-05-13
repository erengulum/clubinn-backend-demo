package com.hacettepe.clubinn.controller;


import com.hacettepe.clubinn.config.helper.ResponseMessage;
import com.hacettepe.clubinn.model.dto.ClubCategoryDto;
import com.hacettepe.clubinn.model.dto.ClubCategoryRequestDto;
import com.hacettepe.clubinn.service.ClubCategoryService;
import com.hacettepe.clubinn.util.ApiPaths;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/clubcategories")
@CrossOrigin(origins = ApiPaths.LOCAL_CLIENT_BASE_PATH, maxAge = 3600)
public class ClubCategoryController {

    private final ClubCategoryService clubCategoryService;
    private final ResponseMessage responseMessage;

    public ClubCategoryController(ClubCategoryService clubCategoryService, ResponseMessage responseMessage){
        this.clubCategoryService = clubCategoryService;
        this.responseMessage = responseMessage;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ClubCategoryDto> getClubCategory(@PathVariable Long id){
        return ResponseEntity.ok(clubCategoryService.getOne(id));
    }



    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<List<ClubCategoryDto>> getAllCategories(){
        List<ClubCategoryDto> data = clubCategoryService.getAll();
        return ResponseEntity.ok(data);
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> createNewClub(@Validated @RequestBody ClubCategoryDto clubCategoryDto) {

        String response = clubCategoryService.createNewClub(clubCategoryDto);
        responseMessage.setResponseMessage(response);

        return ResponseEntity.ok(responseMessage);


    }


    @RequestMapping(value = "/delete/{clubId}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseMessage> deleteClubCategory(@PathVariable Long clubId) {

        Boolean response = clubCategoryService.delete(clubId);

        if(!response){
            responseMessage.setResponseMessage("Silme işlemi sirasında bir hata meydana geldi.Lütfen tekrar deneyiniz");
            return ResponseEntity.badRequest().body(responseMessage);
        }

        else{
            responseMessage.setResponseMessage("Silme islemi basariyla gerceklestirildi");
            return ResponseEntity.ok(responseMessage);
        }

    }




    @RequestMapping(value = "/request/create", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> createNewRequest(@Validated @RequestBody ClubCategoryRequestDto clubCategoryRequestDto) {

        String response = clubCategoryService.requestClubCategory(clubCategoryRequestDto);
        responseMessage.setResponseMessage(response);
        return ResponseEntity.ok(responseMessage);

    }

    @RequestMapping(value = "/request/all",method = RequestMethod.GET)
    public ResponseEntity<List<ClubCategoryRequestDto>> getAllRequests(){
        List<ClubCategoryRequestDto> data = clubCategoryService.getAllRequests();
        return ResponseEntity.ok(data);
    }


    @RequestMapping(value = "/request/delete/{requestId}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseMessage> deleteClubCategoryRequest(@PathVariable Long requestId) {
        log.warn("Request silme islemi backende geldi");
        Boolean response = clubCategoryService.deleteClubRequest(requestId);

        if(!response){
            responseMessage.setResponseMessage("There is a problem during deletion process. Please try again");
            return ResponseEntity.badRequest().body(responseMessage);
        }

        else{
            responseMessage.setResponseMessage("Request is successfully deleted");
            return ResponseEntity.ok(responseMessage);
        }

    }

    @RequestMapping(value = "/request/vote/{id}",method = RequestMethod.GET)
    public ResponseEntity<Boolean> voteForRequest(@PathVariable Long id){
        log.warn("oylama controller");
        Boolean response = clubCategoryService.voteForRequest(id);
        return ResponseEntity.ok(response);
    }



    @RequestMapping(value = "/request/create/club", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> convertRequestToClub(@Validated @RequestBody ClubCategoryRequestDto clubCategoryRequestDto) {
        log.warn("convert controller");
        String response = clubCategoryService.convertRequestToClub(clubCategoryRequestDto);
        responseMessage.setResponseMessage(response);
        log.warn(" controller cikiss");
        return ResponseEntity.ok(responseMessage);

    }



}
