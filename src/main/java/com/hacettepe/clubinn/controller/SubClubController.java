package com.hacettepe.clubinn.controller;


import com.hacettepe.clubinn.config.helper.ResponseMessage;
import com.hacettepe.clubinn.model.dto.ClubCategoryDto;
import com.hacettepe.clubinn.model.dto.SubClubDto;
import com.hacettepe.clubinn.model.entity.SubClub;
import com.hacettepe.clubinn.service.SubClubService;
import com.hacettepe.clubinn.util.ApiPaths;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/subclubs")
@CrossOrigin(origins = ApiPaths.LOCAL_CLIENT_BASE_PATH, maxAge = 3600)
public class SubClubController {



    private final SubClubService subClubService;
    private final ResponseMessage responseMessage;

    public SubClubController(SubClubService subClubService, ResponseMessage responseMessage){
        this.subClubService = subClubService;
        this.responseMessage = responseMessage;
    }


    @RequestMapping(value = "/{subclubid}", method = RequestMethod.GET)
    public ResponseEntity<SubClubDto> getSubClub(@PathVariable Long subclubid){
        return ResponseEntity.ok(subClubService.getOne(subclubid));
    }




    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<List<SubClubDto>> getAllSubClubDto(){
        List<SubClubDto> data = subClubService.getAll();
        return ResponseEntity.ok(data);
    }




    @RequestMapping(value = "/all/{categoryId}",method = RequestMethod.GET)
    public ResponseEntity<List<SubClubDto>> getAllSubClubsByCategory(@PathVariable Long categoryId){
        List<SubClubDto> data = subClubService.getAllByCategory(categoryId);
        return ResponseEntity.ok(data);
    }





    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> createNewClub(@Validated @RequestBody SubClubDto subClubDto) {

        Boolean response = subClubService.createNewSubClub(subClubDto);

        if(!response){
            responseMessage.setResponseMessage("Yeni subclub  oluşturulurken bir hata meydana geldi.Lütfen tekrar deneyiniz");
            return ResponseEntity.badRequest().body(responseMessage);
        }

        else{
            responseMessage.setResponseMessage("Yeni subclub başarıyla oluşturuldu!");
            return ResponseEntity.ok(responseMessage);
        }


    }





    @RequestMapping(value = "/delete/{subClubId}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseMessage> deleteClubCategory(@PathVariable Long subClubId) {

        Boolean response = subClubService.deleteSubClub(subClubId);

        if(!response){
            responseMessage.setResponseMessage("Silme işlemi sirasında bir hata meydana geldi.Lütfen tekrar deneyiniz");
            return ResponseEntity.badRequest().body(responseMessage);
        }

        else{
            responseMessage.setResponseMessage("Silme islemi basariyla gerceklestirildi");
            return ResponseEntity.ok(responseMessage);
        }

    }



}