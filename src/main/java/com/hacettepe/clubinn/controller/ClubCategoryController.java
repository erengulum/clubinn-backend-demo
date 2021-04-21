package com.hacettepe.clubinn.controller;


import com.hacettepe.clubinn.config.helper.ResponseMessage;
import com.hacettepe.clubinn.model.dto.ClubCategoryDto;
import com.hacettepe.clubinn.model.dto.RegistrationRequest;
import com.hacettepe.clubinn.model.entity.ClubCategory;
import com.hacettepe.clubinn.service.ClubCategoryService;
import com.hacettepe.clubinn.service.UserService;
import com.hacettepe.clubinn.util.ApiPaths;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
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

        Boolean response = clubCategoryService.createNewSubClub(clubCategoryDto);

        if(!response){
            responseMessage.setResponseMessage("Yeni kulüp kategorisi oluşturulurken bir hata meydana geldi.Lütfen tekrar deneyiniz");
            return ResponseEntity.badRequest().body(responseMessage);
        }

        else{
            responseMessage.setResponseMessage("Yeni Kulüp Kategorisi başarıyla oluşturuldu!");
            return ResponseEntity.ok(responseMessage);
        }


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














}
