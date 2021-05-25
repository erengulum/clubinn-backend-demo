package com.hacettepe.clubinn.controller;


import com.hacettepe.clubinn.config.helper.ResponseMessage;
import com.hacettepe.clubinn.model.dto.*;
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

    public SubClubController(SubClubService subClubService, ResponseMessage responseMessage) {
        this.subClubService = subClubService;
        this.responseMessage = responseMessage;
    }


    @RequestMapping(value = "join", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> joinSubClub(@Validated @RequestBody JoinDto joinDto) {

        Boolean response = subClubService.join(joinDto);


        if (response) {
            responseMessage.setResponseMessage("Basarili bir sekilde kaydoldunuz");
            return ResponseEntity.ok(responseMessage);

        } else {
            responseMessage.setResponseMessage("Bir hata meydana geldi. Lütfen tekrar deneyin");
            return ResponseEntity.badRequest().body(responseMessage);
        }


    }

    @RequestMapping(value = "getall/{username}", method = RequestMethod.GET)
    public ResponseEntity<List<SubClubDto>> getSubclubMemberships(@PathVariable String username) {

        List<SubClubDto> subClubDtos = subClubService.getAllSubClubMemberships(username);

        return ResponseEntity.ok(subClubDtos);
    }


    @RequestMapping(value = "getmembers/{subclubId}", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> getMembersOfSubclub(@PathVariable Long subclubId) {

        List<UserDto> members = subClubService.getAllSubclubMembers(subclubId);

        return ResponseEntity.ok(members);
    }


    @RequestMapping(value = "/{subclubid}", method = RequestMethod.GET)
    public ResponseEntity<SubClubDto> getSubClub(@PathVariable Long subclubid) {
        return ResponseEntity.ok(subClubService.getOne(subclubid));
    }


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<SubClubDto>> getAllSubClubDto() {
        List<SubClubDto> data = subClubService.getAll();
        return ResponseEntity.ok(data);
    }


    @RequestMapping(value = "/all/{categoryId}", method = RequestMethod.GET)
    public ResponseEntity<List<SubClubDto>> getAllSubClubsByCategory(@PathVariable Long categoryId) {
        List<SubClubDto> data = subClubService.getAllByCategory(categoryId);
        return ResponseEntity.ok(data);
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> createNewClub(@Validated @RequestBody SubClubDto subClubDto) {

        Boolean response = subClubService.createNewSubClub(subClubDto);

        if (!response) {
            responseMessage.setResponseMessage("Yeni subclub  oluşturulurken bir hata meydana geldi.Lütfen tekrar deneyiniz");
            return ResponseEntity.badRequest().body(responseMessage);
        } else {
            responseMessage.setResponseMessage("Yeni subclub başarıyla oluşturuldu!");
            return ResponseEntity.ok(responseMessage);
        }


    }


    @RequestMapping(value = "/delete/{subClubId}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseMessage> deleteClubCategory(@PathVariable Long subClubId) {

        Boolean response = subClubService.deleteSubClub(subClubId);

        if (!response) {
            responseMessage.setResponseMessage("Silme işlemi sirasında bir hata meydana geldi.Lütfen tekrar deneyiniz");
            return ResponseEntity.badRequest().body(responseMessage);
        } else {
            responseMessage.setResponseMessage("Silme islemi basariyla gerceklestirildi");
            return ResponseEntity.ok(responseMessage);
        }

    }


    //ANNOUNCEMENTS


    @RequestMapping(value = "announcements/all/{subclubId}", method = RequestMethod.GET)
    public ResponseEntity<List<AnnouncementDto>> getAllAnnouncements(@PathVariable Long subclubId) {
        List<AnnouncementDto> data = subClubService.getAllAnnouncements(subclubId);
        return ResponseEntity.ok(data);
    }


    @RequestMapping(value = "announcements/create/{subclubId}", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> createNewAnnouncement(@PathVariable Long subclubId, @Validated @RequestBody AnnouncementDto announcementDto) {

        Boolean response = subClubService.createNewAnnouncement(announcementDto, subclubId);


        if (!response) {
            responseMessage.setResponseMessage("Yeni duyuru oluşturulurken bir hata meydana geldi.Lütfen tekrar deneyiniz");
            return ResponseEntity.badRequest().body(responseMessage);
        } else {
            responseMessage.setResponseMessage("Yeni duyuru başarıyla oluşturuldu!");
            return ResponseEntity.ok(responseMessage);
        }


    }

    @RequestMapping(value = "announcements/delete/{announcementId}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseMessage> deletAnnons(@PathVariable Long announcementId) {

        Boolean response = subClubService.deleteSubClubAnnouncement(announcementId);

        if (!response) {
            responseMessage.setResponseMessage("Silme işlemi sirasında bir hata meydana geldi.Lütfen tekrar deneyiniz");
            return ResponseEntity.badRequest().body(responseMessage);
        } else {
            responseMessage.setResponseMessage("Silme islemi basariyla gerceklestirildi");
            return ResponseEntity.ok(responseMessage);
        }

    }

    // FEEDBACK CRUD

    @RequestMapping(value = "feedbacks/create/{subclubId}/{username}", method = RequestMethod.POST)
    public ResponseEntity<FeedbackDto> createNewFeedback(@PathVariable("subclubId") Long subclubId, @PathVariable("username") String username, @Validated @RequestBody FeedbackDto feedbackDto) {

        return ResponseEntity.ok(subClubService.createNewFeedback(feedbackDto,subclubId,username));

    }


    @RequestMapping(value = "feedbacks/all/{subclubId}",method = RequestMethod.GET)
    public ResponseEntity<List<FeedbackDto>> getAllFeedbacksBySubClubId(@PathVariable Long subclubId){
        List<FeedbackDto> data = subClubService.getAllFeedbacks(subclubId);
        return ResponseEntity.ok(data);
    }

    @RequestMapping(value = "feedbacks/update/{feedbackId}" , method=RequestMethod.PUT)
    public ResponseEntity<ResponseMessage> updateFeedback(@PathVariable Long feedbackId, @RequestBody FeedbackDto feedbackDto){

        Boolean response = subClubService.updateFeedback(feedbackDto,feedbackId);

        if(!response){
            responseMessage.setResponseMessage("Error while updating feedback");
            return ResponseEntity.badRequest().body(responseMessage);
        }

        else{
            responseMessage.setResponseMessage("Feedback is successfully updated");
            return ResponseEntity.ok(responseMessage);
        }

    }

    @RequestMapping(value = "feedbacks/delete/{feedbackId}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseMessage> deleteFeedback(@PathVariable Long feedbackId) {

        Boolean response = subClubService.deleteFeedback(feedbackId);

        if(!response){
            responseMessage.setResponseMessage("Silme işlemi sirasında bir hata meydana geldi.Lütfen tekrar deneyiniz");
            return ResponseEntity.badRequest().body(responseMessage);
        }

        else{
            responseMessage.setResponseMessage("Silme islemi basariyla gerceklestirildi");
            return ResponseEntity.ok(responseMessage);
        }

    }

    //Administration
    @RequestMapping(value = "/{subclubId}/admin/{userId}", method = RequestMethod.GET)
    public ResponseEntity<ResponseMessage> assignAdmin(@PathVariable("subclubId") Long subclubId, @PathVariable("userId") Long userId) {
        Boolean response = subClubService.assignAdmin(subclubId, userId);

        if (response) {
            responseMessage.setResponseMessage("Admin atama işlemi basariyla tamamlanmıştır");
            return ResponseEntity.ok(responseMessage);
        } else {
            responseMessage.setResponseMessage("Admin atama işlemi sırasında bir hata meydana geldi");
            return ResponseEntity.badRequest().body(responseMessage);
        }


    }

    //Get Admin of the subclub
    @RequestMapping(value = "/getadmin/{subclubId}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getAdmin(@PathVariable Long subclubId) {
        log.warn("getAdmin calisiyor");
        UserDto response = subClubService.getSubclubAdmin(subclubId);

        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.ok(null);
        }

    }


}
